package com.oracle.truffle.sl.runtime.conscell;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class SLConsCell {

    private final Object head;

    public SLConsCell(Object head) {
        this.head = head;
    }

    public Object getHead() {
        return head;
    }

    public abstract Object getTail();

    @Override
    @TruffleBoundary
    public String toString() {
        return String.format("[%s,%s]", head.toString(), getTail().toString());
    }
}
