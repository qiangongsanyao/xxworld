package com.kkch.xxworld.container;

public interface ExecutorContainer {

	void putEasyTask(Runnable runnable);
	
	void putHardTask(Runnable runnable);
	
	void putSuperTask(Runnable runnable);

	void start();
	
	void shutDown();
	
}
