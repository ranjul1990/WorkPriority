package com.work.priority;

public class MgmtIdPriorityCalculator implements PriorityCalculator {

	@Override
	public double calculatePriority(long submitTime) {
		return Long.MAX_VALUE;
	}

}
