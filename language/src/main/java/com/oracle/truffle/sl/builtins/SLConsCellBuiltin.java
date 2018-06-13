package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.SLConsCell;
import com.oracle.truffle.sl.runtime.SLFuncConsCell;
import com.oracle.truffle.sl.runtime.SLFunction;

/**
 * Built-in function to create a new cons cell.
 */
@NodeInfo(shortName = "cons")
public abstract class SLConsCellBuiltin extends SLBuiltinNode {

    @Specialization
    public final SLFuncConsCell newCell(SLFunction head, Object tail) {
        return new SLFuncConsCell(head, tail);
    }

    @Specialization(guards = "!isFunc(head)")
    public final SLConsCell newCell(Object head, Object tail) {
        return new SLConsCell(head, tail);
    }

    protected boolean isFunc(Object head) {
        return head instanceof SLFunction;
    }
}
