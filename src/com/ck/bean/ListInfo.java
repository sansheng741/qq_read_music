package com.ck.bean;

public class ListInfo {
	private String lname;
	private String qid;
	private int mid;
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public ListInfo(String lname, String qid, int mid) {
		super();
		this.lname = lname;
		this.qid = qid;
		this.mid = mid;
	}
	public ListInfo() {
		super();
	}
	@Override
	public String toString() {
		return "ListInfo [lname=" + lname + ", qid=" + qid + ", mid=" + mid + "]";
	}
	
	
}
