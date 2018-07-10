package com.oracle.truffle.sl.runtime.conscell;

public final class SLConsCell4 extends SLConsCell {

    // Implicit shape:
    //   - head
    //   - SLConsCell2
    //		- head
    //		- SLConsCell2
    //			- head
    //			- tail

    private final Object inlinedHead1;
    private final Object inlinedHead2;
    private final Object inlinedTail;

    public SLConsCell4(Object head, SLConsCell3 tailCell) {
        super(head);
        this.inlinedHead1 = tailCell.getHead();
        SLConsCell2 cell = (SLConsCell2) tailCell.getTail();
        this.inlinedHead2 = cell.getHead();
        this.inlinedTail = cell.getTail();
    }

    public Object getTail() {
        return new SLConsCell2(this.inlinedHead1, new SLConsCell2(this.inlinedHead2, this.inlinedTail));
    }
}
