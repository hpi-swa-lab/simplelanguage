package com.oracle.truffle.sl.runtime.valueobject;

import com.oracle.truffle.api.CompilerDirectives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SLNaryValueObject extends SLValueObject {

    private final List<Object> values;
    private final List<Class<?>> classes;

    public SLNaryValueObject(List<Object> values) {
        this.values = values;
        this.classes = new ArrayList<>();
        this.shape = Shape.directAccessOf(values.size());

        for (Object value : values) {
            this.classes.add(value.getClass());
        }
    }

    @Override
    protected Collection<Object> getAll() {
        return this.values;
    }

    @Override
    public Object get(int index) {
        if (index > shape.getNumFields()) {
            throw new RuntimeException("Index " + index + " out of bounds for SLValueObject2");
        }

        List<Integer> range = shape.getSubshapeRange(index);
        int begin = range.get(0);
        int end = range.get(1);

        if (begin == end) {
            return CompilerDirectives.castExact(values.get(begin), classes.get(begin));
        }
        else {
            List<Object> subValues = new ArrayList<>();

            for (int i = begin; i <= end; i++) {
                subValues.add(this.values.get(i));
            }

            return new SLNaryValueObject(subValues);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < shape.getNumFields(); i++) {
            builder.append(get(i).toString());
            if (i != shape.getNumFields() - 1) {
                builder.append(", ");
            }
        }

        builder.append(']');
        return builder.toString();
    }
}
