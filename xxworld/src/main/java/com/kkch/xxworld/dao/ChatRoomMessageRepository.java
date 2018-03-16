package com.kkch.xxworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkch.xxworld.entity.ChatRoomMessage;

public interface ChatRoomMessageRepository extends JpaRepository<ChatRoomMessage, Integer> {

	
	
}
