package com.work.priority;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

	public static long getTimeInSeconds(Date date) {

		return date.getTime() / 1000;
	}

	public static int compareTime(Date date1, Date date2) {

		long time1 = date1.getTime() / 1000 % 60;
		long time2 = date2.getTime() / 1000 % 60;

		if (time1 == time2) {
			return 0;
		}
		if (time1 > time2) {
			return 1;
		}
		if (time1 < time2) {
			return -1;
		}
		return 0;

	}

	public static double calaculateLogn(double time) {
		if (time == 0.0) {
			return 0.0;
		}
		double log = Math.log(time);
		return log;
	}

	public static int calculateVIPRanking(Date orderDate1, Date orderDate2) {
		// TODO Auto-generated method stub

		long time1 = getTimeInSeconds(orderDate1);

		long time2 = getTimeInSeconds(orderDate2);

		double log1 = Math.max(4, (2 * time1) * calaculateLogn(time1));

		double log2 = Math.max(4, (2 * time2) * calaculateLogn(time2));

		return Double.compare(log1, log2);

	}

	public static int calculatePriorityRanking(Date orderDate1, Date orderDate2) {
		// TODO Auto-generated method stub

		long time1 = getTimeInSeconds(orderDate1);

		long time2 = getTimeInSeconds(orderDate2);

		double log1 = Math.max(3, time1 * calaculateLogn(time1));

		double log2 = Math.max(3, time2 * calaculateLogn(time2));

		return Double.compare(log1, log2);

	}

	public static String convertTime(long time) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		String dateString = formatter.format(new Date(time * 1000L));

		return dateString;
	}
}