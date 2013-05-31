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
package net.sf.taverna.wsdl.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * A TypeDescriptor that specifically describes a complex type
 * 
 */
public class ComplexTypeDescriptor extends TypeDescriptor {
	private List<TypeDescriptor> elements = new ArrayList<TypeDescriptor>();

	public List<TypeDescriptor> getElements() {
		return elements;
	}

	public void setElements(List<TypeDescriptor> elements) {
		this.elements = elements;
	}
	
	public TypeDescriptor elementForName(String name) {
		TypeDescriptor result=null;
		for (TypeDescriptor desc : getElements()) {
			if (desc.getName().equals(name)) {
				result=desc;
				break;
			}
		}
		return result;
	}
}
