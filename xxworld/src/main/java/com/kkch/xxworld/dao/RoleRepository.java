package com.kkch.xxworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kkch.xxworld.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{

	Role findByName(String name);
	
}
