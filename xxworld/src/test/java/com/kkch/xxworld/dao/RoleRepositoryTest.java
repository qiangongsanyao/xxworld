package com.kkch.xxworld.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkch.xxworld.entity.Level;
import com.kkch.xxworld.entity.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

	@Autowired
	RoleRepository roleDao;
	
	Role role;
	
	@Before
	public void before() {
		role = new Role("恋骑士丶");
	}
	
	@Test
	public void testSaveS() {
		System.out.println(role);
		roleDao.deleteAll();
		roleDao.save(role);
		System.out.println(roleDao.findOne(role.getId()));
	}

	@Test
	public void testFindOneID() {
		
	}
	
	
	static class Person{
		
		boolean 辞职() {
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		
		Person 我 = new Person();
		Person 女朋友  = new Person();
		
		
		
		
	}

}
