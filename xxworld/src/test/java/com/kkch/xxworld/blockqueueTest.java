package com.kkch.xxworld;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class blockqueueTest {

	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		for(int i=0;i<10;i++)
			queue.add(String.valueOf(i));
		
		for(int i=0;i<10;i++) {
			String s = queue.take();
			System.out.println(Thread.currentThread().getName()+" get "+s);
		}
		for(int i=0;i<10;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							String s = queue.take();
							System.out.println(Thread.currentThread().getName()+" get "+s);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		for(int i=0;i<1000;i++) {
			for(int j=0;j<10;j++) {
				queue.add(UUID.randomUUID().toString().replaceAll("-", ""));
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
