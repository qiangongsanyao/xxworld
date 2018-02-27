package com.kkch.xxworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkch.xxworld.bean.World;

@Controller
public class WorldController {

	@Autowired
	private World world;
	
	@RequestMapping("/xxworld")
	public String xxworld(Model model) {
		model.addAttribute("world",world);
		return "xxworld";
	}
	
	
}
