package com.work.priority;

public class VIPIdPriorityCalculator implements PriorityCalculator {

	@Override
	public double calculatePriority(long submitTime) {
		double diff = System.currentTimeMillis() - submitTime ;
		return Math.max(4, 2*diff * OrderUtil.calaculateLogn(diff));
	}
	

}
