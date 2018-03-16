package com.kkch.xxworld.pool.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.kkch.xxworld.pool.Pool;

public class TimedPool<T> implements Pool<T>,Runnable {

	private HashMap<String, T> objectMap = new HashMap<>();
	private HashMap<String, Long> timeMap = new HashMap<>();
	
	
	public TimedPool(int aliveTime, int freshMs) {
		super();
		this.aliveTime = aliveTime;
		this.freshMs = freshMs;
		Thread gcThread = new Thread(this);
		gcThread.setDaemon(true);
		gcThread.start();
		System.out.println("alive time:"+aliveTime/1000+"s");
	}
	
	public TimedPool(int aliveTime) {
		this(aliveTime,60*1000);
	}
	

	public TimedPool() {
		this(30*60*1000);
	}

	public int getAliveTime() {
		return aliveTime;
	}

	public void setAliveTime(int aliveTime) {
		this.aliveTime = aliveTime;
	}

	int aliveTime;
	
	int freshMs;
	
	public int getFreshMs() {
		return freshMs;
	}

	public void setFreshMs(int freshMs) {
		this.freshMs = freshMs;
	}

	@Override
	public String put(T t) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return put(t, uuid);
	}

	public String put(T t, String uuid) {
		olock.writeLock().lock();
		objectMap.put(uuid, t);
		olock.writeLock().unlock();
		tlock.writeLock().lock();
		timeMap.put(uuid, System.currentTimeMillis());
		tlock.writeLock().unlock();
		return uuid;
	}

	@Override
	public T get(String uuid) {
		olock.readLock().lock();
		T t = objectMap.get(uuid);
		olock.readLock().unlock();
		return t;
	}

	ReentrantReadWriteLock olock = new ReentrantReadWriteLock();
	ReentrantReadWriteLock tlock = new ReentrantReadWriteLock();
	
	@Override
	public void run() {
		while(true) {
			ArrayList<String> timeoutuuids = new ArrayList<>();
			tlock.readLock().lock();
			try {
				long nowtime = System.currentTimeMillis();
				timeMap.forEach((id,t)->{
					if(nowtime-t>aliveTime) {
						timeoutuuids.add(id);
					}
				});
			}catch (Exception e) {
				e.printStackTrace();
			}
			tlock.readLock().unlock();
			if(!timeoutuuids.isEmpty()) {
				tlock.writeLock().lock();
				ArrayList<String> reovedtimeoutuuids = new ArrayList<>();
				timeoutuuids.forEach(id->{
					if(System.currentTimeMillis()-timeMap.get(id)>aliveTime) {
						timeMap.remove(id);
						reovedtimeoutuuids.add(id);
					}
				});
				tlock.writeLock().unlock();
				if(!reovedtimeoutuuids.isEmpty()) {
					olock.writeLock().lock();
					reovedtimeoutuuids.forEach(objectMap::remove);
					olock.writeLock().unlock();
				}
			}
			try {
				Thread.sleep(freshMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public T getAndFresh(String uuid) {
		olock.readLock().lock();
		T t = objectMap.get(uuid);
		olock.readLock().unlock();
		tlock.writeLock().lock();
		if(timeMap.containsKey(uuid)) {
			timeMap.put(uuid, System.currentTimeMillis());
		}else {
			t = null;
		}
		tlock.writeLock().unlock();
		return t;
	}

	public void remove(String uuid) {
		tlock.writeLock().lock();
		timeMap.remove(uuid);
		tlock.writeLock().unlock();
		olock.writeLock().lock();
		objectMap.remove(uuid);
		olock.writeLock().unlock();
	}

}
