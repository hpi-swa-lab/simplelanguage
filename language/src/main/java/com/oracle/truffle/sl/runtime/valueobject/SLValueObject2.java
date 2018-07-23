package com.oracle.truffle.sl.runtime.valueobject;

import com.oracle.truffle.api.CompilerDirectives;

import java.util.Arrays;
import java.util.Collection;

public class SLValueObject2 extends SLValueObject {

    private final Object value1;
    private final Object value2;

    private final Class<?> class1;
    private final Class<?> class2;


    public SLValueObject2(Object value1, Object value2) {
        this.value1 = value1;
        this.value2 = value2;
        this.class1 = value1.getClass();
        this.class2 = value2.getClass();
    }

    @Override
    protected Collection<Object> getAll() {
        return Arrays.asList(value1, value2);
    }

    @Override
    public Object get(int index) {
        if (index == 1) {
            return CompilerDirectives.castExact(value1, class1);
        } else if (index == 2) {
            return CompilerDirectives.castExact(value2, class2);
        }

        throw new RuntimeException("Index " + index + " out of bounds for SLValueObject2");
    }
}
