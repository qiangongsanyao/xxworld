package com.kkch.xxworld.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MapBlock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String detail;
	
	private int northid;
	private int southid;
	private int westid;
	private int eastid;
	
	private boolean safety;
	

	public int getId() {
		return id;
	}

	public int getNorthid() {
		return northid;
	}

	public int getSouthid() {
		return southid;
	}

	public int getWestid() {
		return westid;
	}

	public int getEastid() {
		return eastid;
	}

	public String getName() {
		return name;
	}

	public String getDetail() {
		return detail;
	}

	public boolean getSafety() {
		return safety;
	}

	
	@Override
	public String toString() {
		return "MapBlock [id=" + id + ", name=" + name + ", detail=" + detail + ", northid=" + northid + ", southid="
				+ southid + ", westid=" + westid + ", eastid=" + eastid + ", safety=" + safety + "]";
	}

	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MapBlock) {
			return id == ((MapBlock)obj).id;
		}
		return false;
	}
	
	
	
}
