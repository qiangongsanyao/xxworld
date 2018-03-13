package com.kkch.xxworld.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Backpack extends Storagable {

	private Integer cash;

	public Integer getCash() {
		if(cash == null) {
			cash = 0;
		}
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public Backpack() {
		super();
		this.maxItemNum = 25;
		this.cash = 0;
	}
	
	public int totalWeight() {
		int weight = 0;
		for(Item item:items) {
			weight += item.weight();
		}
		return weight;
	}
	
}
