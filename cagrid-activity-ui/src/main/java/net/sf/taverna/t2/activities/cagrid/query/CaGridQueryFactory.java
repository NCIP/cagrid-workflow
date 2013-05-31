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

import net.sf.taverna.t2.partition.ActivityQuery;
import net.sf.taverna.t2.partition.ActivityQueryFactory;
import net.sf.taverna.t2.partition.AddQueryActionHandler;

public class CaGridQueryFactory extends ActivityQueryFactory {

	@Override
	protected ActivityQuery createQuery(String property) {
		return new CaGridQuery(property);
	}

	@Override
	protected String getPropertyKey() {
		//TODO: change this line?
		return "taverna.defaultwsdl";
	}

	@Override
	public AddQueryActionHandler getAddQueryActionHandler() {
		return new CaGridAddQueryActionHandler();
	}

	@Override
	public boolean hasAddQueryActionHandler() {
		return true;
	}
	
	

}
