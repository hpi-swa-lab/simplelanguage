package com.oracle.truffle.sl.runtime.valueobject;

import java.util.*;

public abstract class SLValueObject {

    // object size -> (index -> object size)
    private static Map<Shape, Map<Integer, Map<Shape, Integer>>> history = new HashMap<>();
    private static Map<Shape, Map<Integer, Set<Shape>>> transformations = new HashMap<>();

    protected Shape shape;

    public static SLValueObject newValueObject(Object... values) {
        List<Object> subValues = new ArrayList<>();

        Shape shape = Shape.directAccessOf(values.length);
        for (Object value : values) {
            subValues.add(value);
        }

        // Iterate over all fields and check if they can get inlined according to transformation list
        for (int i = 0; i < subValues.size();) {
            Object value = subValues.get(i);
            if (value instanceof SLValueObject) {
                SLValueObject valueObject = (SLValueObject) value;
                Shape subShape = valueObject.getShape();
                Map<Integer, Set<Shape>> transformation = transformations.get(shape);
                if (transformation != null && transformation.get(i).contains(subShape)) {
                    System.out.println("Inlined");
                    // Inline shape
                    shape.inlineShape(i, subShape);
                    // Inline object
                    subValues.remove(i);
                    subValues.addAll(i, valueObject.getAll());
                    // Reset inlining process to allow recursive inlining
                    i = 0;
                    continue;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }

        // Update history
        history.putIfAbsent(shape, new HashMap<>());
        Map<Integer, Map<Shape, Integer>> shapeHistory = history.get(shape);

        int i = 0;
        for (Object value : subValues) {
            if (value instanceof SLValueObject) {
                System.out.println("Updated history.");
                SLValueObject valueObject = (SLValueObject) value;
                Shape subShape = valueObject.getShape();
                shapeHistory.putIfAbsent(i, new HashMap<>());
                Map<Shape, Integer> indexHistory = shapeHistory.get(i);
                indexHistory.putIfAbsent(subShape, 0);
                int numObservations = indexHistory.get(subShape);
                indexHistory.put(subShape, numObservations + 1);
                
                shapeHistory.put(i, indexHistory);
                history.put(shape, shapeHistory);

                if (numObservations == 7) {
                    System.out.println("Added transformation.");
                    transformations.putIfAbsent(shape, new HashMap<>());
                    transformations.get(shape).putIfAbsent(i, new HashSet<>());
                    transformations.get(shape).get(i).add(subShape);
                }
            }
            i++;
        }

        return new SLNaryValueObject(subValues, shape);
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
