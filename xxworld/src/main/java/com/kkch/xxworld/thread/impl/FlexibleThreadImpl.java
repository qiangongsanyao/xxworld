package com.kkch.xxworld.thread.impl;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;

import com.kkch.xxworld.thread.FlexibleThread;

public class FlexibleThreadImpl extends FlexibleThread {

	private long thisTaskStartTime = -1;
	
	public FlexibleThreadImpl(int timeout, BlockingQueue<Runnable> tasks) {
		super(timeout, tasks);
	}
	
	private Vector<Exception> exceptions = new Vector<>();

	public Iterable<Exception> exceptions (){
		return exceptions;
	}
	
	@Override
	public void run() {
		super.run();
		while(!shutdown&&!isInterrupted()) {
			try {
				Runnable runnable = tasks.take();
				thisTaskStartTime = System.currentTimeMillis();
				try {
					runnable.run();
				} catch (Exception e) {
					e.printStackTrace();
					exceptions.add(e);
				}
				thisTaskStartTime = -1;
			} catch (InterruptedException e) {
				e.printStackTrace();
				shutdown = true;
				interrupt();
				exceptions.add(e);
			}
		}
		finished = true;
	}
	
	@Override
	public boolean isTimeOut() {
		if(thisTaskStartTime < 0) {
			return false;
		} else {
			if(System.currentTimeMillis()-thisTaskStartTime>timeout) {
				return true;
			} else {
				return false;
			}
		}
	}

}
