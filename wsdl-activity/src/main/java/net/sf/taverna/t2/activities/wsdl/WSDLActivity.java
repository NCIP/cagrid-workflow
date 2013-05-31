/**
*============================================================================
*  The Ohio State University Research Foundation, The University of Chicago -
*  Argonne National Laboratory, Emory University, SemanticBits LLC, 
*  and Ekagra Software Technologies Ltd.
*
*  Distributed under the OSI-approved BSD 3-Clause License.
*  See http://ncip.github.com/cagrid-workflow/LICENSE.txt for details.
*============================================================================
**/
package net.sf.taverna.t2.activities.wsdl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.wsdl.WSDLException;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.taverna.t2.reference.ExternalReferenceSPI;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.ReferenceServiceException;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.workflowmodel.OutputPort;
import net.sf.taverna.t2.workflowmodel.processor.activity.AbstractAsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivityCallback;
import net.sf.taverna.wsdl.parser.TypeDescriptor;
import net.sf.taverna.wsdl.parser.UnknownOperationException;
import net.sf.taverna.wsdl.parser.WSDLParser;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.XMLStringProvider;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * An asynchronous Activity that is concerned with WSDL based web-services.
 * <p>
 * The activity is configured according to the WSDL location and the operation.<br>
 * The ports are defined dynamically according to the WSDL specification, and in
 * addition an output<br>
 * port <em>attachmentList</em> is added to represent any attachements that are
 * returned by the webservice.
 * </p>
 * 
 * @author Stuart Owen
 */
