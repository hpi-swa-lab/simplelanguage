package com.oracle.truffle.sl.runtime.valueobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shape {

    private final List<Shape> subShapes;
    private final List<Integer> shapeIndices;

    public static Shape of(List<Shape> subShapes) {
        return new Shape(subShapes);
    }

    public static Shape directAccessOf(int numElements) {
        List<Shape> nullElements = new ArrayList<>();
        for (int i = 0; i < numElements; i++) {
            nullElements.add(null);
        }
        return new Shape(nullElements);
    }

    private Shape(List<Shape> subShapes) {
        this.subShapes = subShapes;
        this.shapeIndices = new ArrayList<>();

        int curIndex = 0;
        for (Shape shape : subShapes) {
            this.shapeIndices.add(curIndex);
            if (shape == null) {
                curIndex += 1;
            }
            else {
                curIndex += shape.getNumPrimitives();
            }
        }
        // add number of total primitves at end
        this.shapeIndices.add(curIndex);
    }

    private int getNumPrimitives() {
        return shapeIndices.get(shapeIndices.size() - 1);
    }

    public List<Integer> getSubshapeRange(int index) {
        return Arrays.asList(shapeIndices.get(index), shapeIndices.get(index + 1) - 1); 
    }

    public int getNumFields() {
        return subShapes.size();
    }
}
