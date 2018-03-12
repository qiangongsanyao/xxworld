package com.kkch.xxworld.executor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangableExecutorImplTest {

	@Autowired
	ChangableExecutorImpl changableExecutorImpl;
	@Test
	public void test() {
		changableExecutorImpl.setTimeout(20000);
		changableExecutorImpl.start();
		for(int i=0;i<1000;i++) {
			changableExecutorImpl.putTask(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(12000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		try {
			for(int i=0;i<1000;i++) {
				changableExecutorImpl.printSelfMsg();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
