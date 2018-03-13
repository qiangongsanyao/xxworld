package com.kkch.xxworld.service;

import com.kkch.xxworld.entity.MapBlock;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.ImmovableException;

public interface MapBlockService {

	MapBlock getMap(Role role);
	
	MapBlock getEast(Role role);
	
	MapBlock getNorth(Role role);
	
	MapBlock getWest(Role role);
	
	MapBlock getSouth(Role role);
	
	void goEast(Role role) throws ImmovableException;
	
	void goNorth(Role role) throws ImmovableException;
	
	void goWest(Role role) throws ImmovableException;
	
	void goSouth(Role role) throws ImmovableException;

	void freshMaps();
	
	String format(MapBlock mapBlock);
	
}
