package com.oracle.truffle.sl.runtime;

public final class SLLongConsCell {

    private final long head;
    private final Object tail;

    public SLLongConsCell(long head, Object tail) {
        this.head = head;
        this.tail = tail;
    }

    public long getHead() {
        return head;
    }

    public Object getTail() {
        return tail;
    }
}
