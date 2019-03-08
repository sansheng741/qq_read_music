package com.ck.bean;

import java.util.Arrays;

public class QqInfo {
	private String qid;
	private String pwd;
	private byte[] qimage;
	private String qname;
	private String motto;
	private String email;
	private int age;
	private String sex;
	private String birthday;
	private String adrr;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public byte[] getQimage() {
		return qimage;
	}
	public void setQimage(byte[] qimage) {
		this.qimage = qimage;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAdrr() {
		return adrr;
	}
	public void setAdrr(String adrr) {
		this.adrr = adrr;
	}
	
	public QqInfo(String qid, String pwd, byte[] qimage, String qname, String motto, String email, int age, String sex,
			String birthday, String adrr) {
		super();
		this.qid = qid;
		this.pwd = pwd;
		this.qimage = qimage;
		this.qname = qname;
		this.motto = motto;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
		this.adrr = adrr;
	}
	public QqInfo() {
		super();
	}
	@Override
	public String toString() {
		return "QQ [qid=" + qid + ", pwd=" + pwd + ", qimage=" + Arrays.toString(qimage) + ", qname=" + qname
				+ ", motto=" + motto + ", email=" + email + ", age=" + age + ", sex=" + sex + ", birthday=" + birthday
				+ ", adrr=" + adrr + "]";
	}
	
	
	
}
