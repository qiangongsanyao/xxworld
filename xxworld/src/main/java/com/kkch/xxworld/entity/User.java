package com.kkch.xxworld.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true,nullable=false)
	private String name;
	
	@Column(unique=false,nullable=false)
	private String pass;
	
	private Date date;
	
	public User(Integer id, String name, String pass) {
		this();
		this.id = id;
		this.name = name;
		this.pass = pass;
	}
	
	public User() {
		super();
		this.date = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass + ", date=" + date + "]";
	}
	
}
