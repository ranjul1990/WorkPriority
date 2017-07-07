package com.work.priority;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PriorityQueue {
	private Node<Data> head;

	private Node<Data> tail;

	private Set<Long> ids = new HashSet<Long>();

	public synchronized boolean enqueue(long id, long time) {
		if (ids.contains(id)) {
			return false;
		}
		ids.add(id);
		Data data = new Data(id, time);
		Node<Data> newNode = new Node<Data>(data, null);
		if (head == null && tail == null) {
			head = newNode;
			tail = newNode;
			return true;
		}
		head.getData().updatePriority();
		int compare = Double.compare(data.getPriority(), head.getData().getPriority());
		if (0 < compare) {
			newNode.setNext(head);
			head = newNode;
			return true;
		}
		Node<Data> current = head.getNext();
		Node<Data> previous = head;
		while (current != null) {
			current.getData().updatePriority();
			compare = Double.compare(data.getPriority(), current.getData().getPriority());
			if (0 < compare) {
				newNode.setNext(current);
				previous.setNext(newNode);
				return true;
			}
			previous = current;
			current = current.getNext();
		}
		tail.setNext(newNode);
		tail = newNode;
		return true;
	}

	public synchronized Data dequeue() {
		if (head == null && tail == null) {
			return null;
		}
		Node<Data> deQueueNode = head;
		if (head == tail) {
			head = tail = null;
		} else {
			head = head.getNext();
		}
		Data data = deQueueNode.getData();
		ids.remove(data.getRequestorId());
		return data;
	}

	public List<Long> getIds() {
		ArrayList<Long> ids = new ArrayList<Long>();

		Node<Data> current = head;
		while (current != null) {
			ids.add(current.getData().getRequestorId());
			current = current.getNext();
		}
		return ids;
	}

	public double getAverageTime(String time) {

		SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

		long totalSeconds = 0;
		long avgWaitTime = 0;
		Date currentTime;
		try {
			currentTime = date.parse(time);

			Node<Data> current = head;
			while (current != null) {

				totalSeconds = totalSeconds + (current.getData().getTime() - OrderUtil.getTimeInSeconds(currentTime));
				System.out.println("curr********" + current.getData().getTime());
				System.out.println("_______________"+OrderUtil.getTimeInSeconds(currentTime));
				current = current.getNext();
			}
		} catch (ParseException e) {
			System.out.println("Exception*************** " + e.getMessage());
		}
		System.out.println("total seconds ++++++++++++" + totalSeconds);

		if (totalSeconds != 0) {
			avgWaitTime = totalSeconds / getIds().size();
		}
		return avgWaitTime;

	}

	public synchronized Data remove(long id) {
		if (head == null && tail == null) {
			return null;
		}
		Data data = head.getData();
		if (data.getRequestorId() == id) {
			ids.remove(id);
			if (head == tail) {
				head = tail = null;
			} else {
				head = head.getNext();
			}
			return data;
		}
		Node<Data> current = head.getNext();
		Node<Data> prev = head;

		while (current != null) {
			data = current.getData();
			if (data.getRequestorId() == id) {
				ids.remove(id);
				prev.setNext(current.getNext());
				return data;
			}
			prev = current;
			current = current.getNext();

		}
		return null;
	}

	public int getPositionInQueue(long id) {
		if (head == null && tail == null) {
			return -1;
		}
		Data data = head.getData();
		if (data.getRequestorId() == id) {
			return 0;
		}
		Node<Data> current = head.getNext();
		int i = 1;
		while (current != null) {
			data = current.getData();
			if (data.getRequestorId() == id) {
				return i;
			}
			current = current.getNext();
			i++;
		}
		return -1;
	}

}

class Node<T> {
	private T data;

	public T getData() {
		return data;
	}

	private Node<T> next;

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

}

class Data {
	private long requestorId;

	private long time;

	private double priority;

	public long getRequestorId() {
		return requestorId;
	}

	public long getTime() {
		return time;
	}

	public double getPriority() {
		return priority;
	}

	private PriorityType priorityType;

	public Data(long requestorId, long time) {
		this.requestorId = requestorId;
		this.time = time;
		priorityType = PriorityType.getPriorityType(requestorId);
		updatePriority();
	}

	public void updatePriority() {
		priority = priorityType.getPriorityCalculator().calculatePriority(time);
	}

	@Override
	public String toString() {
		return "Data [requestorId=" + requestorId + ", priority=" + priority + ", priorityType=" + priorityType + "]";
	}

}

enum PriorityType {
	NORMAL(new NormalIdPriorityCalculator()),

	PRIORITY(new PriorityIdPriorityCalculator()),

	VIP(new VIPIdPriorityCalculator()),

	MGMT(new MgmtIdPriorityCalculator());

	private PriorityCalculator priorityCalculator;

	public PriorityCalculator getPriorityCalculator() {
		return priorityCalculator;
	}

	PriorityType(PriorityCalculator priorityCalculator) {
		this.priorityCalculator = priorityCalculator;
	}

	public static PriorityType getPriorityType(long requestorId) {
		int firstBit = (byte) (requestorId % 3 == 0 ? 1 : 0);
		int secondBit = (byte) (requestorId % 5 == 0 ? 2 : 0);

		int ordinal = firstBit | secondBit;

		return PriorityType.values()[ordinal];
	}
}
