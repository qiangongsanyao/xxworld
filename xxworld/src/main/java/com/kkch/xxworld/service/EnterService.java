package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.Role;

public interface EnterService {

	void setUUID(Role role);
	
	Role getRole(String uuid);
	
}
