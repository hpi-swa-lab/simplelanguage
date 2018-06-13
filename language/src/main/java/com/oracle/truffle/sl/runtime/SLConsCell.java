package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class SLConsCell {

	private final Object head;
	private final Object tail;
	
	public SLConsCell(Object head, Object tail) {
		this.head = head;
		this.tail = tail;
	}
	
	public Object getHead() {
		return head;
	}
	
	public Object getTail() {
		return tail;
	}
	
	@Override
	@TruffleBoundary
	public String toString() {
		return String.format("[%s,%s]", head.toString(), tail.toString());
	}
}
