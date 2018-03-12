package com.kkch.xxworld.executor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kkch.xxworld.executor.a.ChangableExecutor;
import com.kkch.xxworld.executor.exception.RunnningException;
import com.kkch.xxworld.thread.FlexibleThread;
import com.kkch.xxworld.thread.factory.FlexibleThreadFactory;

@Component
@Scope("prototype")
public class ChangableExecutorImpl extends ChangableExecutor{

	@Autowired
	private FlexibleThreadFactory flexibleThreadFactory;
	
	public FlexibleThreadFactory getFlexibleThreadFactory() {
		return flexibleThreadFactory;
	}

	public void setFlexibleThreadFactory(FlexibleThreadFactory flexibleThreadFactory) {
		this.flexibleThreadFactory = flexibleThreadFactory;
	}

	private LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
	
	private int checkRate = 1000;
	
	/**
	 * 获得刷新任务池的频率
	 * @param checkRate
	 */
	public int getCheckRate() {
		return checkRate;
	}

	/**
	 * 设置刷新任务池的频率
	 * @param checkRate
	 */
	public void setCheckRate(int checkRate) {
		this.checkRate = checkRate;
	}

	@Override
	public void putTask(Runnable task) {
		tasks.add(task);
	}

	boolean inited = false;
	
	public void init() {
		if(!inited) {
			ensureinit();
		}
		inited = true;
	}
	
	private Vector<FlexibleThread> normalthreads;
	private Vector<FlexibleThread> abnormalthreads;
	
	Thread thread;
	
	private void ensureinit() {
		if(normalthreads!=null) {
			normalthreads.forEach(FlexibleThread::shutDown);
		}
		normalthreads = new Vector<>();
		abnormalthreads = new Vector<>();
		enSureNormalLength();
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!thread.isInterrupted()) {
					try {
						checkNormal();
					} catch (RunnningException e1) {
						e1.printStackTrace();
					}
					enSureNormalLength();
					try {
						Thread.sleep(checkRate);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	protected void checkNormal() throws RunnningException {
		synchronized (normalthreads) {
			Iterator<FlexibleThread> it = normalthreads.iterator();
			ArrayList<FlexibleThread> timeouts = new ArrayList<>();
			while(it.hasNext()) {
				FlexibleThread thread = it.next();
				if(thread.isTimeOut()) {
					System.out.println(thread.getName()+" is timeout");
					thread.shutDown();
					it.remove();
					timeouts.add(thread);
				}
			}
			for(FlexibleThread t:timeouts){
				if(!t.isFinished()) {
//					System.out.println(t.getName()+" is finished");
					if(abnormalthreads.size()<getAbnormalThreadNum()) {
						abnormalthreads.add(t);
					}else {
						cleanAbnormalThreads();
						if(abnormalthreads.size()<getAbnormalThreadNum()) {
							abnormalthreads.add(t);
						}else {
							throw new RunnningException();
						}
					}
				}
			};
		}
	}

	private void cleanAbnormalThreads() {
		Iterator<FlexibleThread> it = abnormalthreads.iterator();
		while(it.hasNext()) {
			FlexibleThread thread = it.next();
			if(thread.isFinished()) {
				it.remove();
			}
		}
	}

	private void enSureNormalLength() {
		synchronized (normalthreads) {
			if(normalthreads.size()<getNormalThreadNum()) {
				for(int i=normalthreads.size();i<getNormalThreadNum();i++) {
					FlexibleThread thread = getNewThread();
					normalthreads.add(thread);
					thread.start();
				}
			} else {
				for(int i=getNormalThreadNum();i<normalthreads.size();i++) {
					FlexibleThread thread = normalthreads.remove(i);
					thread.shutDown();
					abnormalthreads.add(thread);
				}
			}
		}
	}

	private FlexibleThread getNewThread() {
		return flexibleThreadFactory.get(getTimeout(),tasks);
	}

	@Override
	public void start() {
		init();
	}

	@Override
	public void shutdown() {
		thread.interrupt();
		normalthreads.forEach(FlexibleThread::shutDown);
		inited = false;
	}

	public void printSelfMsg() {
		System.out.println("normal thread num is "+normalthreads.size());
		System.out.println("abnormal thread num is "+abnormalthreads.size());
	}

}
