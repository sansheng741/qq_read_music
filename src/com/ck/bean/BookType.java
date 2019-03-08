package com.ck.bean;

public class BookType {
	private String typename;

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public BookType(String typename) {
		super();
		this.typename = typename;
	}

	public BookType() {
		super();
	}

	@Override
	public String toString() {
		return "BookType [typename=" + typename + "]";
	}

	
	
	
}
