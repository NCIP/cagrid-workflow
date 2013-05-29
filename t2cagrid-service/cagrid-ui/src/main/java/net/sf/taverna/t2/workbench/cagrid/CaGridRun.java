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
package net.sf.taverna.t2.workbench.cagrid;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.swing.JTabbedPane;

import net.sf.taverna.t2.reference.T2Reference;


//classes to invoke the caGrid service to execute a given workflow
public class CaGridRun {
	//workflow instance identifier
	private int workflowid;
	//workflow inputs
	private Map<String, T2Reference> inputs;
	//time for execution
	private Date date;
	//xml string that represent the execution results -- may be multiple results
	private String[] results;
	//the container to show multiple results
	private JTabbedPane resultPane;
	CaGridRun(){
		inputs =null;
		date = new Date();
		Random generator = new Random();
		workflowid = generator.nextInt();
	}

}
