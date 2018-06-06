package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLBigNumber;

import java.math.BigInteger;

@NodeInfo(shortName = "num")
public abstract class SLNumberCastBuiltin extends SLBuiltinNode {

	@Specialization
	public SLBigNumber convertFrom(String numberAsString) {
		return new SLBigNumber(new BigInteger(numberAsString));
	}
	
}
