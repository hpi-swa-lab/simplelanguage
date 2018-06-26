package com.oracle.truffle.sl.builtins;

import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.runtime.*;
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

    @Specialization
    public final DynamicObject newCell(Object head, Object tail) {

        Property headProperty = Property.create("head", headLocation, 0);
        Property tailProperty = Property.create("tail", headLocation, 0);
        Shape shape = emptyShape
                .addProperty(headProperty)
                .addProperty(tailProperty);

        SLShapeWrapper wrapper = SLShapeWrapper.getWrapperForShape(shape);

        if (head instanceof DynamicObject) {
            DynamicObject headDynamic = (DynamicObject)head;
            Shape headSubshape = headDynamic.getShape();
            wrapper.observeSubshape(0, headSubshape);
            if (wrapper.isTransformation(0, headSubshape)) {
                shape = shape.removeProperty(headProperty); 
                shape = SLShapeWrapper.inlineSubshape(shape, headSubshape, 0, "head");
            }
        }

        if (tail instanceof DynamicObject) {
            DynamicObject tailDynamic = (DynamicObject)tail;
            Shape tailSubshape = tailDynamic.getShape();
            wrapper.observeSubshape(1, tailSubshape);
            if (wrapper.isTransformation(1, tailSubshape)) {
                shape = shape.removeProperty(tailProperty); 
                shape = SLShapeWrapper.inlineSubshape(shape, tailSubshape, 1, "tail");
            }
        }

        // Shape may have changed due to tranformations
        wrapper = SLShapeWrapper.getWrapperForShape(shape);

        DynamicObject cell = shape.newInstance();
        cell.set("head", head);
        cell.set("tail", tail);
        System.out.println("Object shape: " + cell.getShape());
        return cell;
    }
}
