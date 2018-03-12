package com.kkch.xxworld.container;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutorContainerTest {

	@Autowired
	ExecutorContainer executorContainer;
	
	int a;
	
	class EasyTask implements Runnable{

		int no = a++;
		
		@Override
		public void run() {
			executor(9*1000,no);
		}
		
	}
	
	class HardTask implements Runnable{

		int no = a++;
		
		@Override
		public void run() {
			executor(55*1000,no);
		}
		
	}
	
	class SuperTask implements Runnable{

		int no = a++;
		
		@Override
		public void run() {
			executor(550*1000,no);
		}
		
	}
	
	@Test
	public void test() throws InterruptedException {
		executorContainer.start();
		executorContainer.putEasyTask(new EasyTask());
		executorContainer.putHardTask(new HardTask());
		executorContainer.putSuperTask(new SuperTask());
		Thread.sleep(10000);
		executorContainer.putEasyTask(new EasyTask());
		executorContainer.putHardTask(new HardTask());
		executorContainer.putSuperTask(new SuperTask());
		executorContainer.putEasyTask(new SuperTask());
		executorContainer.putHardTask(new SuperTask());
		executorContainer.putSuperTask(new SuperTask());
		Thread.sleep(10000);
		executorContainer.putEasyTask(new EasyTask());
		executorContainer.putHardTask(new HardTask());
		executorContainer.putSuperTask(new SuperTask());
		Thread.sleep(10000);
		executorContainer.putEasyTask(new EasyTask());
		executorContainer.putHardTask(new HardTask());
		executorContainer.putSuperTask(new SuperTask());
		Thread.sleep(10000);
		executorContainer.putEasyTask(new EasyTask());
		executorContainer.putHardTask(new HardTask());
		executorContainer.putSuperTask(new SuperTask());
		Thread.sleep(10000);
		Thread.sleep(1000000);
	}

	public void executor(int time, int no) {
		System.out.println(Thread.currentThread().getName()+" do task "+no);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(Thread.currentThread().getName()+" finish task "+no);
	}

}
