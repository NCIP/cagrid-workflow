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
package gov.nih.nci.cagrid.portal.portlet.workflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.nih.nci.cagrid.portal.portlet.workflow.domain.WorkflowDescription;

import java.util.Map;

import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public abstract class AbstractWorkflowRegistryTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testGetWorkflows() throws WorkflowException {
		Map<String, WorkflowDescription> workflows = getRegistry().getWorkflows();
		assertNotNull(workflows);
	}
	@Test
	public void testGetWorkflow() throws WorkflowException {
		WorkflowDescription wd = getRegistry().getWorkflow("1508");
		assertNotNull(wd);
		assertEquals("1508", wd.getId());
	}

	public abstract WorkflowRegistryService getRegistry();
}
