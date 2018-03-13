package com.kkch.xxworld.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Storage extends Storagable{

	public Storage(){
		super();
		this.maxItemNum = 16;
	}
	
}
