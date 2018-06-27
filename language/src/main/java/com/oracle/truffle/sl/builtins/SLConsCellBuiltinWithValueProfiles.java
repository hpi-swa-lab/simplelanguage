package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLProfileConsCell;

/**
 * Built-in function to create a new generic cons cell, simply holding Objects.
 * The types of the head are profiled in a specialization in {@link SLHeadBuiltin}
 */
@NodeInfo(shortName = "consWvp")
public abstract class SLConsCellBuiltinWithValueProfiles extends SLBuiltinNode {

    @Specialization
    public final SLProfileConsCell newCell(Object head, Object tail) {
        return new SLProfileConsCell(head, tail);
    }
}
