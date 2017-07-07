package com.work.priority;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/UserService")

public class OrderService {
	OrderDAO orderDao = new OrderDAO();

	@POST
	@Path("/addOrderedData")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addOrderedData(Order order) {
		return orderDao.addOrder(order);
	}
	
	
	@GET
	@Path("/getHighestRankId")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getHighestRankId() {
		Order highestRatedId = orderDao.getHighestRatedId();
		return highestRatedId;
	}

	@GET
	@Path("/getListOfOrderIds")
	@Produces(MediaType.APPLICATION_JSON)
	public java.util.List<Long> getListOfIds() {
		return orderDao.getListOfIds();
	}

	@POST
	@Path("/removeOrderId")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeId(Integer orderID) {
		if (orderDao.removeId(orderID)) {
			return "Removed Successfully";
		}
		return "Failed to remove";
	}

	@POST
	@Path("/getPositionInQueue")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getPositionInQueue(int orderId) {
		int positionInQueue = orderDao.getPositionInQueue(orderId);
		if (positionInQueue != -1) {
			return "Order found at index : " + positionInQueue;
		}
		return "Order not found";

	}

	@POST
	@Path("/getAverageWaitTime")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getAverageWaitTime(String time) throws ParseException {

		return "Average Waiting Time : " + orderDao.getAverageWaitTime(time);
	}

	void addOrder(Order order) {
		orderDao.addOrder(order);
	}

	static OrderService service = new OrderService();

}