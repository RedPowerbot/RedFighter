package com.util;

public abstract class Condition {

	public abstract boolean isTrue();

	/**
	 * Waits until the condition is in a inputed state.
	 * 
	 * @param isTrue
	 * @param timeOut
	 * @return Time it took for the condition to become the state. -1 if ran out
	 *         of time.
	 */
	public long waitOn(boolean isTrue, int timeOut) {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < timeOut) {
			try {
				if (isTrue() == isTrue) {
					return System.currentTimeMillis() - start;
				}
				Thread.sleep(5);
			} catch (Exception e) {
				break;
			}
		}
		return -1;
	}

}
