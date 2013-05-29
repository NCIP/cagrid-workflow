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
package net.sf.taverna.wsdl.xmlsplitter;

public class XMLSplitterExecutionException extends Exception {

	private static final long serialVersionUID = 5623707293500493612L;

	public XMLSplitterExecutionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public XMLSplitterExecutionException(String msg) {
		super(msg);
	}
}
