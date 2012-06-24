package com.breaking;

public class TimedBreakHandler implements BreakHandler {

	private long breakPeriod;
	private long intermissionPeriod;

	private long lastBreakDate;
	private long nextBreakDate;
	private long nextBreakPeriod;

	public static TimedBreakHandler getShortBreakHandler() {
		return new TimedBreakHandler(300000, 1800000); // 5 minutes, 30
	}

	public static TimedBreakHandler getLongBreakHandler() {
		return new TimedBreakHandler(1800000, 180 * 108000000); // 30 minutes, 3
																// hours
	}

	public TimedBreakHandler(long breakPeriod, long intermissionPeriod) {
		this.breakPeriod = breakPeriod;
		this.intermissionPeriod = intermissionPeriod;
		reset();
	}

	@Override
	public boolean needToBreak() {
		if (System.currentTimeMillis() > nextBreakDate && !isFinished()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean reset() {
		lastBreakDate = System.currentTimeMillis();
		nextBreakDate = lastBreakDate + randomize(intermissionPeriod);
		nextBreakPeriod = randomize(breakPeriod);
		return true;
	}

	@Override
	public boolean isFinished() {
		return System.currentTimeMillis() > nextBreakDate + nextBreakPeriod;
	}

	private long randomize(long l) {
		return (long) (l + Math.random() * (l / .25));
	}

}
