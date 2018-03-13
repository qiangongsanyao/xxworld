package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.Role;

public interface RoleStatusService {

	int acceptableWeight(Role role);

	boolean overWeight(Role role);

	int acceptableCash(Role role);

}
