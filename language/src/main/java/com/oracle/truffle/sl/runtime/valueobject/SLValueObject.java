package com.oracle.truffle.sl.runtime.valueobject;

import java.util.*;

public abstract class SLValueObject {

    // object size -> (index -> object size)
    private static Map<Integer, Map<Integer, Integer>> transformations = new HashMap<>();

    static {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 3);
        transformations.put(3, m);
    }

    private Shape shape;

    public static SLValueObject newValueObject(Object... values) {
        List<Shape> subShapes = new ArrayList<>();
        List<Object> subValues = new ArrayList<>();

        int i = 0;
        for (Object value : values) {
            if (value instanceof SLValueObject) {
                SLValueObject valueObject = (SLValueObject) value;
                int numFields = valueObject.getNumFields();
                Map<Integer, Integer> transformation = transformations.get(values.length);
                if (transformation != null && transformation.get(i) == numFields) {
                    // TODO mehrere object sizes an einem index?
                    subShapes.add(i, valueObject.getShape());
                    subValues.addAll(valueObject.getAll());
                } else {
                    subShapes.add(i, null);
                    // TODO history
                    Map<Integer, Integer> newTransformation = new HashMap<>();
                    newTransformation.put(i, numFields);
                    transformations.put(values.length, transformation);

                    subValues.add(value);
                    // TODO überschreibt existierende
                }
            } else {
                subShapes.add(i, null);
                subValues.add(value);
            }
            i++;
        }

        SLValueObject newValueObject;

        if (subValues.size() == 2) {
            newValueObject = new SLValueObject2(subValues.get(0), subValues.get(1));
        } else if (subValues.size() == 3) {
            newValueObject = new SLValueObject3(subValues.get(0), subValues.get(1), subValues.get(2));
        // TODO 4
        } else if (subValues.size() == 5) {
            newValueObject = new SLValueObject5(subValues.get(0), subValues.get(1), subValues.get(2), subValues.get(3), subValues.get(4));
        } else {
            throw new RuntimeException("äh");
        }
        newValueObject.setShape(Shape.of(subShapes));
        return newValueObject;
    }

    private void setShape(Shape shape) {
        this.shape = shape;
    }

    private Shape getShape() {
        return shape;
    }


    public int getNumFields() {
        return shape.getNumFields();
    }

    protected abstract Collection<Object> getAll();

    public abstract Object get(int index);
}
