package com.oracle.truffle.sl.runtime.valueobject;

import com.oracle.truffle.api.CompilerDirectives;

import java.util.Arrays;
import java.util.Collection;

public class SLValueObject5 extends SLValueObject {

    private final Object value1;
    private final Object value2;
    private final Object value3;
    private final Object value4;
    private final Object value5;

    private final Class<?> class1;
    private final Class<?> class2;
    private final Class<?> class3;
    private final Class<?> class4;
    private final Class<?> class5;


    public SLValueObject5(Object value1, Object value2, Object value3, Object value4, Object value5) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.class1 = value1.getClass();
        this.class2 = value2.getClass();
        this.class3 = value3.getClass();
        this.class4 = value4.getClass();
        this.class5 = value5.getClass();
    }

    @Override
    protected Collection<Object> getAll() {
        return Arrays.asList(value1, value2, value3);
    }

    @Override
    public Object get(int index) {
        if (index == 0) {
            return CompilerDirectives.castExact(value1, class1);
        } else if (index == 1) {
            return CompilerDirectives.castExact(value2, class2);
        } else if (index == 2) {
            return CompilerDirectives.castExact(value3, class3);
        } else if (index == 3) {
            return CompilerDirectives.castExact(value4, class4);
        } else if (index == 4) {
            return CompilerDirectives.castExact(value5, class5);
        }

        throw new RuntimeException("Index " + index + " out of bounds for SLValueObject2");
    }

    @Override
    public String toString() {
        return "[" + value1 + "," + value2 + "," + value3 + "," + value4 + "," + value5 + "]";
    }
}
