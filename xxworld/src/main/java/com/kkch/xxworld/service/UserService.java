package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.User;
import com.kkch.xxworld.exception.ValidateException;

public interface UserService {
	
	User add(String name,String pass) throws ValidateException;
	
	boolean validate(String name,String pass) throws ValidateException;
	
}
