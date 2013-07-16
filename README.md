Welcome to the caGrid Workflow Project!
=====================================

caGrid is the underlying service-oriented infrastructure that supports the National Cancer Informatics Program (NCIP). 
Driven primarily by scientific use cases from the cancer research community, it caGrid provides the necessary core 
infrastructure to facilitate interoperability.

caGrid provides the technology that enables collaborating institutions to share information and analytical resources 
efficiently and securely, while also allowing investigators to easily contribute to and leverage the resources of a 
national-scale, multi-institutional environment.

caGrid Workflow is an Open Source project written in Java, and makes use of caGrid core services and the Taverna
workbench to enable orchestration of complex workflows; it employs three components:
 * [Taverna Workbench] (http://www.taverna.org.uk/documentation/taverna-2-x/) - Taverna is an open source and domain-independent Workflow Management System – a suite of tools used to design and execute scientific workflows and aid in silico experimentation.
 * [caGrid Plug-in for Taverna Workbench] (http://www.taverna.org.uk/introduction/related-projects/cagrid/) - Enables Taverna Workbench users to securely connect to caGrid services and build workflows between them
 * [caGrid Workflow Service] (http://www.cagrid.org/display/workflow14/Home) - is a caGrid WSRF service that accepts Taverna SCUFL workflows, securely orchestrates interactions between services, and returns the requested data set to the caller

caGrid Workflow is distributed under the BSD 3-Clause License. Please see the NOTICE and LICENSE files for details.

You will find more details about cagrid-workflow in the following links:

 * [caGrid.org Wiki] (https://wiki.cagrid.org)
 * [How to Create a Workflow Using Taverna] (http://www.cagrid.org/display/knowledgebase/How+to+Create+CaGrid+Workflow+Using+Taverna)
 * [Code Repository] (https://github.com/NCIP/cagrid-workflow)
 * [Issue Tracker] (https://tracker.nci.nih.gov/browse/CAGRID)
 * [Mailing List] (https://list.nih.gov/cgi-bin/wa.exe?A0=cagrid_users-l)

Please join us in further developing and improving caGrid Workflow.
