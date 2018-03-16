package com.kkch.xxworld.entity;

import java.util.concurrent.locks.ReentrantLock;

public class Runtime {

	private String loginId;

	private String runtimeUUID;
	
	private ReentrantLock lock = new ReentrantLock();
	
	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getRuntimeUUID() {
		return runtimeUUID;
	}
	
	public void setRuntimeUUID(String runtimeUUID) {
		this.runtimeUUID = runtimeUUID;
	}
	
	
}
