package com.kkch.xxworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.entity.Sex;
import com.kkch.xxworld.exception.ValidateException;
import com.kkch.xxworld.service.EnterService;
import com.kkch.xxworld.service.RoleRecordService;
import com.kkch.xxworld.service.RoleService;
import com.kkch.xxworld.service.UserService;

@Controller 
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleRecordService rrs;
	@Autowired
	EnterService enterService;
	
	@RequestMapping("/input")
	public String input() {
		return "user-input";
	}
	
	@RequestMapping("/create-input")
	public String createInput() {
		return "create-input";
	}
	
	@RequestMapping("/create-role")
	public String createRole(HttpServletRequest request,Model model) {
		String username =  null;
		Object object = request.getSession().getAttribute("username");
		if(object!=null&&object instanceof String) {
			username = (String) object;
		}
		if(username==null) {
			model.addAttribute("error","尚未登录");
			return "user-input";
		}
		String rolename = request.getParameter("rolename");
		String sex = request.getParameter("sex");
		if(rolename==null||rolename.length()==0) {
			model.addAttribute("error","角色名不能为空");
			return "create-input";
		}
		if(rolename.length()>6) {
			model.addAttribute("error","角色名不能超过六位");
			return "create-input";
		}
		Sex s = sex.equals("male")?Sex.male:sex.equals("female")?Sex.female:null;
		if(s==null) {
			model.addAttribute("error","请选择性别");
			return "create-input";
		}
		try {
			try {
				Role[] roles = (Role[]) request.getSession().getAttribute("roles");
				if(roles.length>=5) {
					model.addAttribute("error","可创建角色已满暂时无法创建新角色");
					return "login";	
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(roleService.queryName(rolename)) {
				model.addAttribute("error","用户名已存在");
				return "create-input";
			}
			rrs.addRole(username, rolename, s);
			try {
				List<Role> rolelist =  rrs.getRoles((String)request.getSession().getAttribute("username"));
				rolelist.forEach(enterService::setUUID);
				Role[] roles = new Role[rolelist.size()];
				roles = rolelist.toArray(roles);
				request.getSession().setAttribute("roles", roles);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "login";
		}catch (Exception e) {
			model.addAttribute("error","未知错误");
			return "create-input";
		}
	}
	
	@RequestMapping("/login.action")
	public String login(HttpServletRequest request,Model model) {
		if(validate(request,model)) {
			try {
				List<Role> rolelist =  rrs.getRoles((String)request.getSession().getAttribute("username"));
				rolelist.forEach(enterService::setUUID);
				Role[] roles = new Role[rolelist.size()];
				roles = rolelist.toArray(roles);
				request.getSession().setAttribute("roles", roles);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "login";
		}else {
			return "user-input";
		}
	}
	
	

	private boolean validate(HttpServletRequest request,Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username==null) {
			model.addAttribute("error","账户名为空");
			return false;
		}
		if(password==null) {
			model.addAttribute("error","密码为空");
			return false;
		}
		try {
			if(userService.validate(username, password)) {
				request.getSession().setAttribute("username", username);
				return true;
			}else {
				model.addAttribute("error","账户名不存在或者密码错误");
				return false;
			}
		} catch (ValidateException e) {
			model.addAttribute("error",e.getMessage());
			return false;
		}catch (Exception e) {
			model.addAttribute("error","未知错误");
			return false;
		}
	}
	
}
