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
package gov.nih.nci.cagrid.portal.portlet.workflow.service.impl;

import gov.nih.nci.cagrid.portal.portlet.workflow.domain.WorkflowDescription;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration
public class JSONTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	WorkflowDescription wd;
	@Autowired
	ObjectMapper om;
	
	@Test
	public void testJacksion() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("workflow", wd);
		om.writeValue( System.out, model);
	}
	
}
