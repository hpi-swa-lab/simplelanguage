package com.oracle.truffle.sl.builtins;

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

    @Specialization
    public final DynamicObject newCell(Object head, Object tail) {
        Shape.Allocator allocator = LAYOUT.createAllocator();
        Location headLocation = allocator.locationForType(Integer.class);
        Location tailLocation = allocator.locationForType(Object.class);

        Shape shape = LAYOUT.createShape(SLObjectType.SINGLETON)
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
