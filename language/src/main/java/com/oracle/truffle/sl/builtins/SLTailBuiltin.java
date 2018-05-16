package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLContext;
import com.oracle.truffle.sl.runtime.SLConsCell;

@NodeInfo(shortName = "tail")
public abstract class SLTailBuiltin extends SLBuiltinNode {
	
	@CompilationFinal SLContext context;
	
	@Specialization
	public Object tail(SLConsCell consCell) {
		if (context != getContext()) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			context = getContext();
		}
		return consCell.getTail();
	}
}
