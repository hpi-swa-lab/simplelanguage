package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLConsCell;
import com.oracle.truffle.sl.runtime.SLFuncConsCell;
import com.oracle.truffle.sl.runtime.SLFunction;

@NodeInfo(shortName = "head")
public abstract class SLHeadBuiltin extends SLBuiltinNode {

    @Specialization
    public final SLFunction head(SLFuncConsCell consCell) {
        return consCell.getHead();
    }

    @Specialization
    public final Object head(SLConsCell consCell) {
        return consCell.getHead();
    }
}
