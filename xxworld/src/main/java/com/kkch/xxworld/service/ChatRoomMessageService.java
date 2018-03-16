package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.ChatRoomMessage;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.MsgTooLongException;

public interface ChatRoomMessageService {

	Iterable<ChatRoomMessage> find(int page);

	ChatRoomMessage addChatRootMessage(Role role,String msg) throws MsgTooLongException;
	
}
