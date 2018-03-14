package com.kkch.xxworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

import com.kkch.xxworld.dao.UserRepository;
import com.kkch.xxworld.service.MapBlockService;

@SpringBootApplication
public class XxworldApplication{

	@Autowired
	JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(XxworldApplication.class, args);
	}

}
