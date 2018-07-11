package com.oracle.truffle.sl.runtime.conscell;

public final class SLConsCell3 extends SLConsCell {

    // Implicit shape:
    //   - head
    //   - SLConsCell2
    //		- head
    //		- tail

    private final Object inlinedHead;
    private final Object inlinedTail;

    public SLConsCell3(Object head, SLConsCell2 tailCell) {
        super(head);
        this.inlinedHead = tailCell.getHead();
        this.inlinedTail = tailCell.getTail();
    }

    public SLConsCell2 getTail() {
        return new SLConsCell2(this.inlinedHead, this.inlinedTail);
    }
}
