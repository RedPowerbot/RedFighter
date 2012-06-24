package com.util;

import java.util.Enumeration;

public class SimpleEnumeration<E> implements Enumeration<E> {

	private E[] array;
	private int index = -1;
	private int len;
	
	public SimpleEnumeration(E[] array) {
		this.array = array;
		this.len = array.length;
	}
	
	@Override
	public boolean hasMoreElements() {
		return index + 1 < len;
	}

	@Override
	public E nextElement() {
		index++;
		return array[index];
	}
	
}