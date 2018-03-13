package com.kkch.xxworld.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.ImmovableException;
import com.kkch.xxworld.service.MapBlockService;
import com.kkch.xxworld.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapBlockServiceImplTest {

	@Autowired
	MapBlockService mapBlockService;
	@Autowired
	RoleService roleService;
	
	Role role;
	
	@Before
	public void before() {
		mapBlockService.freshMaps();
		role = roleService.findById(1);
	}
	
	@Test
	public void testAllMaps() throws ImmovableException {
		System.out.println(mapBlockService.format(mapBlockService.getMap(role)));
		System.out.println(mapBlockService.format(mapBlockService.getSouth(role)));
		System.out.println(mapBlockService.format(mapBlockService.getNorth(role)));
		System.out.println(mapBlockService.format(mapBlockService.getWest(role)));
		System.out.println(mapBlockService.format(mapBlockService.getEast(role)));
		mapBlockService.goNorth(role);
		System.out.println(mapBlockService.format(mapBlockService.getMap(role)));
		mapBlockService.goSouth(role);
		mapBlockService.goSouth(role);
		System.out.println(mapBlockService.format(mapBlockService.getMap(role)));
		mapBlockService.goNorth(role);
		mapBlockService.goWest(role);
		System.out.println(mapBlockService.format(mapBlockService.getMap(role)));
		mapBlockService.goEast(role);
		mapBlockService.goEast(role);
		System.out.println(mapBlockService.format(mapBlockService.getMap(role)));
	}

}
