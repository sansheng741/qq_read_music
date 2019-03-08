package com.ck.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties extends Properties {
	private static MyProperties myProperties;
	
	private MyProperties(){
		InputStream is = MyProperties.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			this.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//µ¥ÀýÄ£Ê½
	public static MyProperties getInstance(){
		if(myProperties == null){
			myProperties = new MyProperties();
		}
		return myProperties;
	}
}
