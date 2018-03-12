package com.kkch.xxworld.thread.factory;

import java.util.concurrent.BlockingQueue;

import com.kkch.xxworld.thread.FlexibleThread;

/**
 * 
 * @author Ch
 *
 */
public interface FlexibleThreadFactory {

	FlexibleThread get(int timeout,BlockingQueue<Runnable> tasks);

}
