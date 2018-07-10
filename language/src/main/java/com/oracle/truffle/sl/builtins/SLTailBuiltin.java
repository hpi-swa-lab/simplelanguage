package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ValueProfile;
import com.oracle.truffle.sl.runtime.SLConsCell;
import com.oracle.truffle.sl.runtime.SLFuncConsCell;
import com.oracle.truffle.sl.runtime.SLLongConsCell;
import com.oracle.truffle.sl.runtime.SLProfileConsCell;

@NodeInfo(shortName = "tail")
public abstract class SLTailBuiltin extends SLBuiltinNode {


    @Specialization
    public final Object tail(SLFuncConsCell consCell) {
        return consCell.getTail();
    }

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
