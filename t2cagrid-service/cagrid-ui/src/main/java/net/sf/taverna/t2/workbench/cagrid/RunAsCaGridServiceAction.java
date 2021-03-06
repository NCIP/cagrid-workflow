/*******************************************************************************
 * Copyright (C) 2007 The University of Manchester   
 * 
 *  Modifications to the initial code base are copyright of their
 *  respective authors, or their employers as appropriate.
 * 
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *    
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *    
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 ******************************************************************************/
package net.sf.taverna.t2.workbench.cagrid;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.taverna.t2.facade.WorkflowInstanceFacade;
import net.sf.taverna.t2.invocation.InvocationContext;
import net.sf.taverna.t2.lang.ui.ModelMap;
import net.sf.taverna.t2.provenance.ProvenanceConnectorRegistry;
import net.sf.taverna.t2.provenance.connector.ProvenanceConnector;
import net.sf.taverna.t2.reference.ReferenceContext;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.reference.ui.WorkflowLaunchPanel;
import net.sf.taverna.t2.workbench.ModelMapConstants;
import net.sf.taverna.t2.workbench.icons.WorkbenchIcons;
import net.sf.taverna.t2.workbench.provenance.ProvenanceConfiguration;
//import net.sf.taverna.t2.workbench.run.DataflowRunsComponent;
import net.sf.taverna.t2.workbench.ui.impl.Workbench;
import net.sf.taverna.t2.workbench.ui.zaria.PerspectiveSPI;
import net.sf.taverna.t2.workflowmodel.Dataflow;
import net.sf.taverna.t2.workflowmodel.DataflowInputPort;
import net.sf.taverna.t2.workflowmodel.DataflowOutputPort;
import net.sf.taverna.t2.workflowmodel.DataflowValidationReport;
import net.sf.taverna.t2.workflowmodel.EditException;
import net.sf.taverna.t2.workflowmodel.InvalidDataflowException;
import net.sf.taverna.t2.workflowmodel.TokenProcessingEntity;
import net.sf.taverna.t2.workflowmodel.impl.EditsImpl;
import net.sf.taverna.t2.workflowmodel.serialization.DeserializationException;
import net.sf.taverna.t2.workflowmodel.serialization.SerializationException;
import net.sf.taverna.t2.workflowmodel.serialization.xml.XMLDeserializer;
import net.sf.taverna.t2.workflowmodel.serialization.xml.XMLDeserializerImpl;
import net.sf.taverna.t2.workflowmodel.serialization.xml.XMLSerializer;
import net.sf.taverna.t2.workflowmodel.serialization.xml.XMLSerializerImpl;

import org.apache.log4j.Logger;

public class RunAsCaGridServiceAction extends AbstractAction {

	private final class InvocationContextImplementation implements
			InvocationContext {
		private final ReferenceService referenceService;
		
		private final ProvenanceConnector provenanceConnector;

		private InvocationContextImplementation(
				ReferenceService referenceService, ProvenanceConnector provenanceConnector) {
			this.referenceService = referenceService;
			this.provenanceConnector = provenanceConnector;
		}

		public ReferenceService getReferenceService() {
			return referenceService;
		}

		public <T> List<? extends T> getEntities(Class<T> entityType) {
			// TODO Auto-generated method stub
			return null;
		}

		public ProvenanceConnector getProvenanceConnector() {
			return provenanceConnector;
		}
	}

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(RunAsCaGridServiceAction.class);

	private CaGridComponent runComponent;

	//private PerspectiveSPI resultsPerspective;

	public RunAsCaGridServiceAction() {
		
		putValue(SMALL_ICON, WorkbenchIcons.runIcon);
		putValue(NAME, "Run workflow...");
		putValue(SHORT_DESCRIPTION, "Run the current workflow");

	}

