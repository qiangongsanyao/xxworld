package com.kkch.xxworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kkch.xxworld.entity.Status;

public interface StatusRepository extends JpaRepository<Status,Integer>{

	Status findOneByName(String name);

}
