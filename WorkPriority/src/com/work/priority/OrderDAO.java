package com.work.priority;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDAO {

	static PriorityQueue pQueue = new PriorityQueue();

	public String addOrder(Order order) {

		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
			Date orderDate = date.parse(order.getOrderDate());

			long timeInSeconds = OrderUtil.getTimeInSeconds(orderDate);
			if (pQueue.enqueue(order.getOrderID(), timeInSeconds)) {
				return "Success";
			} else {
				return "Already Present";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "failed";
	}
	
	public static void main(String[] args) {
		System.out.println(new OrderDAO().getListOfIds());
	}
	
	public Order getHighestRatedId() {
		Data dequeue = pQueue.dequeue();

		Order order = new Order();
		try{	
			if(dequeue != null){			
			
		order.setOrderID(dequeue.getRequestorId());
		order.setOrderDate(OrderUtil.convertTime(dequeue.getTime()));
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return order;
		
	}

	public List<Long> getListOfIds() {

		return pQueue.getIds();
	}

	public boolean removeId(long id) {
		Data remove = pQueue.remove(id);

		if (null != remove) {
			return true;
		}
		return false;
	}

	public int getOrdersSize() {
		return pQueue.getIds().size();
	}

	public int getPositionInQueue(int id) {
		return pQueue.getPositionInQueue(id);
	}

	public double getAverageWaitTime(String time) throws ParseException {

		return pQueue.getAverageTime(time);
	}

}
