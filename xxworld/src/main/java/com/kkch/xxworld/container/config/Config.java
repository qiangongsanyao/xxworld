package com.kkch.xxworld.container.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kkch.xxworld.executor.ChangableExecutorImpl;
import com.kkch.xxworld.executor.a.ChangableExecutor;

@SpringBootApplication
public class Config {

	@Bean("easy")
	public ChangableExecutor easyTaskChangableExecutor() {
		ChangableExecutor changableExecutor = new ChangableExecutorImpl();
		changableExecutor.setTimeout(10*1000);
		return changableExecutor;
	}
	
	@Bean("hard")
	public ChangableExecutor hardTaskChangableExecutor() {
		ChangableExecutor changableExecutor = new ChangableExecutorImpl();
		changableExecutor.setTimeout(60*1000);
		return changableExecutor;
	}
	
	@Bean("super")
	public ChangableExecutor superTaskChangableExecutor() {
		ChangableExecutor changableExecutor = new ChangableExecutorImpl();
		changableExecutor.setTimeout(600*1000);
		return changableExecutor;
	}
	
}
