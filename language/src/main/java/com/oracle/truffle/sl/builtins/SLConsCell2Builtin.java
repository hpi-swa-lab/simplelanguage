package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell2;

/**
 * Built-in function to create a new generic cons cell, simply holding Objects.
 */
@NodeInfo(shortName = "cons2")
public abstract class SLConsCell2Builtin extends SLBuiltinNode {

    @Specialization
    public final SLConsCell newCell(Object head, Object tail) {
        return new SLConsCell2(head, tail);
    }
}
