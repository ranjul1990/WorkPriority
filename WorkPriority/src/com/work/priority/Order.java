package com.work.priority;

import java.util.Date;

//@XmlRootElement(name = "order")
public class Order {

	/**
	 * 
	 */

	// @XmlElement

	private long orderID;

	private String orderDate;

	public Order() {

	}

	public Order(int orderID, String date) {
		this.orderID = orderID;
		this.orderDate = date;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

}
