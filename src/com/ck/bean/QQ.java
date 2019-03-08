package com.ck.bean;


/**
 * 
 * @author Administrator
 * 
       qid varchar2(32) primary key,-- qq号
       pwd varchar2(32) not null,--密码
       qimage blob ,-- 头像
       qname varchar2(50) not null ,-- 网名
       motto varchar2(86),-- 签名
       age int ,--年龄
       sex varchar2(4) constraint CK_sex check( sex in('男','女')),-- 性别
       birthday varchar2(20),--生日
       adrr varchar2(64)-- 地址
 *
 */
public class QQ {
	
	public String qid;
	public String pwd;
	public  byte[] qimage ;
	public String qname;
	public String motto;
	public String email;
	public int age;
	public String sex ;
	public String birthday;
	public String addr;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
