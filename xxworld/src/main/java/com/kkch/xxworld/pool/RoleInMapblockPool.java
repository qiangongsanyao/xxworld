package com.kkch.xxworld.pool;

import com.kkch.xxworld.entity.MapBlock;
import com.kkch.xxworld.entity.Role;

/**
 * 存储地图上玩家
 * @author CH
 *
 */
public interface RoleInMapblockPool {

	/**
	 * 玩家进入地图
	 * @param role
	 * @param mapBlock
	 */
	void enter(Role role,MapBlock mapBlock);
	
	/**
	 * 查询地图上的玩家
	 * @param mapId
	 * @return
	 */
	Iterable<Role> roles(int mapId);
	
}
