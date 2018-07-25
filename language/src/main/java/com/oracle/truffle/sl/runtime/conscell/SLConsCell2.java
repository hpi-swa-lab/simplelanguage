package com.oracle.truffle.sl.runtime.conscell;

import com.oracle.truffle.api.CompilerDirectives;

public final class SLConsCell2 extends SLConsCell {

    private final Object tail;

    public SLConsCell2(Object head, Object tail) {
        super(head);
        this.tail = tail;
    }

    public Object getTail() {
        return this.tail;
    }

    @Override
    @CompilerDirectives.TruffleBoundary
    public String toString() {
        return String.format("[%s,%s]", getHead().toString(), tail.toString());
    }
}
