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
package net.sf.taverna.wsdl.parser;

/**
 * Exception thrown when a given service operation name cannot be found for that
 * WSDL
 * 
 * @author Stuart Owen
 * 
 */

public class UnknownOperationException extends Exception {
	private static final long serialVersionUID = -9119188266154359132L;

	UnknownOperationException(String val) {
		super(val);
	}
}
