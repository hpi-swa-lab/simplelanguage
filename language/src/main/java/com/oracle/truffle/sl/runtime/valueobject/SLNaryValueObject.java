package com.oracle.truffle.sl.runtime.valueobject;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.sl.runtime.valueobject.Shape.Range;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SLNaryValueObject extends SLValueObject {

    private final List<Object> values;
    private final List<Class<?>> classes;

    SLNaryValueObject(List<Object> values, Shape shape) {
        this.values = values;
        this.classes = values.stream().map(Object::getClass).collect(toList());
        this.shape = shape;
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

        Range range = shape.getSubshapeRange(index);
        int begin = range.getBegin();
        int end = range.getEnd();

        if (begin == end) {
            // Direct access of object
            return CompilerDirectives.castExact(values.get(begin), classes.get(begin));
        } else {
            // Reify inlined object
            List<Object> subValues = IntStream.range(begin, end + 1).mapToObj(values::get).collect(toList());
            return new SLNaryValueObject(subValues, Shape.directAccessOf(subValues.size()));
        }
    }

    @Override
    public String toString() {
        return IntStream.range(0, shape.getNumFields())
                .mapToObj(i -> get(i).toString())
                .collect(joining(", ", "[", "]"));
    }
}
