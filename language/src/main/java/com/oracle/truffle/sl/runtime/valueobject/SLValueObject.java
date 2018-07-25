package com.oracle.truffle.sl.runtime.valueobject;

import java.util.*;

public abstract class SLValueObject {

    private static boolean allowInlining = true;

    // object size -> (index -> object size)
    private static Map<SLShape, Map<Integer, Map<SLShape, Integer>>> history = new HashMap<>();
    private static Map<SLShape, Map<Integer, Set<SLShape>>> transformations = new HashMap<>();

    protected SLShape shape;

    public static SLValueObject newValueObject(Object... values) {

        SLShape shape = SLShape.directAccessOf(values.length);
        List<Object> subValues = Arrays.asList(values);


        if (!allowInlining) {
            return new SLNaryValueObject(subValues, shape);
        }

        boolean inliningHappened;
        // always do one pass over elements, if inlining happened, do another to check for recursive inlining
        do {
            inliningHappened = false;
            List<Object> processedValues = new ArrayList<>();

            // Iterate over all fields and check if they can get inlined according to transformation list
            for (int i = 0; i < subValues.size(); i++) {
                Object value = subValues.get(i);
                if (value instanceof SLValueObject && shape.getDepth() < 7) {
                    SLValueObject valueObject = (SLValueObject) value;
                    SLShape subShape = valueObject.getShape();
                    Map<Integer, Set<SLShape>> transformation = transformations.get(shape);
                    if (transformation != null && transformation.get(i) != null && transformation.get(i).contains(subShape)) {
                        // System.out.println("Inlined");
                        shape.inlineShape(i, subShape);
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
        Map<Integer, Map<SLShape, Integer>> shapeHistory = history.get(shape);

        int i = 0;
        for (Object value : subValues) {
            if (value instanceof SLValueObject) {
                // System.out.println("Updated history.");
                SLValueObject valueObject = (SLValueObject) value;
                SLShape subShape = valueObject.getShape();
                shapeHistory.putIfAbsent(i, new HashMap<>());
                Map<SLShape, Integer> indexHistory = shapeHistory.get(i);
                int numObservations = indexHistory.getOrDefault(subShape, 0);
                indexHistory.put(subShape, numObservations + 1);

                if (numObservations == 7) {
                    // System.out.println("Added transformation.");
                    transformations.putIfAbsent(shape, new HashMap<>());
                    transformations.get(shape).putIfAbsent(i, new HashSet<>());
                    transformations.get(shape).get(i).add(subShape);
                }
            }
            i++;
        }

        return new SLNaryValueObject(subValues, shape);
    }

    private void setShape(SLShape shape) {
        this.shape = shape;
    }

    private SLShape getShape() {
        return shape;
    }


    public int getNumFields() {
        return shape.getNumFields();
    }

    protected abstract Collection<Object> getAll();

    public abstract Object get(int index);
}
