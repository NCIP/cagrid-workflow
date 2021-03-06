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
package gov.nih.nci.cagrid.portal.portlet.workflow.mvc;

import gov.nih.nci.cagrid.portal.portlet.workflow.WorkflowExecutionService;
import gov.nih.nci.cagrid.portal.portlet.workflow.domain.SessionEprs;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;

@Controller
@RequestMapping(params={"action=viewInstances"})
public class ViewInstancesController extends AbstractController {
	protected final Log log = LogFactory.getLog(ViewInstancesController.class);

	private WorkflowExecutionService workflowService;
	private SessionEprs eprs;
	
	@Override
	protected ModelAndView handleRenderRequestInternal(RenderRequest request, RenderResponse response) throws Exception {
		String action = PortletRequestUtils.getStringParameter(request, "action", "NaN");
    	log.info("handleRenderRequest.  Action: " + action);
    	boolean isAllDone = workflowService.updateSession(getSessionEprs());
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put( "eprs", eprs.getEprs());
    	model.put("isEmpty", eprs.size()==0);
    	model.put("isAllDone", isAllDone); //whether all instances are in a stopped state.  No more polling necessary
    	log.debug("Model contents: " + model);
    	return new ModelAndView("viewInstances",model);
	}	

	public SessionEprs getSessionEprs() {
		return eprs;
	}
	public void setSessionEprs(SessionEprs sessionEprs) {
		this.eprs = sessionEprs;
	}
	public WorkflowExecutionService getWorkflowService() {
		return workflowService;
	}
	public void setWorkflowService(WorkflowExecutionService workflowService) {
		this.workflowService = workflowService;
	}
}

 
 
