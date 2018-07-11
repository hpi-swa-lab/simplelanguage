package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell2;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell3;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell4;

/**
 * Built-in function to create a new generic cons cell, simply holding Objects.
 */
@NodeInfo(shortName = "cons")
public abstract class SLConsCellBuiltin extends SLBuiltinNode {

    @Specialization
    public final SLConsCell4 newCell(Object head, SLConsCell3 tail) {
        return new SLConsCell4(head, tail);
    }

    @Specialization
    public final SLConsCell3 newCell(Object head, SLConsCell2 tail) {
        return new SLConsCell3(head, tail);
    }

    @Fallback
    public final SLConsCell2 newCell(Object head, Object tail) {
        return new SLConsCell2(head, tail);
    }
}
