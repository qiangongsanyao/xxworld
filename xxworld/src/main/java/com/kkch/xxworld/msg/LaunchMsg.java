package com.kkch.xxworld.msg;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class LaunchMsg implements MessageCreator{

	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage("服务器启动.."+" 启动时间为:"+new Date());
	}

}
