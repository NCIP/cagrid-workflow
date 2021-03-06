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
package net.sf.taverna.t2.activities.cagrid.actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import net.sf.taverna.t2.activities.wsdl.WSDLActivity;
import net.sf.taverna.t2.activities.wsdl.WSDLActivityConfigurationBean;
import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class CaGridActivityConfigureAction extends ActivityConfigurationAction<WSDLActivity,WSDLActivityConfigurationBean> {

	private final Frame owner;
	private static Logger logger = Logger
			.getLogger(CaGridActivityConfigureAction.class);

	public CaGridActivityConfigureAction(WSDLActivity activity,Frame owner) {
		super(activity);
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		//temporily disabled due to raven issues. 
//		WSDLActivityConfigurationBean bean = getActivity().getConfiguration();
//			
//		WSSecurityProfileChooser wsSecurityProfileChooser = new WSSecurityProfileChooser(owner);
//		if (wsSecurityProfileChooser.isInitialised()) {
//			wsSecurityProfileChooser.setVisible(true);
//		}
//		
//		WSSecurityProfile wsSecurityProfile = wsSecurityProfileChooser.getWSSecurityProfile();
//		String profileString;
//		if (wsSecurityProfile != null) { // user did not cancel
//			profileString = wsSecurityProfile.getWSSecurityProfileString();
//			logger.info("WSSecurityProfile string read as:"+profileString);
//			bean.setSecurityProfileString(profileString);
//			configureActivity(bean);
//		}
		
	}

}
