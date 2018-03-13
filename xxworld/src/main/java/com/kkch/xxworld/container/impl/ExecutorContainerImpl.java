package com.kkch.xxworld.container.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kkch.xxworld.container.ExecutorContainer;
import com.kkch.xxworld.executor.a.ChangableExecutor;

@Component
public class ExecutorContainerImpl implements ExecutorContainer {

	@Autowired
	@Qualifier("easy")
	ChangableExecutor easyChangableExecutor;
	@Autowired
	@Qualifier("hard")
	ChangableExecutor hardChangableExecutor;
	@Autowired
	@Qualifier("super")
	ChangableExecutor superChangableExecutor;
	
	@Override
	public void putEasyTask(Runnable runnable) {
		easyChangableExecutor.putTask(runnable);
	}

	@Override
	public void putHardTask(Runnable runnable) {
		hardChangableExecutor.putTask(runnable);
	}

	@Override
	public void putSuperTask(Runnable runnable) {
		superChangableExecutor.putTask(runnable);
	}

	public ChangableExecutor getEasyChangableExecutor() {
		return easyChangableExecutor;
	}

	public void setEasyChangableExecutor(ChangableExecutor easyChangableExecutor) {
		this.easyChangableExecutor = easyChangableExecutor;
	}

	public ChangableExecutor getHardChangableExecutor() {
		return hardChangableExecutor;
	}

	public void setHardChangableExecutor(ChangableExecutor hardChangableExecutor) {
		this.hardChangableExecutor = hardChangableExecutor;
	}

	public ChangableExecutor getSuperChangableExecutor() {
		return superChangableExecutor;
	}

	public void setSuperChangableExecutor(ChangableExecutor superChangableExecutorr) {
		this.superChangableExecutor = superChangableExecutorr;
	}

	@Override
	public void start() {
		easyChangableExecutor.start();
		hardChangableExecutor.start();
		superChangableExecutor.start();
	}

	@Override
	public void shutDown() {
		easyChangableExecutor.shutdown();
		hardChangableExecutor.shutdown();
		superChangableExecutor.shutdown();
	}

	
}
