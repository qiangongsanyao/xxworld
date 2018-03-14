package com.kkch.xxworld.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkch.xxworld.entity.Role;
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
			Role arole = roleService.getRole(role.getId());
			model.addAttribute("map", mapBlockService.getMap(arole));
			model.addAttribute("uuid", arole.getRuntime().getRuntimeUUID());
			addMaps(model,arole.getMapId());
		} catch (Exception e) {
			e.printStackTrace();
			return "relogin";
		}
		return "world";
	}

	private void addMaps(Model model, Integer integer) {
		model.addAttribute("east",mapBlockService.getEastName(integer));
		model.addAttribute("west",mapBlockService.getWestName(integer));
		model.addAttribute("north",mapBlockService.getNorthName(integer));
		model.addAttribute("south",mapBlockService.getSouthName(integer));
	}
	
}
