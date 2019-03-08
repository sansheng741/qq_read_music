package com.ck.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ck.dao.DBHelper;

public class RegisterUtil {
	
	private DBHelper db = new DBHelper();
		
	public Map<String,String> getAll(){
		Map<String,String> map = new HashMap<String,String>();
		//查数据库
		String sql = "select * from registers";
		List<Map<String, String>> list = db.findAll(sql, null);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				map.put(list.get(i).get("QID"), list.get(i).get("PWD"));
			}
		}
		return map;
	}

	public void add(Map<String, String> map1) {
		String qid = map1.keySet().toString();
		String pwd = map1.values().toString();
		qid = qid.substring(1,qid.length() - 1);
		pwd = pwd.substring(1,pwd.length() - 1);
		List<Object> params = new ArrayList<Object>();
		//数据库，如果有则不添加，若果没有则添加
		String sql = "select * from registers where qid = ?";
		params.add(qid);
		List<Map<String, String>> list = db.findAll(sql, params);
		if(list.size() <= 0){
			sql = "insert into registers values(seq_rsid.nextval,?,?)";
			params.clear();
			params.add(qid);
			params.add(pwd);
			db.doUpdate(sql, params);
		}	
	}

	public void remove(String qid) {
		//数据库删除
		String sql = "delete from registers where qid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(qid);
		db.doUpdate(sql, params);
	}
}
