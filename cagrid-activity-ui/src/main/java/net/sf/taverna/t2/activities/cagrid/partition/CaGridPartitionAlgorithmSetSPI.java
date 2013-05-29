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
package net.sf.taverna.t2.activities.cagrid.partition;

import net.sf.taverna.t2.partition.ActivityPartitionAlgorithmSet;
import net.sf.taverna.t2.partition.algorithms.LiteralValuePartitionAlgorithm;

public class CaGridPartitionAlgorithmSetSPI extends ActivityPartitionAlgorithmSet {

	public CaGridPartitionAlgorithmSetSPI() {
		partitionAlgorithms.add(new LiteralValuePartitionAlgorithm("operation"));
		partitionAlgorithms.add(new LiteralValuePartitionAlgorithm("use"));
		partitionAlgorithms.add(new LiteralValuePartitionAlgorithm("style"));
		partitionAlgorithms.add(new LiteralValuePartitionAlgorithm("url"));
	}
}
