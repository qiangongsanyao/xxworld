package com.kkch.xxworld.bean;

public class Cmd {

	private String cmd;
	private Object target;
	private long time;
	
	public Cmd(String cmd, Object target, long time) {
		super();
		this.cmd = cmd;
		this.target = target;
		this.time = time;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Cmd [cmd=" + cmd + ", target=" + target + ", time=" + time + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cmd) {
			Cmd cmd = (Cmd)obj;
			if(!cmd.cmd.equals(this.cmd)) {
				return false;
			}
			if(!cmd.target.equals(this.target)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
}
