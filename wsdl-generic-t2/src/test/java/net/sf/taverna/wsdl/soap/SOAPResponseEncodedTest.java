/*
 * Copyright (C) 2003 The University of Manchester 
 *
 * Modifications to the initial code base are copyright of their
 * respective authors, or their employers as appropriate.  Authorship
 * of the modifications may be determined from the ChangeLog placed at
 * the end of this file.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 ****************************************************************
 * Source code information
 * -----------------------
 * Filename           $RCSfile: SOAPResponseEncodedTest.java,v $
 * Revision           $Revision: 1.1 $
 * Release status     $State: Exp $
 * Last modified on   $Date: 2008-08-21 21:23:09 $
 *               by   $Author: tanw $
 * Created on 08-May-2006
 *****************************************************************/
package net.sf.taverna.wsdl.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.taverna.wsdl.parser.WSDLParser;
import net.sf.taverna.wsdl.testutils.LocationConstants;

import org.apache.axis.message.SOAPBodyElement;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;

public class SOAPResponseEncodedTest  implements LocationConstants {
	
	@Ignore("Integration test")
	@SuppressWarnings("unchecked")
	@Test
	public void testSimpleRPC() throws Exception {
		
		WSDLParser wsdlParser = new WSDLParser(WSDL_TEST_BASE+"ma.wsdl");

		String xml1 = "<ns1:whatGeneInStageResponse soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"urn:hgu.webservice.services\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><whatGeneInStageReturn soapenc:arrayType=\"ns2:GeneExpressedQueryShortDetails[0]\" xsi:type=\"soapenc:Array\" xmlns:ns2=\"http://SubmissionQuery.WSDLGenerated.hgu\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><agene xsi:type=\"string\">a gene</agene></whatGeneInStageReturn></ns1:whatGeneInStageResponse>";

		List<SOAPBodyElement> response = new ArrayList<SOAPBodyElement>();

		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(xml1.getBytes()));
		response.add(new SOAPBodyElement(doc.getDocumentElement()));

		SOAPResponseEncodedParser parser = new SOAPResponseEncodedParser(wsdlParser.getOperationOutputParameters("whatGeneInStage"));
		parser.setStripAttributes(true);

		Map outputMap = parser.parse(response);

		assertNotNull("no output map returned", outputMap);

		assertEquals("map should contain 1 element", 1, outputMap.size());

		Object result = outputMap.get("whatGeneInStageReturn");

		assertNotNull(
				"output map should have contained entry for 'whatGeneInStageReturn'",
				result);

		assertEquals("output data should be a string", String.class, result.getClass());

		assertEquals(
				"incorrect xml content in output",
				"<whatGeneInStageReturn><agene>a gene</agene></whatGeneInStageReturn>",
				result.toString());
	}
}
