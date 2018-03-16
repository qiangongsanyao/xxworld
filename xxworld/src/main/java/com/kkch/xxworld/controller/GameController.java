package com.kkch.xxworld.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkch.xxworld.bean.Cmd;
import com.kkch.xxworld.entity.ChatRoomMessage;
import com.kkch.xxworld.entity.Item;
import com.kkch.xxworld.entity.MapBlock;
import com.kkch.xxworld.entity.Role;
import com.kkch.xxworld.exception.ImmovableException;
import com.kkch.xxworld.exception.MsgTooLongException;
import com.kkch.xxworld.exception.UuidInvalidException;
import com.kkch.xxworld.pool.UuidCmdPool;
import com.kkch.xxworld.pool.impl.RuntimeRolePool;
import com.kkch.xxworld.service.BackPackService;
import com.kkch.xxworld.service.ChatRoomMessageService;
import com.kkch.xxworld.service.EnterService;
import com.kkch.xxworld.service.MapBlockService;
import com.kkch.xxworld.service.RoleRecordService;
import com.kkch.xxworld.service.RoleService;
import com.kkch.xxworld.service.RoleStatusService;

@Controller
@RequestMapping("/game")
public class GameController {


	@Autowired
	RuntimeRolePool runtimeRolePool;
	@Autowired
	MapBlockService mapBlockService;
	@Autowired
	UuidCmdPool uuidCmdPool;
	
	@ModelAttribute
	public void validateUUID(String uuid,Model model,String pmr) {
		if(uuid==null) {
			throw new UuidInvalidException();
		}
		Role role = runtimeRolePool.freshAndGet(uuid);
		if(role==null) {
			throw new UuidInvalidException();
		}
		model.addAttribute("role", role);
		model.addAttribute("t", System.currentTimeMillis());
		model.addAttribute("uuid", uuid);
		if(pmr!=null) {
			model.addAttribute("pmr", pmr);
		}
	}
	
	@RequestMapping("/fresh")
	public String fresh(@ModelAttribute("role") Role role,Model model) {
		addMaps(model, role);
		return "world";
	}
	
	@RequestMapping("/moreroles")
	public String moreroles(@ModelAttribute("role") Role role,Model model) {
		addMaps(model, role);
		return "moreroles";
	}
	
	@RequestMapping("/lookrole")
	public String lookrole(@ModelAttribute("role") Role role,Model model,Integer roleid) {
		
		if(roleid!=null) {
			Role targetrole = runtimeRolePool.query(String.valueOf(roleid));
			if(targetrole!=null) {
				model.addAttribute("targetrole", targetrole);				
			}
		}
		
		return "lookrole";
	}
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/status")
	public String status(@ModelAttribute("role") Role role,Model model) {
		roleService.freshStatus(role);
		return "status";
	}
	
	@RequestMapping("/friend")
	public String friend(@ModelAttribute("role") Role role,Model model) {
		
		return "friend";
	}
	
	@RequestMapping("/mail")
	public String mail(@ModelAttribute("role") Role role,Model model) {
		
		return "mail";
	}
	
	@RequestMapping("/house")
	public String house(@ModelAttribute("role") Role role,Model model) {
		
		return "house";
	}
	
	@RequestMapping("/league")
	public String league(@ModelAttribute("role") Role role,Model model) {
		
		return "league";
	}
	
	@RequestMapping("/skill")
	public String skill(@ModelAttribute("role") Role role,Model model) {
		
		return "skill";
	}

	@RequestMapping("/setting")
	public String setting(@ModelAttribute("role") Role role,Model model) {
		
		return "setting";
	}
	

	@RequestMapping("/task")
	public String task(@ModelAttribute("role") Role role,Model model) {
		
		return "task";
	}
	
	@RequestMapping("/msg")
	public String msg(@ModelAttribute("role") Role role,Model model) {
		
		return "msg";
	}
	
	@RequestMapping("/happy")
	public String happy(@ModelAttribute("role") Role role,Model model) {
		
		return "happy";
	}
	

	@Autowired
	RoleStatusService roleStatusService;
	@Autowired
	BackPackService backPackService ;
	
	@RequestMapping("/bag")
	public String bag(@ModelAttribute("role") Role role,Model model,HttpServletRequest request) {
		int page = 0;
		Object o = request.getParameter("page");
		if(o!=null) {
			String os = (String)o;
			try {
				page = Integer.parseInt(os);
			}catch (Exception e) {
				
			}
		}
		backPackService.freshback(role);
		model.addAttribute("cash", role.getBackpack().getCash());
		String weight = "负重:"+(role.getBackpack().totalWeight()*100/roleStatusService.acceptableWeight(role)+"%")+"|"+role.getBackpack().getItems().size()+"/"+role.getBackpack().getMaxItemNum();
		model.addAttribute("weight", weight);
		List<Item> items = role.getBackpack().getItems();
		if(items.size()<page*8) {
			page = 0;
		}
		int startIndex = page*8;
		ArrayList<Item> ais = new ArrayList<>(items);
		ArrayList<Item> selected = new ArrayList<>(8);
		Collections.reverse(ais);
		for(int i=0,num=0;i<ais.size()&&num<8;i++) {
			if(i>=startIndex) {
				num++;
				selected.add(ais.get(i));
			}
		}
		if(startIndex>0) {
			model.addAttribute("last",page-1);
		}
		if(ais.size()>(page+1)*8) {
			model.addAttribute("next",page+1);
		}
		model.addAttribute("items", selected);
		model.addAttribute("startIndex",startIndex);
		return "bag";
	}
	
