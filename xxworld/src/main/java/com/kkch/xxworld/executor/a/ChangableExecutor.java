package com.kkch.xxworld.executor.a;

import com.kkch.xxworld.executor.i.FlexibleExecutor;

/**
 * 任务执行器
 * @author Ch
 *
 */
public abstract class ChangableExecutor implements FlexibleExecutor {

	/**
	 * 默认正常运行的任务数量
	 */
	public final static int defaultNormalThreadNum = 10;
	
	/**
	 * 默认超时的最大任务数量
	 */
	public final static int defaultAbnormalThreadNum = 50;
	
	private int normalThreadNum;
	
	private int abnormalThreadNum;
	
	private int timeout = 10000;
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getNormalThreadNum() {
		return normalThreadNum;
	}

	public void setNormalThreadNum(int normalThreadNum) {
		this.normalThreadNum = normalThreadNum;
	}

	public int getAbnormalThreadNum() {
		return abnormalThreadNum;
	}

	public void setAbnormalThreadNum(int abnormalThreadNum) {
		this.abnormalThreadNum = abnormalThreadNum;
	}
	
	public ChangableExecutor() {
		this(defaultNormalThreadNum,defaultAbnormalThreadNum);
	}

	public ChangableExecutor(int normalThreadNum, int abnormalThreadNum) {
		super();
		this.normalThreadNum = normalThreadNum;
		this.abnormalThreadNum = abnormalThreadNum;
	}

}
