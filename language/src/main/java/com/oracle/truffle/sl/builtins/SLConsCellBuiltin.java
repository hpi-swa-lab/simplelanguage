package com.oracle.truffle.sl.builtins;

import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.*;
import com.oracle.truffle.sl.runtime.SLObjectType;

/**
 * Built-in function to create a new cons cell.
 */
@NodeInfo(shortName = "cons")
public abstract class SLConsCellBuiltin extends SLBuiltinNode {

    private static final Layout LAYOUT = Layout.createLayout();
    private static final Shape.Allocator ALLOCATOR = LAYOUT.createAllocator();
    private static final Location headLocation = ALLOCATOR.locationForType(Object.class);
    private static final Location tailLocation = ALLOCATOR.locationForType(Object.class);
    private static final Shape emptyShape = LAYOUT.createShape(SLObjectType.SINGLETON);

    private static Map<Integer, Map<Shape, Integer>> observedShapes = new HashMap<>();

    static {
        observedShapes.put(0, new HashMap<>());
        observedShapes.put(1, new HashMap<>());
    }

    private static void observeShape(Integer position, Shape shape) {
        Integer numberObserved = observedShapes.get(position).getOrDefault(shape, 0);
        numberObserved += 1;
        observedShapes.get(position).put(shape, numberObserved);

        if (numberObserved == 7) {
            System.out.println("Transformation recorded at position\n" + 
                position.toString() + " contains\n" + shape.toString());
        }
    }

    @Specialization
    public final DynamicObject newCell(Object head, Object tail) {
        if (head instanceof DynamicObject) {
            DynamicObject headDynamic = (DynamicObject)head;
            observeShape(0, headDynamic.getShape());
        }
        
        if (tail instanceof DynamicObject) {
            DynamicObject tailDynamic = (DynamicObject)tail;
            observeShape(1, tailDynamic.getShape());
        }

        Shape shape = emptyShape
                .addProperty(
                        Property.create("head", headLocation, 0)
                )
                .addProperty(
                        Property.create("tail", tailLocation, 0));

        DynamicObject cell = shape.newInstance();
        cell.set("head", head);
        cell.set("tail", tail);
        return cell;
    }
}
