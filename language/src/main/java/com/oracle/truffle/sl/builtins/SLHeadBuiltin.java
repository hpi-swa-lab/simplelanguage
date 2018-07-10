package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ValueProfile;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLLongConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLProfileConsCell;


@NodeInfo(shortName = "head")
public abstract class SLHeadBuiltin extends SLBuiltinNode {

    private final ValueProfile valueProfile = ValueProfile.createClassProfile();

    @Specialization
    public final long head(SLLongConsCell consCell) {
        return consCell.getHead();
    }

    @Specialization
    public final Object head(SLProfileConsCell consCell) {
        return valueProfile.profile(consCell.getHead());
    }

    @Specialization
    public final Object head(SLConsCell consCell) {
        return consCell.getHead();
    }
}
