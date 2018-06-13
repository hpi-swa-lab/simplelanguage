package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class SLFuncConsCell {

    private final SLFunction head;
    private final Object tail;

    public SLFuncConsCell(SLFunction head, Object tail) {
        this.head = head;
        this.tail = tail;
    }

    public SLFunction getHead() {
        return head;
    }

    public Object getTail() {
        return tail;
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return String.format("[%s,%s]", head.getName(), tail.toString());
    }
}
