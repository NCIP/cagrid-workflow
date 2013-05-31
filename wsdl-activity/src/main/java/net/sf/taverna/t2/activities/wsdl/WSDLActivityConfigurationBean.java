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
package net.sf.taverna.t2.activities.wsdl;

/**
 * A standard Java Bean that provides the details required to configure a WSDLActivity.
 * <p>
 * This contains details about the WSDL and the Operation that the WSDLActivity is intended to invoke.
 * </p>
 * @author Stuart Owen
 */
public class WSDLActivityConfigurationBean {
    private String wsdl;
    private String operation;
    private String securityProfileString;
    
    /** Creates a new instance of WSDLActivityConfigurationBean */
    public WSDLActivityConfigurationBean() {
    }

    public String getWsdl() {
        return wsdl;
    }

    public void setWsdl(String wsdl) {
        this.wsdl = wsdl;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

	public String getSecurityProfileString() {
		return securityProfileString;
	}

	public void setSecurityProfileString(String securityProfileString) {
		this.securityProfileString = securityProfileString;
	}
}
