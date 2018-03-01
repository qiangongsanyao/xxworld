package com.kkch.xxworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

import com.kkch.xxworld.dao.UserRepository;
import com.kkch.xxworld.msg.LaunchMsg;

@SpringBootApplication
public class XxworldApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(XxworldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.send("server",new LaunchMsg());
	}
}
