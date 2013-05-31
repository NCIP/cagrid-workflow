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
package net.sf.taverna.t2.activities.wsdl.xmlsplitter;

import java.io.IOException;
import java.io.StringReader;

import net.sf.taverna.t2.workflowmodel.health.HealthChecker;
import net.sf.taverna.t2.workflowmodel.health.HealthReport;
import net.sf.taverna.t2.workflowmodel.health.HealthReport.Status;
import net.sf.taverna.wsdl.parser.TypeDescriptor;
import net.sf.taverna.wsdl.xmlsplitter.XMLSplitterSerialisationHelper;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLInputSplitterHealthChecker implements HealthChecker<XMLInputSplitterActivity> {

	public boolean canHandle(Object subject) {
		return subject!=null && subject instanceof XMLInputSplitterActivity;
	}

	public HealthReport checkHealth(XMLInputSplitterActivity activity) {
		String xml = activity.getConfiguration().getWrappedTypeXML();
		Element element;
		try {
			element = new SAXBuilder().build(new StringReader(xml)).getRootElement();
		} catch (JDOMException e) {
			return new HealthReport("XMLInputSplitter Activity","Error reading the configuration XML:"+e.getMessage(),Status.SEVERE);
		} catch (IOException e) {
			return new HealthReport("XMLInputSplitter Activity","Error reading the configuration XML:"+e.getMessage(),Status.SEVERE);
		}
		TypeDescriptor typeDescriptor = XMLSplitterSerialisationHelper.extensionXMLToTypeDescriptor(element);
		if (typeDescriptor==null) {
			return new HealthReport("XMLInputSplitter Activity","The datatype is NULL",Status.SEVERE);
		}
		else {
			return new HealthReport("XMLInputSplitter Activity","The datatype is declared OK",Status.OK);
		}
	}

}
