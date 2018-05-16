package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLConsCell;
import com.oracle.truffle.sl.runtime.SLContext;

/**
 * Built-in function to create a new cons cell.
 */
@NodeInfo(shortName = "cons")
public abstract class SLConsCellBuiltin extends SLBuiltinNode {
	
	@CompilationFinal SLContext context;
	
	@Specialization
	public Object newCell(Object head, Object tail) {
		if (context != getContext()) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			context = getContext();
		}
		return new SLConsCell(head, tail);
	}
}
