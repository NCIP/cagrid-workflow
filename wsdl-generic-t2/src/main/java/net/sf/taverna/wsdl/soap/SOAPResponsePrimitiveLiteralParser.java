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
package net.sf.taverna.wsdl.soap;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.taverna.wsdl.parser.TypeDescriptor;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * A response parser specifically for literal use services that return primative types.
 * It extends the SOAPReponseLiteralParser, but unwraps the result from the enclosing XML
 * to expose the primitive result.
 * 
 * This is specially designed for unwrapped/literal type services, and RPC/literal services (untested). 
 * @author Stuart
 *
 */
@SuppressWarnings("unchecked")
public class SOAPResponsePrimitiveLiteralParser extends
		SOAPResponseLiteralParser {

	public SOAPResponsePrimitiveLiteralParser(List<TypeDescriptor> outputDescriptors) {
		super(outputDescriptors);
	}

	@Override
	public Map parse(List response) throws Exception {
		Map result = super.parse(response);
		Object dataValue = result.get(getOutputName());
		if (dataValue!=null) {
			String xml = dataValue.toString();
			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
			.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		
			Node node = doc.getFirstChild();
			result.put(getOutputName(), node.getFirstChild().getNodeValue());
		}
		return result;
	}
	
	
}

	
