package com.oracle.truffle.sl.runtime.conscell;

public final class SLConsCell2 extends SLConsCell {
    
	private final Object tail;

	public SLConsCell2(Object head, Object tail) {
		super(head);
		this.tail = tail;
	}

	public Object getTail() {
		return this.tail;
	}
}
