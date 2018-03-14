package com.kkch.xxworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.UserRepository;
import com.kkch.xxworld.entity.User;
import com.kkch.xxworld.exception.ValidateException;
import com.kkch.xxworld.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{


	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public User add(String name, String pass) throws ValidateException{
		User user = new User();
		user.setName(name);
		user.setPass(pass);
		User u = null;
		try {
			u = userRepository.getByName(name);
		} catch (Exception e) {
			throw new ValidateException("未知错误!");
		}
		if(u!=null) {
			throw new ValidateException("该用户名已存在");
		}
		try {
			userRepository.saveAndFlush(user);
			return user;
		}catch (Exception e) {
			throw new ValidateException("注册失败!");
		}
	}

	@Override
	@Transactional
	public boolean validate(String name, String pass) throws ValidateException {
		User user = null;
		try {
			 user = userRepository.getByName(name);
		}catch (Exception e) {
			throw new ValidateException("注册失败!");
		}
		return user!=null&&String.valueOf(user.getPass()).equals(pass);
	}

}
