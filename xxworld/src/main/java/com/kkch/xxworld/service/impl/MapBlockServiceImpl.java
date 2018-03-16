package com.kkch.xxworld.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.MapblockRepository;
import com.kkch.xxworld.dao.RoleRepository;
import com.kkch.xxworld.entity.MapBlock;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.ImmovableException;
import com.kkch.xxworld.pool.RoleInMapblockPool;
import com.kkch.xxworld.service.MapBlockService;

@Service
public class MapBlockServiceImpl implements MapBlockService {

	@Autowired
	MapblockRepository mapblockRepository; 
	@Autowired
	RoleInMapblockPool roleInMapblockPool;
	@Autowired
	RoleRepository roleRepository;
	
	private Map<Integer, MapBlock> maps;
	
	private ArrayList<MapBlock> shows;
	
	private volatile int lastVersion;
	
	private volatile int version = 1;
	
	
	
	public int getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(int lastVersion) {
		this.lastVersion = lastVersion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public Iterable<MapBlock> getShows() {
		return shows;
	}

	@Override
	@Transactional
	public synchronized void freshMaps() {
		maps = Collections.unmodifiableMap(mapblockRepository.findAll().stream().collect(Collectors.toMap(MapBlock::getId, Function.identity())));
		shows = new ArrayList<>();
		for(MapBlock map:maps.values()) {
			if(map.isShowable()) {
				shows.add(map);
			}
		}
		lastVersion = version;
	}

	@Override
	public MapBlock getMap(Role role) {
		Integer id = role.getMapId();
		if(version>lastVersion) {
			freshMaps();
		}
		if(maps.get(id)==null) {
			id = 1;
		}
		return maps.get(id);
	}

	@Override
	public MapBlock getEast(Role role) {
		Integer id = role.getMapId();
		MapBlock mapBlock = maps.get(id);
		if(mapBlock==null) {
			return null;
		}
		return maps.get(mapBlock.getEastid());
	}

	@Override
	public MapBlock getNorth(Role role) {
		Integer id = role.getMapId();
		MapBlock mapBlock = maps.get(id);
		if(mapBlock==null) {
			return null;
		}
		return maps.get(mapBlock.getNorthid());
	}

	@Override
	public MapBlock getWest(Role role) {
		Integer id = role.getMapId();
		MapBlock mapBlock = maps.get(id);
		if(mapBlock==null) {
			return null;
		}
		return maps.get(mapBlock.getWestid());
	}

	@Override
	public MapBlock getSouth(Role role) {
		Integer id = role.getMapId();
		MapBlock mapBlock = maps.get(id);
		if(mapBlock==null) {
			return null;
		}
		return maps.get(mapBlock.getSouthid());
	}

	@Override
	public String format(MapBlock mapBlock) {
		if(mapBlock==null) {
			return null;
		}
		StringBuffer sb = new StringBuffer("id="+mapBlock.getId()+", ");
		sb.append("name="+mapBlock.getName());
		MapBlock map = maps.get(mapBlock.getNorthid());
		if(map!=null) {
			sb.append(", "+"north="+map.getName());
		}
		map = maps.get(mapBlock.getSouthid());
		if(map!=null) {
			sb.append(", "+"south="+map.getName());
		}
		map = maps.get(mapBlock.getWestid());
		if(map!=null) {
			sb.append(", "+"west="+map.getName());
		}
		map = maps.get(mapBlock.getEastid());
		if(map!=null) {
			sb.append(", "+"east="+map.getName());
		}
		return sb.toString();
	}

	@Override
	public void goEast(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getEastid());
		setMap(role, tm);
	}

	public MapBlock get(Integer id) throws ImmovableException {
		MapBlock mapBlock = maps.get(id);
		if(mapBlock==null) {
			throw new ImmovableException("没有这张地图");
		}
		return mapBlock;
	}

	@Override
	public void goNorth(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getNorthid());
		setMap(role, tm);
	}

	@Override
	public void goWest(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getWestid());
		setMap(role, tm);
	}

	@Override
	public void goSouth(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getSouthid());
		setMap(role, tm);
	}

	@Override
	public String getWestName(Integer mapId) {
		try {
			MapBlock mapBlock = get(mapId);
			if(mapBlock!=null) {
				MapBlock tm = get(mapBlock.getWestid());
				if(tm!=null)
					return tm.getName();
			}
		} catch (ImmovableException e) {
			
		}
		return null;
	}

	@Override
	public String getEastName(Integer mapId) {
		try {
			MapBlock mapBlock = get(mapId);
			if(mapBlock!=null) {
				MapBlock tm = get(mapBlock.getEastid());
				if(tm!=null)
					return tm.getName();
			}
		} catch (ImmovableException e) {
			
		}
		return null;
	}

	@Override
	public String getNorthName(Integer mapId) {
		try {
			MapBlock mapBlock = get(mapId);
			if(mapBlock!=null) {
				MapBlock tm = get(mapBlock.getNorthid());
				if(tm!=null)
					return tm.getName();
			}
		} catch (ImmovableException e) {
			
		}
		return null;
	}

	@Override
	public String getSouthName(Integer mapId) {
		try {
			MapBlock mapBlock = get(mapId);
			if(mapBlock!=null) {
				MapBlock tm = get(mapBlock.getSouthid());
				if(tm!=null)
					return tm.getName();
			}
		} catch (ImmovableException e) {
			
		}
		return null;
	}

	@Override
	public void goTo(Role role, int mapId) throws ImmovableException {
		MapBlock tm = get(mapId);
		setMap(role, tm);
	}

	@Transactional
	private void setMap(Role role, MapBlock tm) {
		role.setMapId(tm.getId());
		roleInMapblockPool.enter(role, tm);
		roleRepository.updateMapId(role.getId(), tm.getId());
	}

	@Override
	public ArrayList<Role> roles(Role role){
		int mapId = role.getMapId();
		ArrayList<Role> roles= new ArrayList<>();
		for(Role r:roleInMapblockPool.roles(mapId)) {
			if(r.getId()!=(role.getId())) {
				roles.add(r);
			}
		}
		Collections.reverse(roles);
		return roles;
	}
	
	@Override
	public MapBlock getMapAndSetMap(Role arole) {
		MapBlock mapBlock = getMap(arole);
		setMap(arole, mapBlock);
		return mapBlock;
	}

}
