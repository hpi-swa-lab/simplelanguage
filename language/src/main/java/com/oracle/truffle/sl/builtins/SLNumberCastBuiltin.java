package com.oracle.truffle.sl.builtins;

import java.math.BigInteger;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLBigNumber;
import com.oracle.truffle.sl.runtime.SLContext;

import static com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import static com.oracle.truffle.api.CompilerDirectives.transferToInterpreterAndInvalidate;

@NodeInfo(shortName = "num")
public abstract class SLNumberCastBuiltin extends SLBuiltinNode {
	
	@CompilationFinal
	SLContext context;
	
	
	@Specialization
	public SLBigNumber convertFrom(String numberAsString) {
		if (context != getContext()) {
			transferToInterpreterAndInvalidate();
			context = getContext();
		}
		
		return new SLBigNumber(new BigInteger(numberAsString));
	}
	
}
