package com.kkch.xxworld.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkch.xxworld.entity.RoleRecord;

public interface RoleRecordRepository extends JpaRepository<RoleRecord, Integer> {

	List<RoleRecord> findByUserName(String username);

}
