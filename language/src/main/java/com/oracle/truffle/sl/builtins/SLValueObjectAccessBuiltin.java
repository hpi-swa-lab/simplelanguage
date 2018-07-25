package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.valueobject.SLValueObject;

@NodeInfo(shortName = "get")
public abstract class SLValueObjectAccessBuiltin extends SLBuiltinNode {
    @Specialization
    public final Object get(SLValueObject valueObject, Long index) {
        return valueObject.get(index.intValue());
    }
}
