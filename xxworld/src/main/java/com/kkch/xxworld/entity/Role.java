package com.kkch.xxworld.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class Role {

	public Role() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	@Column(unique=true)
	protected String name;
	protected Sex sex;

	@OneToOne
    @JoinColumn(name="level_id")
	protected Level level;
	
	@OneToOne
    @JoinColumn(name="storage_id")
	protected Storage storage;
	@OneToOne
    @JoinColumn(name="backpack_id")
	protected Backpack backpack;
	
	protected Integer mapId;

	@Transient
	protected Runtime runtime = new Runtime();
	
	public Runtime getRuntime() {
		return runtime;
	}

	public void setRuntime(Runtime runtime) {
		this.runtime = runtime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Level getLevel() {
		return level;
	}
	
	public Role(String name) {
		super();
		this.name = name;
		level = new Level(1, 0);
		storage = new Storage();
		backpack = new Backpack();
	}
	
	public Role(String name,Sex sex) {
		this(name);
		this.sex = sex;
		this.mapId = 1;
	}
	
	public Storage getStorage() {
		return storage;
	}

	public Backpack getBackpack() {
		return backpack;
	}
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public void setBackpack(Backpack backpack) {
		this.backpack = backpack;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", sex=" + sex + ", level=" + level
				+ ", \nstorage=" + storage
				+ ", \nbackpack=" + backpack + "]";
	}

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	
}
