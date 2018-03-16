package com.kkch.xxworld.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//选择继承策略
@DiscriminatorColumn(name="type")//配置鉴别器
@DiscriminatorValue("0")
public class Item {

	public Item() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int property = 0;
	
	private String name;	
	
	private int num = 1;

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="storagable_id")
	private Storagable storagable;
	
	private int peerWeight = 10;
	
	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}

	public Item(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		if(num==this.num) {
			return;
		}
		if(!stackable()) {
			throw new RuntimeException(getName()+"不可以叠加");
		}
		this.num = num;
	}
	
	public void use(int num) {
		if(num<1) {
			throw new RuntimeException("无法使用");
		}
		this.num -= num;
	}

	protected boolean stackable;
	
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}
	

	public Storagable getStoragable() {
		return storagable;
	}

	public void setStoragable(Storagable storagable) {
		this.storagable = storagable;
	}

	public boolean stackable() {
		return stackable;
	}
	
	public int weight() {
		return peerWeight*num;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", num=" + num + ", storagable=" + (storagable!=null?storagable.id:"") + ", peerWeight="
				+ peerWeight + ", stackable=" + stackable + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Item) {
			return id == ((Item)obj).getId();
		}
		return false;
	}
	
	
}
