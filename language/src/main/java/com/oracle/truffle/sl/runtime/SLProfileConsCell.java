package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class SLProfileConsCell {

	private final Object head;
	private final Object tail;
	
	public SLProfileConsCell(Object head, Object tail) {
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
