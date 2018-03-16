package com.kkch.xxworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kkch.xxworld.dao.ChatRoomMessageRepository;
import com.kkch.xxworld.entity.ChatRoomMessage;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.MsgTooLongException;
import com.kkch.xxworld.service.ChatRoomMessageService;

@Service
public class ChatRoomMessageImpl implements ChatRoomMessageService {

	@Autowired
	ChatRoomMessageRepository chatRoomMessageRepository;
	
	@Override
	public Iterable<ChatRoomMessage> find(int page) {
		Iterable<ChatRoomMessage> msgs = chatRoomMessageRepository.findAll(new PageRequest(page, 10, Sort.Direction.DESC,"id"));
		return msgs;
	}

	@Override
	public ChatRoomMessage addChatRootMessage(Role role,String msg) throws MsgTooLongException {
		if(msg.length()>35) {
			throw new MsgTooLongException("消息太长了");
		}
		ChatRoomMessage chatRoomMessage = new ChatRoomMessage();
		chatRoomMessage.setRoleName(role.getName());
		chatRoomMessage.setRoleId(role.getId());
		chatRoomMessage.setMsg(msg);
		chatRoomMessageRepository.save(chatRoomMessage);
		return chatRoomMessage;
	}

}
