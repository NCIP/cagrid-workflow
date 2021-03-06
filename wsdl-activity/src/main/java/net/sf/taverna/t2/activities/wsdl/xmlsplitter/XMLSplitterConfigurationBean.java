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
package net.sf.taverna.t2.activities.wsdl.xmlsplitter;


import net.sf.taverna.t2.workflowmodel.processor.activity.config.ActivityPortsDefinitionBean;

public class XMLSplitterConfigurationBean extends ActivityPortsDefinitionBean {
	String wrappedTypeXML;

	public String getWrappedTypeXML() {
		return wrappedTypeXML;
	}

	public void setWrappedTypeXML(String wrappedTypeXML) {
		this.wrappedTypeXML = wrappedTypeXML;
	}
}
