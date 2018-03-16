package com.kkch.xxworld.pool.impl;

public interface OTask<T> extends Runnable{

	void set(T t);
	
}
