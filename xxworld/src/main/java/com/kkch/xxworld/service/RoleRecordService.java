package com.kkch.xxworld.service;

import java.util.List;

import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.entity.Sex;

public interface RoleRecordService {

	List<Role> getRoles(String username);
	
	Role addRole(String username,String rolename,Sex sex);
	
}
