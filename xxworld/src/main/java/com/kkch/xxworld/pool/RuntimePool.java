package com.kkch.xxworld.pool;

import com.kkch.xxworld.pool.impl.OTask;

/**
 * 游戏在线玩家存储池
 * @author CH
 *
 * @param <O>
 */
public interface RuntimePool<O> {

	/**
	 * 获得指定id的玩家
	 * 如果未登录则登录
	 * 如果已登录则更改其运行时uuid
	 * @param id
	 * @return
	 */
	O get(int id);
	
	/**
	 * 获得指定其运行时uuid的玩家
	 * @param uuid
	 * @return
	 */
	O freshAndGet(String uuid);
	
	/**
	 * 查询指定id的玩家
	 * 若不在线，则返回null
	 * @param id
	 * @return
	 */
	O query(String id);
	
	void fresh(String id,OTask<O> task);
	
}
