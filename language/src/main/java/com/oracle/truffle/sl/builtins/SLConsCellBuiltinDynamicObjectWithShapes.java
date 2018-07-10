package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Layout;
import com.oracle.truffle.api.object.Shape;
import com.oracle.truffle.sl.runtime.SLObjectType;
import com.oracle.truffle.sl.runtime.SLShapeWrapper;

/**
 * Built-in function to create a new cons cell dynamic object with shapes.
 */
@NodeInfo(shortName = "consDows")
public abstract class SLConsCellBuiltinDynamicObjectWithShapes extends SLBuiltinNode {

    private static final Layout LAYOUT = Layout.createLayout();
    private static final Shape emptyShape = LAYOUT.createShape(SLObjectType.SINGLETON);

    @Specialization
    public final DynamicObject newCell(Object head, Object tail) {

        DynamicObject cell = emptyShape.newInstance();
        cell.define("head", head);
        cell.define("tail", tail);

        SLShapeWrapper wrapper = SLShapeWrapper.getWrapperForShape(cell.getShape());

        SLShapeWrapper.observeObject(cell);
        SLShapeWrapper.optimizeObject(cell);

        return cell;
    }
}
