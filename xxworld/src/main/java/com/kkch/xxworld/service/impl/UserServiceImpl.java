package com.kkch.xxworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.UserRepository;
import com.kkch.xxworld.entity.User;
import com.kkch.xxworld.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{


	@Autowired
	UserRepository userRepository;
	
	@Override
	public User add(String name, String pass) throws Exception{
		User user = new User();
		user.setName(name);
		user.setPass(pass);
		if(userRepository.getByName(name)!=null) {
			throw new Exception("该用户名已存在");
		}
		try {
			userRepository.saveAndFlush(user);
			return user;
		}catch (Exception e) {
			throw new Exception("注册失败!");
		}
	}

	@Override
	public boolean validate(String name, String pass) throws Exception {
		User user = userRepository.getByName(name);
		return user!=null&&String.valueOf(user.getPass()).equals(pass);
	}

}
