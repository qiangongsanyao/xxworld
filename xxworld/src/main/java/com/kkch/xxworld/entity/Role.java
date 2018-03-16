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
	@OneToOne
    @JoinColumn(name="status_id")
	protected Status status;

	@OneToOne
    @JoinColumn(name="level_id")
	protected Level level;
	
	@OneToOne
    @JoinColumn(name="storage_id")
	protected Storage storage;
	@OneToOne
    @JoinColumn(name="backpack_id")
	protected Backpack backpack;
	
	@Column(name="map_id")
	protected Integer mapId;

	@Transient
	protected Runtime runtime = new Runtime();
	
	public Runtime getRuntime() {
		return runtime;
	}

	public void setRuntime(Runtime runtime) {
		this.runtime = runtime;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return status.getName();
	}

	public Level getLevel() {
		return level;
	}
	
	public Role(String name) {
		super();
		status = new Status();
		status.setName(name);
		level = new Level(1, 0);
		storage = new Storage();
		backpack = new Backpack();
	}
	
	public Role(String name,Sex sex) {
		this(name);
		status.setSex(sex);
		this.mapId = 1;
	}
	
	public Storage getStorage() {
		return storage;
	}

	public Backpack getBackpack() {
		return backpack;
	}
	
	public Sex getSex() {
		return status.getSex();
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public void setBackpack(Backpack backpack) {
		this.backpack = backpack;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + status.getName() + ", sex=" + status.getSex() + ", level=" + level
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
