package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.*;

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
    public final Object tail(SLConsCell2 consCell) {
        return consCell.getTail();
    }

    @Specialization
    public final SLConsCell2 tail(SLConsCell3 consCell) {
        return consCell.getTail();
    }

    @Specialization
    public final SLConsCell2 tail(SLConsCell4 consCell) {
        return consCell.getTail();
    }
}
