package com.kkch.xxworld.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.RoleRecordRepository;
import com.kkch.xxworld.dao.RoleRepository;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.entity.RoleRecord;
import com.kkch.xxworld.entity.Sex;
import com.kkch.xxworld.service.RoleRecordService;
import com.kkch.xxworld.service.RoleService;

@Service
public class RoleRecordServiceImpl implements RoleRecordService {

	@Autowired
	RoleRecordRepository roleRecordRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleService roleService;
	
	@Override
	@Transactional
	public List<Role> getRoles(String username) {
		List<Role> roles = new ArrayList<>();
		List<RoleRecord> rolerecords = roleRecordRepository.findByUserName(username);
		if(rolerecords==null||rolerecords.isEmpty()) {
			
		} else {
			rolerecords.forEach(rr->{
				try {
					Role role = roleRepository.findOne(rr.getRoleId());
					if(role!=null) {
						roles.add(role);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		return roles;
	}

	@Override
	@Transactional
	public Role addRole(String username, String rolename, Sex sex) {
		Role role = new Role(rolename, sex);
		role = roleService.init(role);
		RoleRecord rr = new RoleRecord();
		rr.setRoleId(role.getId());
		rr.setUserName(username);
		rr = roleRecordRepository.save(rr);
		return roleRepository.findOne(rr.getId());
	}

	@Override
	@Transactional
	public List<Role> getRoles(Role arole) {
		List<Role> roles = new ArrayList<>();
		RoleRecord rrt = roleRecordRepository.findOneByRoleId(arole.getId());
		String username = null;
		if(rrt!=null) {
			username = rrt.getUserName();
		}
		List<RoleRecord> rolerecords = roleRecordRepository.findByUserName(username);
		System.out.println(rolerecords);
		if(rolerecords==null||rolerecords.isEmpty()) {
			
		} else {
			rolerecords.forEach(rr->{
				try {
					Role role = roleRepository.findOne(rr.getRoleId());
					if(role!=null) {
						roles.add(role);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		return roles;
	}

}
