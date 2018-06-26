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
    private static final Shape emptyShape = LAYOUT.createShape(SLObjectType.SINGLETON);

    @Specialization
    public final DynamicObject newCell(Object head, Object tail) {

        DynamicObject cell = emptyShape.newInstance();
        cell.define("head", head);
        cell.define("tail", tail);

        SLShapeWrapper wrapper = SLShapeWrapper.getWrapperForShape(cell.getShape());

        if (head instanceof DynamicObject) {
            DynamicObject headDynamic = (DynamicObject)head;
            Shape headSubshape = headDynamic.getShape();
            wrapper.observeSubshape(0, headSubshape);
            if (wrapper.isTransformation(0, headSubshape)) {
                cell.delete("head");
                SLShapeWrapper.inlineSubobject(cell, headDynamic, "head");
            }
        }

        if (tail instanceof DynamicObject) {
            DynamicObject tailDynamic = (DynamicObject)tail;
            Shape tailSubshape = tailDynamic.getShape();
            wrapper.observeSubshape(1, tailSubshape);
            if (wrapper.isTransformation(1, tailSubshape)) {
                cell.delete("tail");
                SLShapeWrapper.inlineSubobject(cell, tailDynamic, "tail");
            }
        }
        
        System.out.println("----");
        System.out.println("Object shape:");
        System.out.println(cell.getShape());
        System.out.println("Object content:");
        for (Property property : cell.getShape().getProperties()) {
            System.out.println(property.getKey() + ": " + cell.get(property.getKey()));
        }

        return cell;
    }
}
