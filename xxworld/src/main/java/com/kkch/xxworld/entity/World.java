package com.kkch.xxworld.entity;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("world")
public class World {

	@Value("name")
	private String name;

	public World() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY)+"时"+calendar.get(Calendar.MINUTE)+"分";
	}

	@Override
	public String toString() {
		return "World [name=" + name + "]";
	}
	
	
	
}
