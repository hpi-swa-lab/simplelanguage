package com.oracle.truffle.sl.runtime.valueobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Shape {

    private final List<Shape> subShapes;
    private final List<Integer> shapeIndices;

    public static Shape of(List<Shape> subShapes) {
        return new Shape(subShapes);
    }

    static Shape directAccessOf(int numElements) {
        List<Shape> nullElements = IntStream.range(0, numElements).mapToObj(i -> (Shape) null).collect(toList());
        return new Shape(nullElements);
    }

    private Shape(List<Shape> subShapes) {
        this.subShapes = subShapes;
        this.shapeIndices = new ArrayList<>();
        calculateIndices();
    }

    private void calculateIndices() {
        this.shapeIndices.clear();

        int curIndex = 0;
        for (Shape shape : subShapes) {
            this.shapeIndices.add(curIndex);
            if (shape == null) {
                curIndex += 1;
            } else {
                curIndex += shape.getNumPrimitives();
            }
        }
        // add number of total primitves at end
        this.shapeIndices.add(curIndex);
    }

    private int getNumPrimitives() {
        return shapeIndices.get(shapeIndices.size() - 1);
    }

    Range getSubshapeRange(int index) {
        return new Range(shapeIndices.get(index), shapeIndices.get(index + 1) - 1);
    }

    int getNumFields() {
        return subShapes.size();
    }

    void inlineShape(Shape newSubShape) {
        int i = 0;
        for (Shape subShape : subShapes) {
            if (i == 0 && subShape == null) {
                subShapes.set(i, newSubShape);
            } else if (subShape != null && subShape.getNumFields() < i) {
                // TODO: handle setting subshape of child
            }

            i++;
        }
        calculateIndices();
    }

    /**
     * Range with inclusive begin and end
     */
    class Range {
        private final int begin;
        private final int end;

        Range(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        int getBegin() {
            return begin;
        }

        int getEnd() {
            return end;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(subShapes, shape.subShapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subShapes);
    }
}
