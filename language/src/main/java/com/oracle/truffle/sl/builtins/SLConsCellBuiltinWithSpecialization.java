package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell2;
import com.oracle.truffle.sl.runtime.conscell.SLLongConsCell;

/**
 * Built-in function to create a new cons cell, specialized for SLFunctions.
 * SLFunctions are elements like "E" and "F" we use to fill the cons lists.
 */
@NodeInfo(shortName = "consWs")
public abstract class SLConsCellBuiltinWithSpecialization extends SLBuiltinNode {

    @Specialization
    public final SLLongConsCell newCell(long head, Object tail) {
        return new SLLongConsCell(head, tail);
    }

    @Specialization(guards = "!isLong(head)")
    public final SLConsCell newCell(Object head, Object tail) {
        return new SLConsCell2(head, tail);
    }

    protected boolean isLong(Object head) {
        return head instanceof Long;
    }
}
