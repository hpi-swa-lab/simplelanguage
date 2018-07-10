package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;

@NodeInfo(shortName = "tail")
public abstract class SLTailBuiltin extends SLBuiltinNode {

    @Specialization
    public final Object tail(SLConsCell consCell) {
        return consCell.getTail();
    }
}
