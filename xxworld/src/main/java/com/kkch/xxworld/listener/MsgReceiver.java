package com.kkch.xxworld.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgReceiver {

//	@JmsListener(destination="server")
	public void reserve(String message) {
		System.out.println("收到消息："+message);
	}
	
}
