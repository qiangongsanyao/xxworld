package com.kkch.xxworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.pool.impl.RuntimeRolePool;
import com.kkch.xxworld.service.EnterService;
import com.kkch.xxworld.service.MapBlockService;
import com.kkch.xxworld.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	EnterService enterService;
	@Autowired
	RoleService roleService;
	@Autowired
	MapBlockService mapBlockService;
	@Autowired
	RuntimeRolePool runtimeRolePool;
	
	Object enterlocal = new Object();
	
	@RequestMapping("/enter")
	public String enter(String uuid,Model model) {
		if(uuid==null) {
			return "relogin";
		}
		Role role = enterService.getRole(uuid);
		if(role==null) {
			return "relogin";
		}
		try {
			role.getRuntime().getLock().lock();
			Role arole = runtimeRolePool.get(role.getId());
			model.addAttribute("uuid", arole.getRuntime().getRuntimeUUID());
			model.addAttribute("t", System.currentTimeMillis());
			addMaps(model,arole);
			if(role.getRuntime().getLock().isHeldByCurrentThread()) {
				role.getRuntime().getLock().unlock();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "relogin";
		}
		return "world";
	}

	private void addMaps(Model model, Role arole) {
		model.addAttribute("map", mapBlockService.getMapAndSetMap(arole));
		model.addAttribute("roles", mapBlockService.roles(arole));
		model.addAttribute("east",mapBlockService.getEastName(arole.getMapId()));
		model.addAttribute("west",mapBlockService.getWestName(arole.getMapId()));
		model.addAttribute("north",mapBlockService.getNorthName(arole.getMapId()));
		model.addAttribute("south",mapBlockService.getSouthName(arole.getMapId()));
	}
	
}
