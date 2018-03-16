package com.kkch.xxworld.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class World {

	@Autowired
	ExecutorContainer executorContainer;
	
	public void submit(Runnable runnable) {
		executorContainer.putEasyTask(runnable);
	}
	
}
