package com.ck.bean;

import java.util.Arrays;

public class ClassifyInfo {
	private int cid;
	private String cname;
	private byte[] cimage;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public byte[] getCimage() {
		return cimage;
	}
	public void setCimage(byte[] cimage) {
		this.cimage = cimage;
	}
	public ClassifyInfo(int cid, String cname, byte[] cimage) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cimage = cimage;
	}
	public ClassifyInfo() {
		super();
	}
	@Override
	public String toString() {
		return "ClassifyInfo [cid=" + cid + ", cname=" + cname + ", cimage=" + Arrays.toString(cimage) + "]";
	}
	
	
}
