package com.ck.bean;

public class MusicInfo {
	private int mid;
	private String msong;
	private String msinger;
	private String malbum;
	private String mtime;
	private String msize;
	private String mmv;
	private String mmp3;
	private int cid;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMsong() {
		return msong;
	}
	public void setMsong(String msong) {
		this.msong = msong;
	}
	public String getMsinger() {
		return msinger;
	}
	public void setMsinger(String msinger) {
		this.msinger = msinger;
	}
	public String getMalbum() {
		return malbum;
	}
	public void setMalbum(String malbum) {
		this.malbum = malbum;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public String getMsize() {
		return msize;
	}
	public void setMsize(String msize) {
		this.msize = msize;
	}
	public String getMmv() {
		return mmv;
	}
	public void setMmv(String mmv) {
		this.mmv = mmv;
	}
	public String getMmp3() {
		return mmp3;
	}
	public void setMmp3(String mmp3) {
		this.mmp3 = mmp3;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public MusicInfo(int mid, String msong, String msinger, String malbum, String mtime, String msize, String mmv,
			String mmp3, int cid) {
		super();
		this.mid = mid;
		this.msong = msong;
		this.msinger = msinger;
		this.malbum = malbum;
		this.mtime = mtime;
		this.msize = msize;
		this.mmv = mmv;
		this.mmp3 = mmp3;
		this.cid = cid;
	}
	public MusicInfo() {
		super();
	}
	@Override
	public String toString() {
		return "MusicInfo [mid=" + mid + ", msong=" + msong + ", msinger=" + msinger + ", malbum=" + malbum + ", mtime="
				+ mtime + ", msize=" + msize + ", mmv=" + mmv + ", mmp3=" + mmp3 + ", cid=" + cid + "]";
	}
	
	
}	
