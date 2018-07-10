package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Property;
import com.oracle.truffle.api.object.Shape;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SLShapeWrapper {

    public static SLShapeWrapper getWrapperForShape(Shape shape) {
        if (!wrapperMap.containsKey(shape)) {
            wrapperMap.put(shape, new SLShapeWrapper(shape));
        }

        return wrapperMap.get(shape);
    }

    public static void observeObject(DynamicObject object) {
        SLShapeWrapper wrapper = SLShapeWrapper.getWrapperForShape(object.getShape());

        int i = 0;
        for (Property objectProperty : object.getShape().getProperties()) {
            Object value = objectProperty.get(object, false);
            if (value instanceof DynamicObject) {
                DynamicObject subObject = (DynamicObject) value;
                wrapper.observeSubshape(i, subObject.getShape());
            }

            i++;
        }
    }

    public static void optimizeObject(DynamicObject object) {
        Shape shape = object.getShape();
        SLShapeWrapper wrapper = SLShapeWrapper.getWrapperForShape(shape);

        int i = 0;
        for (Property objectProperty : object.getShape().getProperties()) {
            Object value = objectProperty.get(object, false);
            if (value instanceof DynamicObject) {
                DynamicObject subObject = (DynamicObject) value;
                // TODO: find out original shape of subobject and recursively inline if possible
                if (!(objectProperty.getKey() instanceof String)) {
                    throw new AssertionError("Shape key is not a string.");
                }
                String key = (String) objectProperty.getKey();

                if (wrapper.isTransformation(i, subObject.getShape())) {
                    object.delete(key);
                    inlineSubobject(object, subObject, key);
                }
            }

            i++;
        }
    }

    public static void inlineSubobject(DynamicObject object, DynamicObject subobject, String inlinedPropertyName) {
        for (Property subobjectProperty : subobject.getShape().getProperties()) {
            if (!(subobjectProperty.getKey() instanceof String)) {
                throw new AssertionError("Shape key is not a string.");
            }
            String key = inlinedPropertyName + "_" + (String) subobjectProperty.getKey();
            Object value = subobjectProperty.get(subobject, false);
            object.define(key, value);
        }
    }

    // public static Shape inlineSubshape(Shape shape, Shape subshape, Integer position, String propertyName) {
    //     for (Property property : subshape.getProperties()) {
    //         if (!(property.getKey() instanceof String)) {
    //             throw new AssertionError("Shape key is not a string.");
    //         }
    //         String key = propertyName + "_" + (String)property.getKey();
    //         Shape.Allocator allocator = shape.allocator();
    //         shape = shape.addProperty(Property.create(key, allocator.locationForType(Object.class), 0));
    //     }

    //     System.out.println("Inlining result:\n" + shape.toString());
    //     return shape;
    // }

    private static Map<Shape, SLShapeWrapper> wrapperMap = new HashMap<>();

    private Shape shape;
    private Map<Integer, Map<Shape, Integer>> observations;
    private Map<Integer, Set<Shape>> transformations;

    private SLShapeWrapper(Shape _shape) {
        shape = _shape;
        observations = new HashMap<>();
        transformations = new HashMap<>();
    }

    public boolean isTransformation(Integer position, Shape subshape) {
        return transformations.containsKey(position) && transformations.get(position).contains(subshape);
    }

    public void observeSubshape(Integer position, Shape subshape) {
        if (!observations.containsKey(position)) {
            observations.put(position, new HashMap<>());
        }

        Integer numberObserved = observations.get(position).getOrDefault(subshape, 0);
        numberObserved += 1;
        observations.get(position).put(subshape, numberObserved);

        if (numberObserved == 7) {
            if (!transformations.containsKey(position)) {
                transformations.put(position, new HashSet<>());
            }
            transformations.get(position).add(subshape);
            System.out.println("Transformation recorded:\n" +
                    shape.toString() + "\n" +
                    "at position " + position.toString() + "\n" +
                    subshape.toString());
        }
    }
}
