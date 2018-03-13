package com.kkch.xxworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.BackpackRepository;
import com.kkch.xxworld.dao.RoleRepository;
import com.kkch.xxworld.dao.StorageRepository;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	StorageRepository storageRepository;
	@Autowired
	BackpackRepository backpackRepository;
	
	public RoleServiceImpl() {}

	@Override
	@Transactional
	public Role init(Role role) {
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
		return roleRepository.findByName(name);
	}

	@Override
	public Role findById(Integer id) {
		return roleRepository.findOne(id);
	}

}
