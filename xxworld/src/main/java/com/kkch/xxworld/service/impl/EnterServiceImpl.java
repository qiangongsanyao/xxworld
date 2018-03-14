package com.kkch.xxworld.service.impl;

import org.springframework.stereotype.Service;

import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.pool.impl.TimedPool;
import com.kkch.xxworld.service.EnterService;

@Service
public class EnterServiceImpl implements EnterService {

	TimedPool<Role> rolepool = new TimedPool<>();
	
	@Override
	public void setUUID(Role role) {
		String uuid = rolepool.put(role);
		role.getRuntime().setLoginId(uuid);
	}

	@Override
	public Role getRole(String uuid) {
		return rolepool.get(uuid);
	}

}
