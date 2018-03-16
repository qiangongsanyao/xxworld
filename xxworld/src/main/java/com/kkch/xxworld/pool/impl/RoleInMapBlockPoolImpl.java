package com.kkch.xxworld.pool.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkch.xxworld.entity.MapBlock;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.pool.RoleInMapblockPool;

@Component
public class RoleInMapBlockPoolImpl implements RoleInMapblockPool {

	@Autowired
	RuntimeRolePool rolePool;
	
	HashMap<Integer,HashSet<Integer>> mapIdAndRoleMap = new HashMap<>();
	
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	@Override
	public void enter(Role role, MapBlock mapBlock) {
		lock.writeLock().lock();
		if(!mapIdAndRoleMap.containsKey(mapBlock.getId())) {
			mapIdAndRoleMap.put(mapBlock.getId(), new HashSet<>());
		}
		lock.writeLock().unlock();
		HashSet<Integer> roleIds = mapIdAndRoleMap.get(mapBlock.getId());
		lock.readLock().lock();
		synchronized (roleIds) {
			roleIds.add(role.getId());
		}
		lock.readLock().unlock();
	}

	@Override
	public Iterable<Role> roles(int mapId) {
		ArrayList<Role> roles = new ArrayList<>();
		lock.readLock().lock();
		HashSet<Integer> roleIds = mapIdAndRoleMap.get(mapId);
		lock.readLock().unlock();
		synchronized (roleIds) {
			ArrayList<Integer> removed = new ArrayList<>();
			roleIds.forEach(id->{
				Role role = rolePool.query(String.valueOf(id));
				if(role!=null&&role.getMapId()==mapId) {
					roles.add(role);
				}else {
					removed.add(id);
				}
			});
			roleIds.removeAll(removed);
		}
		return roles;
	}

}
