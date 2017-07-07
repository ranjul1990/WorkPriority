package com.work.priority.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.work.priority.PriorityQueue;

public class PriorityQueueTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		PriorityQueue pQueue = new PriorityQueue();
		
		pQueue.enqueue(1, System.currentTimeMillis());
		pQueue.enqueue(5, System.currentTimeMillis());
		pQueue.enqueue(3, System.currentTimeMillis());
		pQueue.enqueue(15, System.currentTimeMillis());
		
		pQueue.enqueue(5, System.currentTimeMillis());
		
		List<Long> ids = pQueue.getIds();
		System.out.println(ids);
		
		System.out.println(pQueue.remove(5));
		
		ids = pQueue.getIds();
		System.out.println(ids);
		
		TimeUnit.SECONDS.sleep(10);
		pQueue.enqueue(10, System.currentTimeMillis());
		ids = pQueue.getIds();
		System.out.println(ids);
		
		pQueue.enqueue(30, System.currentTimeMillis());
		ids = pQueue.getIds();
		System.out.println(ids);
		pQueue.enqueue(60, System.currentTimeMillis());
		ids = pQueue.getIds();
		System.out.println(ids);
		
		System.out.println(pQueue.dequeue());
		System.out.println(pQueue.dequeue());
		System.out.println(pQueue.dequeue());
		System.out.println(pQueue.dequeue());
		System.out.println(pQueue.dequeue());
		System.out.println(pQueue.dequeue());
		System.out.println(pQueue.dequeue());
	}
}
