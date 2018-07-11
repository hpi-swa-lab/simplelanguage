package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ValueProfile;
import com.oracle.truffle.sl.runtime.conscell.*;


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
    public final Object head(SLConsCell2 consCell) {
        return consCell.getHead();
    }

    @Specialization
    public final Object head(SLConsCell3 consCell) {
        return consCell.getHead();
    }

    @Specialization
    public final Object head(SLConsCell4 consCell) {
        return consCell.getHead();
    }
}
