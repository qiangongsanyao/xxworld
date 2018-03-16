package com.kkch.xxworld.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.BackpackRepository;
import com.kkch.xxworld.dao.LevelRepository;
import com.kkch.xxworld.dao.RoleRepository;
import com.kkch.xxworld.dao.StatusRepository;
import com.kkch.xxworld.dao.StorageRepository;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.entity.Status;
import com.kkch.xxworld.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	StorageRepository storageRepository;
	@Autowired
	BackpackRepository backpackRepository;
	@Autowired
	LevelRepository levelRepository;
	@Autowired
	StatusRepository statusRepository;
	
	public RoleServiceImpl() {}

	@Override
	@Transactional
	public Role init(Role role) {
		statusRepository.save(role.getStatus());
		levelRepository.save(role.getLevel());
		backpackRepository.save(role.getBackpack());
		storageRepository.save(role.getStorage());
		return roleRepository.save(role);
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role findByName(String name) {
		Status status = statusRepository.findOneByName(name);
		if(status!=null) {
			return roleRepository.findOne(status.getRole().getId());
		}else {
			return null;
		}
	}

	@Override
	public Role findById(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public boolean queryName(String rolename) {
		return findByName(rolename)!=null;
	}

	@Override
	public void freshStatus(Role role) {
		role.setLevel(levelRepository.findOne(role.getLevel().getId()));
		role.setStatus(statusRepository.findOne(role.getStatus().getId()));
	}

}