public class WSDLActivity extends
		AbstractAsynchronousActivity<WSDLActivityConfigurationBean> {
	private WSDLActivityConfigurationBean configurationBean;
	private WSDLParser parser;
	private Map<String, Integer> outputDepth = new HashMap<String, Integer>();

	private static Logger logger = Logger.getLogger(WSDLActivity.class);

	/**
	 * Configures the activity according to the information passed by the
	 * configuration bean.<br>
	 * During this process the WSDL is parsed to determine the input and output
	 * ports.
	 * 
	 * @param bean
	 *            the {@link WSDLActivityConfigurationBean} configuration bean
	 */
	@Override
	public void configure(WSDLActivityConfigurationBean bean)
			throws ActivityConfigurationException {
		this.configurationBean = bean;
		try {
			parseWSDL();
			configurePorts();
		} catch (Exception ex) {
			throw new ActivityConfigurationException(
					"Unable to parse the WSDL", ex);
		}
	}

	/**
	 * @return a {@link WSDLActivityConfigurationBean} representing the
	 *         WSDLActivity configuration
	 */
	@Override
	public WSDLActivityConfigurationBean getConfiguration() {
		return configurationBean;
	}

	/**
	 * Provides access to the TypeDescriptor for a given input port name.
	 * <br>
	 * This TypeDescriptor represents the Type defined in the schema for this Activities
	 * WSDL.
	 * 
	 * @param portName
	 * @return the TypeDescriptor, or null if the portName is not recognised.
	 * @throws UnknownOperationException if the operation this Activity is associated with doesn't exist.
	 * @throws IOException
	 * 
	 * @see TypeDescriptor
	 */
	public TypeDescriptor getTypeDescriptorForInputPort(String portName)
			throws UnknownOperationException, IOException {
		List<TypeDescriptor> inputDescriptors = parser
				.getOperationInputParameters(configurationBean.getOperation());
		TypeDescriptor result = null;
		for (TypeDescriptor descriptor : inputDescriptors) {
			if (descriptor.getName().equals(portName)) {
				result = descriptor;
				break;
			}
		}
		return result;
	}

	/**
	 * Provides access to the TypeDescriptor for a given output port name.
	 * <br>
	 * This TypeDescriptor represents the Type defined in the schema for this Activities
	 * WSDL.
	 * 
	 * @param portName
	 * @return the TypeDescriptor, or null if the portName is not recognised.
	 * @throws UnknownOperationException if the operation this Activity is associated with doesn't exist.
	 * @throws IOException
	 * 
	 * @see TypeDescriptor
	 */
	public TypeDescriptor getTypeDescriptorForOutputPort(String portName)
			throws UnknownOperationException, IOException {
		TypeDescriptor result = null;
		List<TypeDescriptor> outputDescriptors = parser
				.getOperationOutputParameters(configurationBean.getOperation());
		for (TypeDescriptor descriptor : outputDescriptors) {
			if (descriptor.getName().equals(portName)) {
				result = descriptor;
				break;
			}
		}
		return result;
	}

	private void parseWSDL() throws ParserConfigurationException,
			WSDLException, IOException, SAXException, UnknownOperationException {
		parser = new WSDLParser(configurationBean.getWsdl());

	}

	private void configurePorts() throws UnknownOperationException, IOException {
		List<TypeDescriptor> inputDescriptors = parser
				.getOperationInputParameters(configurationBean.getOperation());
		List<TypeDescriptor> outputDescriptors = parser
				.getOperationOutputParameters(configurationBean.getOperation());
		for (TypeDescriptor descriptor : inputDescriptors) {
			addInput(descriptor.getName(), descriptor.getDepth(), true,
					new ArrayList<Class<? extends ExternalReferenceSPI>>(),
					String.class);
		}
		//TODO: for a wsrf service, add an additional input port for EndpointReference
		//-----------------------------------------------------------------------------------
		boolean isWSRFService = false;
		String wsrfOperationName = "GetResourceProperty";
		String wsrfOperationActionURI = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetResourceProperty";
		String wsrfURI = null;
		try{
			wsrfURI = parser.getSOAPActionURI(wsrfOperationName);			
		}
	
		catch (Exception e){
			wsrfURI = null;
			System.out.println("NOT a WSRF Service.");
			
		}
		if(wsrfURI!=null){
			System.out.println(parser.getSOAPActionURI(wsrfOperationName));
			if (wsrfURI.equals(wsrfOperationActionURI)){
				isWSRFService = true;
				System.out.println("This is a WSRF Service. \n An additional input port will be added.");
				//parser.setWSRF(true);
			}
			if (isWSRFService ==true){
				addInput("EndpointReference", 0, true,
						new ArrayList<Class<? extends ExternalReferenceSPI>>(),
						String.class);
			}
			
		}
		
		//-----------------------------------------------------------------------------------

		for (TypeDescriptor descriptor : outputDescriptors) {
			addOutput(descriptor.getName(), descriptor.getDepth());
			outputDepth.put(descriptor.getName(), Integer.valueOf(descriptor
					.getDepth()));
		}

		// add output for attachment list
		addOutput("attachmentList", 1);
		outputDepth.put("attachmentList", Integer.valueOf(1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeAsynch(final Map<String, T2Reference> data,
			final AsynchronousActivityCallback callback) {

		callback.requestRun(new Runnable() {

			public void run() {

				ReferenceService referenceService = callback.getContext()
						.getReferenceService();

				Map<String, T2Reference> outputData = new HashMap<String, T2Reference>();
				Map<String, Object> invokerInputMap = new HashMap<String, Object>();

				try {
					for (String key : data.keySet()) {
						invokerInputMap.put(key, referenceService
								.renderIdentifier(data.get(key), String.class,
										callback.getContext()));
					}
					List<String> outputNames = new ArrayList<String>();
					for (OutputPort port : getOutputPorts()) {
						outputNames.add(port.getName());
					}

					T2WSDLSOAPInvoker invoker = new T2WSDLSOAPInvoker(parser,
							configurationBean.getOperation(), outputNames);
					WSDLActivityConfigurationBean bean = getConfiguration();
					EngineConfiguration wssEngineConfiguration = null;

					if (bean.getSecurityProfileString() != null) {
						wssEngineConfiguration = new XMLStringProvider(bean
								.getSecurityProfileString());
					}

					Map<String, Object> invokerOutputMap = invoker.invoke(
							invokerInputMap, wssEngineConfiguration);

					for (String outputName : invokerOutputMap.keySet()) {
						Object value = invokerOutputMap.get(outputName);

						if (value != null) {
							Integer depth = outputDepth.get(outputName);
							if (depth != null) {
								outputData.put(outputName, referenceService
										.register(value, depth, true, callback
												.getContext()));
							} else {
								System.out
										.println("Depth not recorded for output:"
												+ outputName);
								// TODO what should the depth be in this case?
								outputData.put(outputName, referenceService
										.register(value, 0, true, callback
												.getContext()));
							}
						}
					}
				} catch (ReferenceServiceException e) {
					logger.error("Error finding the input data for "
							+ getConfiguration().getOperation(), e);
					callback.fail("Unable to find input data", e);
				} catch (Exception e) {
					logger.error("Error invoking WSDL service "
							+ getConfiguration().getOperation(), e);
					callback.fail(
							"An error occurred invoking the WSDLActivity", e);
				}

				callback.receiveResult(outputData, new int[0]);
			}

		});

	}
}
