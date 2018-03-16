package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.Role;

public interface RoleService {

	Role init(Role role);

	void save(Role role);
	
	Role findByName(String name);

	Role findById(Integer id);

	boolean queryName(String rolename);

	void freshStatus(Role role);

}
