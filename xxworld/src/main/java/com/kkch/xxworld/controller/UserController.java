package com.kkch.xxworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kkch.xxworld.entity.User;
import com.kkch.xxworld.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/signup")
	public @ResponseBody String signUp(String name,String pass) {
		if(name==null||pass==null) {
			return "用户名或密码不合法";
		}
		User user = null;
		try {
			user = userService.add(name, pass);
			if(user == null || user.getId() == null) {
				throw new Exception("未知错误");
			}
		}catch (Exception e) {
			return "注册出错，错误信息为:"+e.getMessage();
		}
		return "注册成功";
	}
	
	@RequestMapping(value="/signin")
	public @ResponseBody Boolean signIn(String name,String pass) {
		if(name==null||pass==null) {
			return false;
		}
		try {
			return userService.validate(name, pass);
		}catch (Exception e) {
			return false;
		}
	}
	
}
