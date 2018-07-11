package com.oracle.truffle.sl.runtime.conscell;

public abstract class SLConsCell {

    private final Object head;

    public SLConsCell(Object head) {
        this.head = head;
    }

    public Object getHead() {
        return head;
    }
}
