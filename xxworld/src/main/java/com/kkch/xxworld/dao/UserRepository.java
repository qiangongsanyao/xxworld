package com.kkch.xxworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkch.xxworld.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User getByName(String name);

}
