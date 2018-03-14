package com.kkch.xxworld.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Level {

	public Level() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int level = 1;
	
	private int exp = 0;

	public Level(int level, int exp) {
		super();
		this.level = level;
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	

	@Override
	public String toString() {
		return "Level [level=" + level + ", exp=" + exp + "]";
	}
	
	
}
