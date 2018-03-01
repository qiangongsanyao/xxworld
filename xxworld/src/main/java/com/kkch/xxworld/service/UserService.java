package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.User;

public interface UserService {
	
	User add(String name,String pass) throws Exception;
	
	boolean validate(String name,String pass) throws Exception;
	
}
