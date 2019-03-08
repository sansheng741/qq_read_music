package com.ck.bean;

import java.sql.Date;
import java.util.Arrays;

public class Librarybooks {
	private String bid;
	private String typename;
	private byte[] picture;
	private String bname;
	private String author;
	private String pub;
	private double bprice;
	private Date bdate;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	
	public Librarybooks(String bid, String typename, byte[] picture,
			String bname, String author, String pub, double bprice, Date bdate) {
		super();
		this.bid = bid;
		this.typename = typename;
		this.picture = picture;
		this.bname = bname;
		this.author = author;
		this.pub = pub;
		this.bprice = bprice;
		this.bdate = bdate;
	}
	public Librarybooks() {
		super();
	}
	@Override
	public String toString() {
		return "Librarybooks [bid=" + bid + ", typename=" + typename + ", picture=" + Arrays.toString(picture)
				+ ", bname=" + bname + ", author=" + author + ", pub=" + pub + ", bprice=" + bprice + ", bdate=" + bdate
				+ "]";
	}
	
	
	
	
	
}
