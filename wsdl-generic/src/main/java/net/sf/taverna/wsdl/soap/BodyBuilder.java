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
package net.sf.taverna.wsdl.soap;

import java.io.IOException;
import java.util.Map;

import javax.wsdl.WSDLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import net.sf.taverna.wsdl.parser.UnknownOperationException;

import org.apache.axis.message.SOAPBodyElement;
import org.xml.sax.SAXException;

/**
 * Interface to a class that is responsible for creating the SOAP body elements from the provided inputs
 * for invoking a SOAP based Web-service.
 * 
 * @author Stuart Owen
 */
@SuppressWarnings("unchecked")
public interface BodyBuilder {
	
	public SOAPBodyElement build(Map inputMap)
			throws WSDLException, ParserConfigurationException, SOAPException,
			IOException, SAXException, UnknownOperationException;
	
}

