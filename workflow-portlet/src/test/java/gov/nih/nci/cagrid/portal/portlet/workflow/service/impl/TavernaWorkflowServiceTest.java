/**
*============================================================================
*  The Ohio State University Research Foundation, The University of Chicago -
*  Argonne National Laboratory, Emory University, SemanticBits LLC, 
*  and Ekagra Software Technologies Ltd.
*
*  Distributed under the OSI-approved BSD 3-Clause License.
*  See http://ncip.github.com/cagrid-core/LICENSE.txt for details.
*============================================================================
**/
package gov.nih.nci.cagrid.portal.portlet.workflow.service.impl;

import gov.nih.nci.cagrid.portal.portlet.workflow.AbstractWorkflowExecutionServiceTest;
import gov.nih.nci.cagrid.portal.portlet.workflow.WorkflowExecutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class TavernaWorkflowServiceTest extends AbstractWorkflowExecutionServiceTest {

	@Autowired
	TavernaWorkflowService svc;
	
	@Override
	public WorkflowExecutionService getExecutionService() {
		return svc;
	}

}
