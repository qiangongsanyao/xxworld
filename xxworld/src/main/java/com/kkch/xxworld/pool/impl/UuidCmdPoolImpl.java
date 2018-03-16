package com.kkch.xxworld.pool.impl;

import org.springframework.stereotype.Component;

import com.kkch.xxworld.bean.Cmd;
import com.kkch.xxworld.pool.UuidCmdPool;

@Component
public class UuidCmdPoolImpl implements UuidCmdPool {
	
	TimedPool<CmdList> uuidpool = new TimedPool<>();
	
	class CmdList{
		
		Cmd[] cmds = new Cmd[60];
		
		int nowIndex = 0;
		
		public synchronized void add(Cmd cmd) {
			cmds[nowIndex++] = cmd;
			if(nowIndex >= cmds.length) {
				nowIndex = 0;
			}
		}

		public synchronized boolean contains(Cmd cmd) {
			for(Cmd c:cmds) {
				if(c!=null&&c.equals(cmd)) {
					return true;
				}
			}
			return false;
		}
		
	}

	@Override
	public void add(String uuid, Cmd cmd) {
		CmdList cmdList = uuidpool.getAndFresh(uuid);
		if(cmdList == null) {
			cmdList = new CmdList();
			uuidpool.put(cmdList,uuid);
		}
		cmdList.add(cmd);
	}

	@Override
	public boolean validate(String uuid, Cmd cmd) {
		CmdList cmdList = uuidpool.getAndFresh(uuid);
		return cmdList!=null&&cmdList.contains(cmd);
	}
	
}
