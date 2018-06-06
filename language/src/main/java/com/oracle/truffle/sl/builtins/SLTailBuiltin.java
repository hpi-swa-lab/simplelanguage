package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLConsCell;

@NodeInfo(shortName = "tail")
public abstract class SLTailBuiltin extends SLBuiltinNode {
	
	@Specialization
	public Object tail(SLConsCell consCell) {
		return consCell.getTail();
	}
}
