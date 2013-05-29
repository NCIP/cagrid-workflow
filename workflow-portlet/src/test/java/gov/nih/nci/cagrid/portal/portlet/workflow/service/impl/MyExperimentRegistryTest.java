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

import gov.nih.nci.cagrid.portal.portlet.workflow.AbstractWorkflowRegistryTest;
import gov.nih.nci.cagrid.portal.portlet.workflow.WorkflowRegistryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class MyExperimentRegistryTest extends AbstractWorkflowRegistryTest {

	@Autowired
	private MyExperimentWorkflowRegistry registry;
	
//	@Test
//	public void testSessionCookie() throws HttpException, IOException {
//		registry.getSessionCookie("kedzie", "Catch-22");
//	}

	@Override
	public WorkflowRegistryService getRegistry() {
		return this.registry;
	}
}
