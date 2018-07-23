package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.valueobject.SLValueObject2;
import com.oracle.truffle.sl.runtime.valueobject.SLValueObject;

/**
 * Built-in function to create a new generic value object.
 */
@NodeInfo(shortName = "vnew")
public abstract class SLValueObjectBuiltin extends SLBuiltinNode {

//    @Specialization
//    public final SLValueObject new2(Object value1, Object value2) {
//        return SLValueObject.newValueObject(value1, value2);
//    }

    @Specialization
    public final SLValueObject newValueObject(Object value1, Object value2, Object value3) {
        return SLValueObject.newValueObject(value1, value2, value3);
    }
}
