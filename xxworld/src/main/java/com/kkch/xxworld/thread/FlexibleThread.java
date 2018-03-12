package com.kkch.xxworld.thread;

import java.util.concurrent.BlockingQueue;

/**
 * 可超时中断的线程
 * @author Ch
 *
 */
public abstract class FlexibleThread extends Thread {

	protected int timeout;
	protected BlockingQueue<Runnable> tasks;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public BlockingQueue<Runnable> getTasks() {
		return tasks;
	}

	public void setTasks(BlockingQueue<Runnable> tasks) {
		this.tasks = tasks;
	}

	/**
	 * 
	 * @param timeout 超时时间
	 * @param tasks 任务池
	 */
	public FlexibleThread(int timeout, BlockingQueue<Runnable> tasks) {
		super();
		this.timeout = timeout;
		this.tasks = tasks;
		if(timeout <= 0) {
			throw new IllegalArgumentException("timeout cannot <= 0");
		}
		if(tasks==null) {
			throw new IllegalArgumentException("tasks cannot be null");
		}
	}
	
	protected volatile boolean finished;

	protected volatile boolean shutdown;
	
	public abstract boolean isTimeOut();

	public boolean isShutdown() {
		return shutdown;
	}
	
	public void shutDown() {
		this.shutdown = true;
		this.tasks = null;
		interrupt();
	}

	public boolean isFinished() {
		return finished;
	}
	
}
