package com.kkch.xxworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{
	
	@Query(value="update role set map_id = ?2 where id = ?1",nativeQuery = true)
	@Transactional
	@Modifying(clearAutomatically = true)
	void updateMapId(int roleid,int mapid);
	
}
