package com.oracle.truffle.sl.runtime.valueobject;

import com.oracle.truffle.api.CompilerDirectives;

public class SL2ValueObject extends SLValueObject {

    private final Object value1;
    private final Object value2;

    private final Class<?> class1;
    private final Class<?> class2;


    public SL2ValueObject(Object value1, Object value2) {
        super(2);

        this.value1 = value1;
        this.value2 = value2;
        this.class1 = value1.getClass();
        this.class2 = value2.getClass();
    }

    @Override
    public Object get(int index) {
        if (index == 1) {
            return CompilerDirectives.castExact(value1, class1);
        } else if (index == 2) {
            return CompilerDirectives.castExact(value2, class2);
        }

        throw new RuntimeException("Index " + index + " out of bounds for SL2ValueObject");
    }
}
