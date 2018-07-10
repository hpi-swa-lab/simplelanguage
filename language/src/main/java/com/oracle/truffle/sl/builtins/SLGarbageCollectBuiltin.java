package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * Built-in function to call System.gc()
 */
@NodeInfo(shortName = "gc")
public abstract class SLGarbageCollectBuiltin extends SLBuiltinNode {

    @Specialization
    public final Object doGc() {
        System.gc();
        return null;
    }
}
