package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell2;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell3;
import com.oracle.truffle.sl.runtime.conscell.SLConsCell4;

/**
 * Built-in function to create a new cons cell.
 */
@NodeInfo(shortName = "cons")
public abstract class SLConsCellBuiltin extends SLBuiltinNode {

    @Specialization
    public final SLConsCell newCell(Object head, Object tail) {
        if (tail instanceof SLConsCell2) {
            return new SLConsCell3(head, (SLConsCell2)tail);
        }
        if (tail instanceof SLConsCell3) {
            return new SLConsCell4(head, (SLConsCell3)tail);
        }
        return new SLConsCell2(head, tail);
    }
}
