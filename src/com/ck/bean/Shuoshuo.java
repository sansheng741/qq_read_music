package com.ck.bean;

import java.util.Arrays;
import java.util.Date;

public class Shuoshuo {
	private String qid;
	private String msg;
	private byte[] qimage;
	public Date qtime;


	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public byte[] getQimage() {
		return qimage;
	}
	public void setQimage(byte[] qimage) {
		this.qimage = qimage;
	}
	public Date getQtime() {
		return qtime;
	}
	public void setQtime(Date qtime) {
		this.qtime = qtime;
	}
	public Shuoshuo() {
		super();
	}
	public Shuoshuo(String qid, String msg, byte[] qimage, Date qtime) {
		super();
		this.qid = qid;
		this.msg = msg;
		this.qimage = qimage;
		this.qtime = qtime;
	}
	@Override
	public String toString() {
		return "Shuoshuo [qid=" + qid + ", msg=" + msg + ", qimage=" + Arrays.toString(qimage) + ", qtime=" + qtime
				+ "]";
	}
	
	
	
	
}
