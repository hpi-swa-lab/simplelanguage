package com.oracle.truffle.sl.runtime.valueobject;

import com.oracle.truffle.api.CompilerDirectives;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SLNaryValueObject extends SLValueObject {

    private final List<Object> values;
    private final List<Class<?>> classes;

    SLNaryValueObject(List<Object> values, SLShape shape) {
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

        if (!shape.isSubshapeAt(index)) {
            // Direct access of object
            int directAccessIndex = shape.getObjectStorageStart(index);
            return CompilerDirectives.castExact(values.get(directAccessIndex), classes.get(directAccessIndex));
        } else {
            // Reify inlined object
            SLShape subshape = shape.getSubshape(index);

            List<Object> subValues = values.subList(shape.getObjectStorageStart(index), shape.getObjectStorageStart(index + 1));

            return new SLNaryValueObject(subValues, subshape);
        }
    }

    @Override
    public String toString() {
        return IntStream.range(0, shape.getNumFields())
                .mapToObj(i -> get(i).toString())
                .collect(joining(", ", "[", "]"));
    }
}
