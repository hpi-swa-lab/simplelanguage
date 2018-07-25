package com.oracle.truffle.sl.runtime.valueobject;

import java.util.*;

public abstract class SLValueObject {

    // object size -> (index -> object size)
    private static Map<Shape, Map<Integer, Map<Shape, Integer>>> history = new HashMap<>();
    private static Map<Shape, Map<Integer, Set<Shape>>> transformations = new HashMap<>();

    protected Shape shape;

    public static SLValueObject newValueObject(Object... values) {

        Shape shape = Shape.directAccessOf(values.length);
        List<Object> subValues = Arrays.asList(values);

        boolean inliningHappened;
        // always do one pass over elements, if inlining happened, do another to check for recursive inlining
        do {
            inliningHappened = false;
            List<Object> processedValues = new ArrayList<>();

            // Iterate over all fields and check if they can get inlined according to transformation list
            for (int i = 0; i < subValues.size(); i++) {
                Object value = subValues.get(i);
                if (value instanceof SLValueObject) {
                    SLValueObject valueObject = (SLValueObject) value;
                    Shape subShape = valueObject.getShape();
                    Map<Integer, Set<Shape>> transformation = transformations.get(shape);
                    if (transformation != null && transformation.get(i).contains(subShape)) {
                        System.out.println("Inlined");
                        shape.inlineShape(subShape);
                        processedValues.addAll(valueObject.getAll());
                        inliningHappened = true;
                    } else {
                        processedValues.add(valueObject);
                    }
                } else {
                    processedValues.add(value);
                }
            }

            subValues = processedValues;
        } while (inliningHappened);


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
                int numObservations = indexHistory.getOrDefault(subShape, 0);
                indexHistory.put(subShape, numObservations + 1);

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
