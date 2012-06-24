package com.util;

import java.util.ArrayList;

public class SortUtil<E extends Comparable<E>> {
	
	public static int[] createOrderedArray(int len) {
		int[] array = new int[len];
		for (int i = 0; i < len; i++) {
			array[i] = i;
		}
		return array;
	}
	
	public void bubbleSort(ArrayList<E> list, boolean ascending) {
		if (list.size() == 0) {
			return;
		}
		int n = list.size();
		E temp;
		boolean doMore = true;
		if (ascending) {
			while (doMore) {
				n--;
				doMore = false;
				for (int i = 0; i < n; i++) {
					if (list.get(i).compareTo(list.get(i + 1)) > 0) {
						temp = list.get(i);
						list.set(i, list.get(i + 1));
						list.set(i + 1, temp);
						doMore = true;
					}
				}
			}
		} else {
			while (doMore) {
				n--;
				doMore = false;
				for (int i = 0; i < n; i++) {
					if (list.get(i).compareTo(list.get(i + 1)) < 0) {
						temp = list.get(i);
						list.set(i, list.get(i + 1));
						list.set(i + 1, temp);
						doMore = true;
					}
				}
			}
		}
	}

	public void bubbleSort(E[] array, boolean ascending) {
		if (array.length == 0) {
			return;
		}
		int n = array.length;
		E temp;
		boolean doMore = true;
		if (ascending) {
			while (doMore) {
				n--;
				doMore = false;
				for (int i = 0; i < n; i++) {
					if (array[i].compareTo(array[i + 1]) > 0) {
						temp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = temp;
						doMore = true;
					}
				}
			}
		} else {
			while (doMore) {
				n--;
				doMore = false;
				for (int i = 0; i < n; i++) {
					if (array[i].compareTo(array[i + 1]) < 0) {
						temp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = temp;
						doMore = true;
					}
				}
			}
		}
	}

}
