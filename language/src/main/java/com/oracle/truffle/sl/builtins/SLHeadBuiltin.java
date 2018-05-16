package com.oracle.truffle.sl.builtins;

import java.util.ArrayList;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLContext;

@NodeInfo(shortName = "head")
public abstract class SLHeadBuiltin extends SLBuiltinNode {
	
	@CompilationFinal SLContext context;
	
	@Specialization
	public Object head(SLConsCell consCell) {
		if (context != getContext()) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			context = getContext();
		}
		return consCell.getHead();
	}
}
