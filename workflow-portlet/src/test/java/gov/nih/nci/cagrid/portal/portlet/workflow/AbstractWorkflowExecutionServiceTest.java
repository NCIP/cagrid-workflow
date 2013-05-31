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

import static org.junit.Assert.assertNotNull;

import org.apache.axis.message.addressing.EndpointReferenceType;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public abstract class AbstractWorkflowExecutionServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Test
	public void testSubmitWorkflow() throws WorkflowException {
		String url = super.applicationContext.getResource("fishsoup.t2flow").getFilename();
		EndpointReferenceType epr = getExecutionService().submitWorkflow("fishsoup", url, null);
		assertNotNull("Endpoint Reference", epr);
	}
	
	public abstract WorkflowExecutionService getExecutionService();
}
