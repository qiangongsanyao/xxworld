package com.kkch.xxworld.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.BackpackRepository;
import com.kkch.xxworld.dao.ItemRepository;
import com.kkch.xxworld.dao.RoleRepository;
import com.kkch.xxworld.dao.StorageRepository;
import com.kkch.xxworld.entity.Backpack;
import com.kkch.xxworld.entity.Item;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.entity.Storage;
import com.kkch.xxworld.exception.NoSoMuchException;
import com.kkch.xxworld.exception.NoSuchItemException;
import com.kkch.xxworld.exception.SaveFailException;
import com.kkch.xxworld.exception.StorageFullException;
import com.kkch.xxworld.service.BackPackService;
import com.kkch.xxworld.service.RoleStatusService;

@Service
public class BackPackServiceImpl implements BackPackService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleStatusService roleStatusService;
	@Autowired
	StorageRepository storageRepository;
	@Autowired
	BackpackRepository backpackRepository;
	@Autowired
	ItemRepository itemRepository;
	
	public BackPackServiceImpl() {}

	@Override
	@Transactional
	public void store(Role role, Item item) throws NoSuchItemException, StorageFullException, SaveFailException {
		freshback(role);
		Backpack backpack = backpackRepository.findOne(role.getBackpack().getId());
		if(!backpack.getItems().contains(item)) {
			throw new NoSuchItemException();
		}
		Storage storage = storageRepository.findOne(role.getStorage().getId());
		if(storage.getItems().size()>=storage.getMaxItemNum()) {
			throw new StorageFullException();
		}
		backpack.getItems().remove(item);
		storage.getItems().add(item);
		try {
			item.setStoragable(storage);
			save(backpack, storage);
		} catch (Exception e) {
			backpack.getItems().add(item);
			storage.getItems().remove(item);
			item.setStoragable(backpack);
			e.printStackTrace();
			throw new SaveFailException(e);
		}
		packageBack(role);
	}

	@Override
	@Transactional
	public void freshback(Role role) {
		role.setBackpack(backpackRepository.findOne(role.getBackpack().getId()));
		role.setStorage(storageRepository.findOne(role.getStorage().getId()));
	}

	@Transactional
	private void packageBack(Role role) {
		Backpack back = backpackRepository.findOne(role.getBackpack().getId());
		Iterator<Item> it = back.getItems().iterator();
		while(it.hasNext()) {
			Item item = it.next();
			if(item.getNum()==0) {
				it.remove();
				item.setStoragable(null);
				itemRepository.save(item);
			}
		}
		freshback(role);
	}

	@Transactional
	private void save(Backpack backpack, Storage storage) {
		backpackRepository.save(backpack);
		storageRepository.save(storage);
	}

	@Override
	@Transactional
	public void receive(Role role, Item item) {
		Backpack backpack = role.getBackpack();
		backpack.getItems().add(item);
		item.setStoragable(backpack);
		role.setBackpack(backpackRepository.save(backpack));
	}	

	@Override
	@Transactional
	public void use(Role role, Item item, int num) throws NoSuchItemException, NoSoMuchException {
		role.setBackpack(backpackRepository.findOne(role.getBackpack().getId()));
		Backpack backpack = role.getBackpack();
		Item mItem = backpack.getItems().get(backpack.getItems().indexOf(item));
		if(mItem==null) {
			throw new NoSuchItemException();
		}
		if(mItem.getNum()<num) {
			throw new NoSoMuchException();
		}
		if(mItem.stackable()) {
			mItem.setNum(mItem.getNum()-num);
		}else {
			mItem.use(num);
		}
		itemRepository.save(mItem);
		packageBack(role);
	}

	@Override
	@Transactional
	public void withdraw(Role role, int num) throws StorageFullException,NoSuchItemException, SaveFailException {
		freshback(role);
		Storage storage = role.getStorage();
		if(storage.getItems().size()<num) {
			throw new NoSuchItemException();
		}
		Backpack backpack = role.getBackpack();
		if(backpack.getItems().size()>=backpack.getMaxItemNum()||roleStatusService.overWeight(role)) {
			throw new StorageFullException();
		}
		Item item = storage.getItems().remove(num);
		backpack.getItems().add(item);
		try {
			item.setStoragable(backpack);
			save(backpack, storage);
		} catch (Exception e) {
			backpack.getItems().remove(item);
			storage.getItems().add(num,item);
			item.setStoragable(storage);
			e.printStackTrace();
		}
		packageBack(role);
	}

	@Override
	public void give(Role provider, Role receiver, Item item)  throws StorageFullException, NoSuchItemException{
		Backpack receiverBackpack = receiver.getBackpack();
		if(receiverBackpack.getItems().size()>=receiverBackpack.getMaxItemNum()||roleStatusService.overWeight(receiver)) {
			throw new StorageFullException();
		}
		Backpack providerBackpack = backpackRepository.findOne(provider.getBackpack().getId());
		if(!providerBackpack.getItems().contains(item)) {
			throw new NoSuchItemException();
		}
		item.setStoragable(receiverBackpack);
		itemRepository.save(item);
		freshback(receiver);
		freshback(provider);
	}

	@Override
	@Transactional
	public void receive(Role role, int cash) {
		freshback(role);
		role.getBackpack().setCash(role.getBackpack().getCash()+cash);
		backpackRepository.save(role.getBackpack());
		freshback(role);
	}

	@Override
	@Transactional
	public void give(Role provider, Role receiver, int cash) throws NoSuchItemException, StorageFullException {
		Backpack providerBackpack = backpackRepository.findOne(provider.getBackpack().getId());
		if(providerBackpack.getCash()<cash) {
			throw new NoSuchItemException();
		}
		Backpack receiverBackpack = receiver.getBackpack();
		if(receiverBackpack.getCash()+cash>roleStatusService.acceptableCash(receiver)) {
			throw new StorageFullException();
		}
		providerBackpack.setCash(providerBackpack.getCash()-cash);
		receiverBackpack.setCash(receiverBackpack.getCash()+cash);
		backpackRepository.save(providerBackpack);
		backpackRepository.save(receiverBackpack);
		provider.setBackpack(backpackRepository.findOne(provider.getBackpack().getId()));
		receiver.setBackpack(backpackRepository.findOne(receiver.getBackpack().getId()));
	}

}
