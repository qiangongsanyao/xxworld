package com.kkch.xxworld.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkch.xxworld.entity.Item;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.NoSoMuchException;
import com.kkch.xxworld.exception.NoSuchItemException;
import com.kkch.xxworld.exception.SaveFailException;
import com.kkch.xxworld.exception.StorageFullException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {

	@Autowired
	RoleService roleService;

	@Autowired
	BackPackService backPackService;
	
	@Autowired
	RoleStatusService roleStatusService;

	Role role;
	Role role2;
	
	@Before
	public void before() {
		role = roleService.findById(1);
		role2 = roleService.findById(2);
	}
	
	@Test
	public void test() throws SaveFailException, StorageFullException, NoSuchItemException, NoSoMuchException {
		backPackService.receive(role, 100000000);
		backPackService.receive(role2, 100000);
		backPackService.receive(role, new Item("麻痹戒指"));
		backPackService.receive(role, new Item("屠龙宝刀"));
		backPackService.give(role, role2, 10000);
	}
	
	public Item getItem(String name) {
		Item item = new Item();
		item.setName(name);
		item.setStackable(false);
		item.setNum(1);
		return item;
	}
	
	public Item getItem(String name,int num) {
		Item item = new Item();
		item.setStackable(true);
		item.setName(name);
		item.setNum(num);
		return item;
	}
	
	public Item getItem(String name,int num, boolean stackable) {
		Item item = new Item();
		item.setName(name);
		item.setStackable(stackable);
		item.setNum(num);
		return item;
	}
	
	@org.junit.After
	public void after() {
		System.out.println(role.getName());
		System.out.println("【背包】："+role.getBackpack().getItems().size()+"/"+role.getBackpack().getMaxItemNum());
		System.out.println("金币："+role.getBackpack().getCash());
		System.out.println("负重率："+role.getBackpack().totalWeight()*100/roleStatusService.acceptableWeight(role)+"%");
		
		role.getBackpack().getItems().forEach(System.out::println);
		System.out.println("【仓库】："+role.getStorage().getItems().size()+"/"+role.getStorage().getMaxItemNum());
		role.getStorage().getItems().forEach(System.out::println);
		
		
		System.out.println(role2.getName());
		System.out.println("【背包】："+role2.getBackpack().getItems().size()+"/"+role2.getBackpack().getMaxItemNum());
		System.out.println("金币："+role2.getBackpack().getCash());
		System.out.println("负重率："+role2.getBackpack().totalWeight()*100/roleStatusService.acceptableWeight(role2)+"%");
		role2.getBackpack().getItems().forEach(System.out::println);
		System.out.println("【仓库】："+role2.getStorage().getItems().size()+"/"+role2.getStorage().getMaxItemNum());
		role2.getStorage().getItems().forEach(System.out::println);
	}

}
