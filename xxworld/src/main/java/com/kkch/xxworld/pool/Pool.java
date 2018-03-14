package com.kkch.xxworld.pool;

public interface Pool<T>{

	String put(T t);
	
	T get(String uuid);
	
}
