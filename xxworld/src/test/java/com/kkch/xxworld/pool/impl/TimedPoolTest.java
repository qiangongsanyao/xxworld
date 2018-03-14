package com.kkch.xxworld.pool.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimedPoolTest {

	TimedPool<String> timedPool = new TimedPool<>(100,100);
	
	@Test
	public void test() throws InterruptedException {
		String role = "role";
		String uuid =timedPool.put(role);
		Thread.sleep(1000);
		System.out.println(timedPool.get(uuid));
	}

}
