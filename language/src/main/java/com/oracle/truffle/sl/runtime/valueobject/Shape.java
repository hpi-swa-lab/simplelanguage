package com.oracle.truffle.sl.runtime.valueobject;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private final List<Shape> subShapes;

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
    }

    public int getNumFields() {
        return subShapes.size();
    }

}
