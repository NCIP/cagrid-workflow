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
package net.sf.taverna.t2.activities.cagrid.query;

public class ServiceQuery {
	public String queryCriteria;
	public String queryValue;
	public ServiceQuery(String queryCriteria, String queryValue){
		this.queryCriteria = queryCriteria;
		this.queryValue = queryValue;
	}
	

}