	public void actionPerformed(ActionEvent e) {
		runComponent = CaGridComponent.getInstance();
		//when the run button is pushed down
		//1: retrieve workflow definition from workbench
		Object model = ModelMap.getInstance().getModel(
				ModelMapConstants.CURRENT_DATAFLOW);
		if (model instanceof Dataflow) {
			Dataflow dataflow = (Dataflow) model;
			XMLSerializer serialiser = new XMLSerializerImpl();
			XMLDeserializer deserialiser = new XMLDeserializerImpl();
			Dataflow dataflowCopy = null;
			try {
				dataflowCopy = deserialiser.deserializeDataflow(serialiser
						.serializeDataflow(dataflow));
			} catch (SerializationException e1) {
				logger.error("Unable to copy dataflow", e1);
			} catch (DeserializationException e1) {
				logger.error("Unable to copy dataflow", e1);
			} catch (EditException e1) {
				logger.error("Unable to copy dataflow", e1);
			}

			if (dataflowCopy != null) {
				//if provenance turned on then add an IntermediateProvLayer to each Processor
				final ReferenceService referenceService = runComponent.getReferenceService();
				ReferenceContext referenceContext = null;
				ProvenanceConnector provenanceConnector = null;
				if (ProvenanceConfiguration.getInstance().getProperty("enabled").equalsIgnoreCase("yes")) {
					//TODO find out the type of ProvenanceConnector and return it
					String connectorType = ProvenanceConfiguration.getInstance().getProperty("connector");

					for (ProvenanceConnector connector:ProvenanceConnectorRegistry.getInstance().getInstances()) {
						if (connectorType.equalsIgnoreCase(connector.getName())) {
							provenanceConnector = connector;
						}
					}
				}
				
				//2: create instance facade
				
				WorkflowInstanceFacade facade;
				try {
					facade = new EditsImpl().createWorkflowInstanceFacade(
							dataflowCopy, new InvocationContextImplementation(
									referenceService, provenanceConnector), "");
				} catch (InvalidDataflowException ex) {
					invalidDataflow(ex.getDataflowValidationReport());
					return;
				}

				//3: pop up workflow launch panel to get input
				List<? extends DataflowInputPort> inputPorts = dataflowCopy
						.getInputPorts();
				if (!inputPorts.isEmpty()) {
					showInputDialog(facade, referenceContext);
				} else {
					
					runComponent.runWorkflow(facade, (Map) null);
				}

			} else {
				showErrorDialog("Unable to make a copy of the workflow to run",
						"Workflow copy failed");
			}
		}

	}

	private void invalidDataflow(DataflowValidationReport report) {
		StringBuilder sb = new StringBuilder();
		sb.append("Unable to validate workflow due to:");
		List<? extends TokenProcessingEntity> unsatisfiedEntities = report
				.getUnsatisfiedEntities();
		if (unsatisfiedEntities.size() > 0) {
			sb.append("\n Missing inputs or cyclic dependencies:");
			for (TokenProcessingEntity entity : unsatisfiedEntities) {
				sb.append("\n  " + entity.getLocalName());
			}
		}
		List<? extends DataflowOutputPort> unresolvedOutputs = report
				.getUnresolvedOutputs();
		if (unresolvedOutputs.size() > 0) {
			sb.append("\n Invalid or unconnected outputs:");
			for (DataflowOutputPort dataflowOutputPort : unresolvedOutputs) {
				sb.append("\n  " + dataflowOutputPort.getName());
			}
		}
		List<? extends TokenProcessingEntity> failedEntities = report
				.getFailedEntities();
		if (failedEntities.size() > 0) {
			sb.append("\n Type check failure:");
			for (TokenProcessingEntity entity : failedEntities) {
				sb.append("\n  " + entity.getLocalName());
			}
		}
		showErrorDialog(sb.toString(), "Workflow validation failed");

	}


	private void showInputDialog(final WorkflowInstanceFacade facade, ReferenceContext refContext) {
		// Create and set up the window.
		final JFrame frame = new JFrame("Workflow input builder");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		WorkflowLaunchPanel wlp = new WorkflowLaunchPanel(facade.getContext()
				.getReferenceService(), refContext) {
			@Override
			public void handleLaunch(Map<String, T2Reference> workflowInputs) {
				//switchToResultsPerspective();
				runComponent = CaGridComponent.getInstance();
				runComponent.runWorkflow(facade, workflowInputs);
				frame.dispose();
			
			}
		};
		wlp.setOpaque(true); // content panes must be opaque

		for (DataflowInputPort input : facade.getDataflow().getInputPorts()) {
			wlp.addInputTab(input.getName(), input.getDepth());
		}

		frame.setContentPane(wlp);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private void showErrorDialog(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.ERROR_MESSAGE);
	}

}
