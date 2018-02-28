package com.kkch.xxworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kkch.xxworld.entity.World;

@Controller
public class WorldController {

	@Autowired
	private World world;
	
	@RequestMapping(path="/xxworld.cmd")
	public String xxworld(Model model,@RequestParam("uid")int id) {
		model.addAttribute("world",world);
		return "xxworld";
	}
	
	
}
