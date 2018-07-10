package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLLongConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLProfileConsCell;

@NodeInfo(shortName = "tail")
public abstract class SLTailBuiltin extends SLBuiltinNode {


    @Specialization
    public final Object tail(SLLongConsCell consCell) {
        return consCell.getTail();
    }

    @Specialization
    public final Object tail(SLProfileConsCell consCell) {
        return consCell.getTail();
    }

    @Specialization
    public final Object tail(SLConsCell consCell) {
        return consCell.getTail();
    }
}
