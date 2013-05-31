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
package gov.nih.nci.cagrid.portal.portlet.workflow.domain;


import java.util.Map;
/**
 * The command class that is used to store the Model used for the Views generated.
 * 
 * @author Dinanath Sulakhe sulakhe@mcs.anl.gov
 * Status: This class needs to split into multiple Model classes later when PortletModeParameterHandlerMappings will be used to 
 *   create multiple Controllers.
 */
public class SubmitWorkflowCommand {

	private WorkflowDescription theWorkflow;
	private String[] inputValues;
	private String result;
	
	public String[] getInputValues() {
		return inputValues;
	}
	public void setInputValues(String[] inputValues) {
		this.inputValues = inputValues;
	}
	public WorkflowDescription getTheWorkflow() {
		return theWorkflow;
	}
	public void setTheWorkflow(WorkflowDescription theWorkflow) {
		this.theWorkflow = theWorkflow;
	}
	public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
