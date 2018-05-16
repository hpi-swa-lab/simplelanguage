package com.oracle.truffle.sl.runtime;

public class SLConsCell {
	
	private Object head;
	
	
	private Object tail;
	
	
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
}
