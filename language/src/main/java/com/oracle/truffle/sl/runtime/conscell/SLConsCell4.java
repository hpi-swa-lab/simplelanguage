package com.oracle.truffle.sl.runtime.conscell;

import java.util.Arrays;
import java.util.ArrayList;

public final class SLConsCell4 extends SLConsCell {
	
	private final Object tail1;
	private final Object tail2;
	private final Object tail3;

	public SLConsCell4(Object head, SLConsCell3 tailCell) {
		super(head);
		ArrayList<Object> tail = (ArrayList<Object>)tailCell.getTail();
		this.tail1 = tail.get(0);
		SLConsCell2 cell = (SLConsCell2)tail.get(1);
		this.tail2 = cell.getHead();
		this.tail3 = cell.getTail();
	}

	public Object getTail() {
		return Arrays.asList(this.tail1, this.tail2, this.tail3);
	}
}
