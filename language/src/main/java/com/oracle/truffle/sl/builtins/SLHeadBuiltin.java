package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;

@NodeInfo(shortName = "head")
public abstract class SLHeadBuiltin extends SLBuiltinNode {

    @Specialization
    public final Object head(SLConsCell consCell) {
        return consCell.getHead();
    }
}
