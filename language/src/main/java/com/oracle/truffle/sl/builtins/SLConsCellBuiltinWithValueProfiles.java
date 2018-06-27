package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ValueProfile;
import com.oracle.truffle.sl.runtime.SLConsCell;

/**
 * Built-in function to create a new generic cons cell, simply holding Objects.
 * The value types are profiled using ValueProfiles.
 */
@NodeInfo(shortName = "consWvp")
public abstract class SLConsCellBuiltinWithValueProfiles extends SLBuiltinNode {

    ValueProfile valueProfile = ValueProfile.createClassProfile();

    @Specialization
    public final SLConsCell newCell(Object head, Object tail) {
        return new SLConsCell(valueProfile.profile(head), tail);
    }
}
