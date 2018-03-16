package com.kkch.xxworld.pool;

import com.kkch.xxworld.bean.Cmd;

/**
 * 存储及验证指定uuid的操作
 * @author CH
 *
 */
public interface UuidCmdPool {

	void add(String uuid,Cmd cmd);
	
	boolean validate(String uuid,Cmd cmd);
	
}
