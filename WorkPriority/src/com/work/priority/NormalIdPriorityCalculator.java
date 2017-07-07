package com.work.priority;

public class NormalIdPriorityCalculator implements PriorityCalculator {

	@Override
	public double calculatePriority(long submitTime) 
	{
		double diff = System.currentTimeMillis() - submitTime ;
		return diff / 1000;
	}

}
