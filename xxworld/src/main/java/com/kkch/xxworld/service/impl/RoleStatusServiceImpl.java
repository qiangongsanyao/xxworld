package com.kkch.xxworld.service.impl;

import org.springframework.stereotype.Service;

import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.service.RoleStatusService;

@Service
public class RoleStatusServiceImpl implements RoleStatusService {

	@Override
	public int acceptableWeight(Role role) {
		synchronized (role.getLevel()) {
			return  role.getLevel().getLevel()*100 + 500;
		}
	}
	
	@Override
	public int acceptableCash(Role role) {
		synchronized (role.getLevel()) {
			return  role.getLevel().getLevel()*2000000 + 200000000;
		}
	}

	@Override
	public boolean overWeight(Role role) {
		return role.getBackpack().totalWeight()>=acceptableWeight(role);
	}

}
