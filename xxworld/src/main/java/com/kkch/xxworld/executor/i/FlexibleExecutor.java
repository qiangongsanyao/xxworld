package com.kkch.xxworld.executor.i;

public interface FlexibleExecutor {

	void putTask(Runnable task);
	
	void start();
	
	void shutdown();
	
}
