package com.kkch.xxworld.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkch.xxworld.dao.MapblockRepository;
import com.kkch.xxworld.entity.MapBlock;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.ImmovableException;
import com.kkch.xxworld.service.MapBlockService;

@Service
public class MapBlockServiceImpl implements MapBlockService {

	@Autowired
	MapblockRepository mapblockRepository; 
	
	private Map<Integer, MapBlock> maps;
	
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
	@Transactional
	public synchronized void freshMaps() {
		maps = Collections.unmodifiableMap(mapblockRepository.findAll().stream().collect(Collectors.toMap(MapBlock::getId, Function.identity())));
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
		role.setMapId(tm.getId());
	}

	public MapBlock get(Integer id) throws ImmovableException {
		MapBlock mapBlock = maps.get(id);
		if(mapBlock==null) {
			throw new ImmovableException();
		}
		return mapBlock;
	}

	@Override
	public void goNorth(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getNorthid());
		role.setMapId(tm.getId());
	}

	@Override
	public void goWest(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getWestid());
		role.setMapId(tm.getId());
	}

	@Override
	public void goSouth(Role role) throws ImmovableException {
		MapBlock mapBlock = get(role.getMapId());
		MapBlock tm = get(mapBlock.getSouthid());
		role.setMapId(tm.getId());
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return null;
	}

}