	@RequestMapping("/goEast")
	public String east(@ModelAttribute("role") Role role,Model model) {
		try {
			checkOverWeight(role);
			mapBlockService.goEast(role);
		} catch (ImmovableException e) {
			model.addAttribute("unreachable",e.getMessage());
		}
		addMaps(model, role);
		return "world";
	}
	
	@RequestMapping("/goto")
	public String goTo(@ModelAttribute("uuid") String uuid,@ModelAttribute("t") long t,@ModelAttribute("role") Role role,Model model,HttpServletRequest request) {
		try {
			String mapid = request.getParameter("mapid");
			int mapId;
			try {
				mapId = Integer.parseInt(mapid);
				if(!uuidCmdPool.validate(uuid, new Cmd("goto", mapId, t))) {
					throw new ImmovableException();
				}
			} catch (Exception e) {
				throw new ImmovableException("传送指令无效");
			}
			checkOverWeight(role);
			mapBlockService.goTo(role,mapId);
		} catch (ImmovableException e) {
			model.addAttribute("unreachable",e.getMessage());
		}
		addMaps(model, role);
		return "world";
	}
	
	@RequestMapping("/city")
	public String city(@ModelAttribute("uuid") String uuid,@ModelAttribute("t") long t,@ModelAttribute("role") Role role,Model model) {
		ArrayList<MapBlock> mapblocks = new ArrayList<>();
		for(MapBlock map:mapBlockService.getShows()) {
			mapblocks.add(map);
			uuidCmdPool.add(uuid, new Cmd("goto", map.getId(), t));
		}
		model.addAttribute("maps", mapblocks);
		return "city";
	}
	
	private void checkOverWeight(Role role) throws ImmovableException {
		if(role.getBackpack().totalWeight()>roleStatusService.acceptableWeight(role)) {
			throw new ImmovableException("你行囊物品太重，无法行动");
		}
	}

	@RequestMapping("/goWest")
	public String west(@ModelAttribute("role") Role role,Model model) {
		try {
			checkOverWeight(role);
			mapBlockService.goWest(role);
		} catch (ImmovableException e) {
			model.addAttribute("unreachable",e.getMessage());
		}
		addMaps(model, role);
		return "world";
	}
	
	@Autowired
	ChatRoomMessageService chatRoomMessageService;
	
	@RequestMapping("/chat")
	public String chat(@ModelAttribute("role") Role role,Model model,HttpServletRequest request) {
		int page = 0;
		Object o = request.getParameter("page");
		if(o!=null) {
			String os = (String)o;
			try {
				page = Integer.parseInt(os);
			}catch (Exception e) {
				
			}
		}
		Iterable<ChatRoomMessage> msgs = chatRoomMessageService.find(page);
		ArrayList<ChatRoomMessage> cmsgs = new ArrayList<>();
		if(page>0) {
			model.addAttribute("last",page-1);
		}
		if(chatRoomMessageService.find(page+1).iterator().hasNext()) {
			model.addAttribute("next",page+1);
		}
		msgs.forEach(cmsgs::add);
		model.addAttribute("msgs",cmsgs);
		return "chatroom";
	}
	
	@RequestMapping("/chat-input")
	public String chatInput(@ModelAttribute("role") Role role,Model model,HttpServletRequest request) {
		int page = 0;
		String msg = request.getParameter("msg");
		if(msg==null||msg.equals("")) {
			model.addAttribute("senderror", "传音消息不能为空");
		}else {
			try {
				chatRoomMessageService.addChatRootMessage(role, msg);
			} catch (MsgTooLongException e) {
				model.addAttribute("senderror", e.getMessage());
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		Iterable<ChatRoomMessage> msgs = chatRoomMessageService.find(page);
		ArrayList<ChatRoomMessage> cmsgs = new ArrayList<>();
		if(page>0) {
			model.addAttribute("last",page-1);
		}
		if(chatRoomMessageService.find(page+1).iterator().hasNext()) {
			model.addAttribute("next",page+1);
		}
		msgs.forEach(cmsgs::add);
		model.addAttribute("msgs",cmsgs);
		return "chatroom";
	}
	
	@RequestMapping("/goNorth")
	public String north(@ModelAttribute("role") Role role,Model model) {
		try {
			checkOverWeight(role);
			mapBlockService.goNorth(role);
		} catch (ImmovableException e) {
			model.addAttribute("unreachable",e.getMessage());
		}
		addMaps(model, role);
		return "world";
	}
	
	@RequestMapping("/goSouth")
	public String south(@ModelAttribute("role") Role role,Model model) {
		try {
			checkOverWeight(role);
			mapBlockService.goSouth(role);
		} catch (ImmovableException e) {
			model.addAttribute("unreachable",e.getMessage());
		}
		addMaps(model, role);
		return "world";
	}
	

	@Autowired
	RoleRecordService rrs;

	@Autowired
	EnterService enterService;
	@RequestMapping("/back")
	public String back(@ModelAttribute("role") Role role,HttpServletRequest request) {
		try {
			List<Role> rolelist =  rrs.getRoles(role);
			rolelist.forEach(enterService::setUUID);
			Role[] roles = new Role[rolelist.size()];
			roles = rolelist.toArray(roles);
			request.getSession().setAttribute("roles", roles);
		}catch (Exception e) {
			e.printStackTrace();
			return "user-input";
		}
		return "login";
	}
	
	private void addMaps(Model model, Role role) {
		int roleId = role.getMapId();
		model.addAttribute("roles", mapBlockService.roles(role));
		model.addAttribute("map", mapBlockService.getMap(role));
		model.addAttribute("east",mapBlockService.getEastName(roleId));
		model.addAttribute("west",mapBlockService.getWestName(roleId));
		model.addAttribute("north",mapBlockService.getNorthName(roleId));
		model.addAttribute("south",mapBlockService.getSouthName(roleId));
	}
	
}
