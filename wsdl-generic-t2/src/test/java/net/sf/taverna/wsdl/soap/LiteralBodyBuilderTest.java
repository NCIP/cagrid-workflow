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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import net.sf.taverna.wsdl.parser.WSDLParser;
import net.sf.taverna.wsdl.testutils.LocationConstants;

import org.apache.axis.message.SOAPBodyElement;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;

public class LiteralBodyBuilderTest implements LocationConstants{

	@Ignore("Integration test")
	@Test
	public void testUnqualifiedNamespaces() throws Exception {
		BodyBuilder builder = createBuilder(WSDL_TEST_BASE+"whatizit.wsdl", "queryPmid");
		
		assertTrue("Is is the wrong type, it should be LiteralBodyBuilder",builder instanceof LiteralBodyBuilder);
		
		String parameters = "<parameters xmlns=\"http://www.ebi.ac.uk/webservices/whatizit/ws\"><pipelineName xmlns=\"\">swissProt</pipelineName><pmid xmlns=\"\">1234</pmid></parameters>";
		Map<String,Object> inputMap = new HashMap<String, Object>();
		inputMap.put("parameters", parameters);
		
		SOAPBodyElement body = builder.build(inputMap);
		
		String xml = body.getAsString();
		
		assertTrue("Content of body is incorrect in the definition of the pipelineName and pmid:"+xml,xml.contains("<pipelineName xmlns=\"\">swissProt</pipelineName><pmid xmlns=\"\">1234</pmid>"));
		assertTrue("Wrapping element should have its namespace declared",xml.contains("<ns1:queryPmid"));
	}
	
	@Ignore("Integration test")
	@Test
	public void testQualifiedUnwrapped() throws Exception {
		BodyBuilder builder = createBuilder(WSDL_TEST_BASE+"TestServices-unwrapped.wsdl", "countString");
		
		assertTrue("Is is the wrong type, it should be LiteralBodyBuilder",builder instanceof LiteralBodyBuilder);
		Map<String,Object>inputMap = new HashMap<String, Object>();
		inputMap.put("str", "bob");
		
		String xml = builder.build(inputMap).getAsString();
		
		assertEquals("XML should containe qualifed namespace for str",xml,"<ns1:str xmlns:ns1=\"http://testing.org\">bob</ns1:str>");
	}
	
	@Ignore("Integration test")
	@Test
	public void testUnwrappedSimple() throws Exception {
		BodyBuilder builder = createBuilder(WSDL_TEST_BASE+"TestServices-unwrapped.wsdl", "countString");
		
		assertTrue("Wrong type of builder, it should be Literal based",builder instanceof LiteralBodyBuilder);
		
		Map<String,Object> inputMap = new HashMap<String, Object>();
		inputMap.put("str", "12345");
		
		SOAPBodyElement body = builder.build(inputMap);
		
		assertEquals("Input element should be named str:","str",body.getNodeName());
		assertEquals("Value should be 12345:","12345",body.getFirstChild().getNextSibling().getNodeValue());
	}
	
	@Ignore("Integration test")
	@Test
	public void testUnwrappedArray() throws Exception {
		BodyBuilder builder = createBuilder(WSDL_TEST_BASE+"TestServices-unwrapped.wsdl", "countStringArray");
		
		assertTrue("Wrong type of builder, it should be Literal based",builder instanceof LiteralBodyBuilder);
		
		Map<String,Object> inputMap = new HashMap<String, Object>();
		inputMap.put("array", "<array><item>1</item><item>2</item><item>3</item></array>");
		
		SOAPBodyElement body = builder.build(inputMap);
		
		String xml = body.getAsString();
		assertEquals("Outer element should be named array. xml = "+xml,"array",body.getNodeName());
		
		Node itemElement = body.getFirstChild().getNextSibling();
		assertEquals("Array element should be named item. xml = "+xml,"item",itemElement.getNodeName());
		assertEquals("First Array element should have the value '1'. xml = "+xml,"1",itemElement.getFirstChild().getNodeValue());
	}
	
	@Ignore("Integration test")
	@Test 
	public void testOperationElementNameEUtils() throws Exception {
		BodyBuilder builder = createBuilder(WSDL_TEST_BASE+"eutils/eutils_lite.wsdl", "run_eInfo");

		assertTrue("Wrong type of builder, it should be Literal based",builder instanceof LiteralBodyBuilder);
		Map<String,Object> inputMap = new HashMap<String, Object>();
		inputMap.put("parameters",
		// Note: Don't use xmlns="" as it would also affect <parameters>
				// - which should not affect the namespace of the soap body
				// element. The element qname of the SOAPBodyElement should be
				// determined by the schema only
				"<parameters xmlns:e='http://www.ncbi.nlm.nih.gov/soap/eutils/einfo'>"
						+ "<e:db>database</e:db>" + "<e:tool>myTool</e:tool>"
						+ "<e:email>nobody@nowhere.net</e:email>"
						+ "</parameters>");
		SOAPBodyElement body = builder.build(inputMap);
		assertEquals("QName of SOAP body's element did not match expected qname ", 
				new QName("http://www.ncbi.nlm.nih.gov/soap/eutils/einfo", "eInfoRequest"), 
				body.getQName());
	}
	/*
	
	@Test 
	public void testOperationElementNameTAV744() throws Exception {
		URL tav744Url = getClass().getResource(
				"/net/sf/taverna/wsdl/parser/TAV-744/InstrumentService__.wsdl");
		
		BodyBuilder builder = createBuilder(tav744Url.toExternalForm(), "getList");

		assertTrue("Wrong type of builder, it should be Literal based",builder instanceof LiteralBodyBuilder);
		Map<String,Object> inputMap = new HashMap<String, Object>();
		// No inputs
		SOAPBodyElement body = builder.build(inputMap);
		assertEquals("QName of SOAP body's element did not match expected qname ", 
				new QName("http://InstrumentService.uniparthenope.it/InstrumentService", "GetListRequest"), 
				body.getQName());
	}
	*/
	
	@Ignore("Integration test")
	@Test
	public void testRPCLiteral() throws Exception {
		BodyBuilder builder = createBuilder(WSDL_TEST_BASE+"MyService-rpc-literal.wsdl", "countString");
		
		assertTrue("Wrong type of builder, it should be Literal based",builder instanceof LiteralBodyBuilder);
		
		Map<String,Object> inputMap = new HashMap<String, Object>();
		inputMap.put("str", "abcdef");
		
		SOAPBodyElement body = builder.build(inputMap);
		
		assertEquals("Outer element should be named countString","countString",body.getNodeName());
		Node strNode = body.getFirstChild();
		assertEquals("Inner element should be called 'str'","str",strNode.getNodeName());
		assertEquals("str content should be abcdef","abcdef",strNode.getFirstChild().getNextSibling().getNodeValue());
	}
	
	protected BodyBuilder createBuilder(String wsdl, String operation) throws Exception {
		WSDLParser parser = new WSDLParser(wsdl);
		return BodyBuilderFactory.instance().create(parser, operation, parser.getOperationInputParameters(operation));
	}
}
