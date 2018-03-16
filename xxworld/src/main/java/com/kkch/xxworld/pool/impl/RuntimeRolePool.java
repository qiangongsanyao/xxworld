package com.kkch.xxworld.pool.impl;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkch.xxworld.container.World;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.pool.RuntimePool;
import com.kkch.xxworld.service.RoleService;

@Component
public class RuntimeRolePool implements RuntimePool<Role>{

	@Autowired
	World world;
	
	TimedPool<Role> uuidpool = new TimedPool<>();
	TimedPool<Role> idpool = new TimedPool<>();
	
	@Autowired
	RoleService roleService;
	
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	@Override
	public Role get(int id) {
		lock.readLock().lock();
		Role role = idpool.getAndFresh(String.valueOf(id));
		if(role == null) {
			role = roleService.findById(id);
			idpool.put(role,String.valueOf(id));
		}
		String uuid = (uuidpool.put(role));
		role.getRuntime().setRuntimeUUID(uuid);
		lock.readLock().unlock();
		return role;
	}

	@Override
	public Role freshAndGet(String uuid) {
		if(uuid==null) {
			return null;
		}
		lock.readLock().lock();
		Role role = uuidpool.getAndFresh(uuid);
		if(role != null) {
			if(uuid.equals(role.getRuntime().getRuntimeUUID())) {
				role = idpool.getAndFresh(String.valueOf(role.getId()));
			} else {
				uuidpool.remove(uuid);
				role = null;
			}
		}
		lock.readLock().unlock();
		return role;
	}

	@Override
	public void fresh(String id, OTask<Role> task) {
		Role role = freshAndGet(id);
		if(id!=null) {
			task.set(role);
			world.submit(task);
		}
	}

	@Override
	public Role query(String id) {
		return idpool.get(id);
	}

}
