package com.work.priority;

public class PriorityIdPriorityCalculator implements PriorityCalculator {

	@Override
	public double calculatePriority(long submitTime) {
		double diff = System.currentTimeMillis() - submitTime ;
		return Math.max(3, diff * OrderUtil.calaculateLogn(diff));
	}

}
