package com.kkch.xxworld.thread.factory.impl;

import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Component;

import com.kkch.xxworld.thread.FlexibleThread;
import com.kkch.xxworld.thread.factory.FlexibleThreadFactory;
import com.kkch.xxworld.thread.impl.FlexibleThreadImpl;

@Component
public class FlexibleThreadFactoryImpl implements FlexibleThreadFactory {

	@Override
	public FlexibleThread get(int timeout,BlockingQueue<Runnable> tasks) {
		FlexibleThreadImpl flexibleThreadImpl = new FlexibleThreadImpl(timeout, tasks);
//		System.out.println(flexibleThreadImpl+" has be created");
		return flexibleThreadImpl;
	}

}
