package com.kkch.xxworld.entity;

public enum Sex {

	male("男"),female("女");
	
	public String value;
	
	Sex(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
