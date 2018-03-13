package com.kkch.xxworld.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//选择继承策略
@DiscriminatorColumn(name="type")//配置鉴别器
@DiscriminatorValue("0")
public class Storagable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	public Storagable() {}
	
	protected int maxItemNum;
	
	public int getMaxItemNum() {
		return maxItemNum;
	}

	public void setMaxItemNum(int maxItemNum) {
		this.maxItemNum = maxItemNum;
	}

	@OneToMany(cascade=CascadeType.ALL, 
			fetch = FetchType.EAGER,
			mappedBy="storagable")
	protected List<Item> items = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItems(Item item) {
		if(item.getStoragable()!=null) {
			item.getStoragable().getItems().remove(item);
		}
		item.setStoragable(this);
		this.items.add(item);
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Storagable [id=" + id + ", maxItemNum=" + maxItemNum + ", items=" + items + "]";
	}

	
}
