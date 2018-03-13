package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.Item;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.NoSoMuchException;
import com.kkch.xxworld.exception.NoSuchItemException;
import com.kkch.xxworld.exception.SaveFailException;
import com.kkch.xxworld.exception.StorageFullException;

public interface BackPackService {

	void store(Role role, Item item) throws StorageFullException,NoSuchItemException, SaveFailException;
	
	void receive(Role role, Item item);
	
	void receive(Role role, int cash);
	
	void withdraw(Role role,int num) throws StorageFullException, NoSuchItemException, SaveFailException;
	
	void use(Role role, Item item, int num) throws NoSuchItemException, NoSoMuchException;
	
	void give(Role provider,Role receiver,Item item) throws StorageFullException, NoSuchItemException, SaveFailException;
	
	void give(Role provider,Role receiver,int cash) throws StorageFullException, NoSuchItemException;
	
	default void use(Role role, Item item) throws NoSuchItemException, NoSoMuchException{
		use(role, item, 1);
	}

	void freshback(Role role);
	
}
