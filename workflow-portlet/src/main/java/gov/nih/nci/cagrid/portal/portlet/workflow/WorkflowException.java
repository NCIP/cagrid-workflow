/**
*============================================================================
*  Copyright The Ohio State University Research Foundation, The University of Chicago - 
*    Argonne National Laboratory, Emory University, SemanticBits LLC, and 
*	Ekagra Software Technologies Ltd.
*
*  Distributed under the OSI-approved BSD 3-Clause License.
*  See http://ncip.github.com/cagrid-workflow/LICENSE.txt for details.
*============================================================================
**/
package gov.nih.nci.cagrid.portal.portlet.workflow;

import org.springframework.core.NestedCheckedException;

public class WorkflowException extends NestedCheckedException {
	private static final long serialVersionUID = 1L;

	public WorkflowException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkflowException(String message) {
		super(message);
	}
public WorkflowException(Throwable cause) {
	super(cause.getMessage(), cause);
}
}
