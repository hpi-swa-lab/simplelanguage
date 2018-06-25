package com.oracle.truffle.sl.runtime.conscell;

import java.util.Arrays;

public final class SLConsCell3 extends SLConsCell {
	
	private final Object tail1;
	private final Object tail2;

	public SLConsCell3(Object head, SLConsCell2 tailCell) {
		super(head);
		this.tail1 = tailCell.getHead();
		this.tail2 = tailCell.getTail();
	}

	public Object getTail() {
		return Arrays.asList(this.tail1, this.tail2);
	}
}
