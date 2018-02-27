package com.kkch.xxworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

	@RequestMapping("/fresh")
	public String fresh(Model model) {
		return "fresh";
	}
	
}
