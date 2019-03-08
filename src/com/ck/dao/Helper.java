package com.ck.dao;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.ck.bean.QQ;
import com.yc.qq.QQDL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
	private String []columnName;
	
	static{
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			    
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Helper help=new Helper();
		System.out.println(help.getCon());
	}
	
	/*//打开windows文本框  吧选择的文件转化为 字节数组
	public byte[] getImageByte(Shell shell){
		//打开一个文件选择框,可以选择文件
				String filepath="";
				FileDialog fd=new FileDialog(shell);
				// 控制你选择的后缀名为  :...
				//fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
				fd.open();
				
				InputStream is=null;
				//指的是 你选择的路径  不为空     意思就是你突然不想换了 是可以直接返回的
				if(fd!=null && !"".equals(fd.getFilterPath())){
					//文件已经有了
					 filepath=fd.getFilterPath()+"\\"+fd.getFileName();
					 Image image=null;
					  try {
						File file=new File(filepath);
						//得到io 流
						is=new FileInputStream(file);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					  byte []bt=null;
						try {
							bt=new byte[is.available()];
							is.read(bt);
							is.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
						return bt;	  
				}
				return null;
	}*/
	
	//从windows打开一个文本框   并且把选择好的 图片设置为 你传来的 lebel的背脊图片 
	public void setLabelImageAndOpenFileDialog(Shell shell,Label label,List<Object> params){
		//打开一个文件选择框,可以选择文件
		String filepath="";
		FileDialog fd=new FileDialog(shell);
		// 控制你选择的后缀名为  :...
		//fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
		fd.open();
		
		InputStream is=null;
		//指的是 你选择的路径  不为空     意思就是你突然不想换了 是可以直接返回的
		if(fd!=null && !"".equals(fd.getFilterPath())){
			//文件已经有了
			 filepath=fd.getFilterPath()+"\\"+fd.getFileName();
			 Image image=null;
			  try {
				File file=new File(filepath);
				//得到io 流
				is=new FileInputStream(file);
				setLabelImage(label,is);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			  
			  if(params==null){
				  return ;
			  }else{
				  byte []bt=null;
					try {
						is=new FileInputStream(new File(filepath));
						bt=new byte[is.available()];
						is.read(bt);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					params.add(bt);
					
					try {
						is.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
			  }
		}
	}
	
	//得到流来设置label的图片
	public void setLabelImage(Label label,InputStream is){
		int width,height;
		width=label.getBounds().width;
		height=label.getBounds().height;
		ImageData id=new ImageData(is);
		id=id.scaledTo(width, height);
		Image image=new Image(null,id);
		label.setImage(image);
		try {
			is.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//这里的label 是你要设置的label  bimage是你从数据库 得到或者 从文本框取到的 图片字节数组
	public void setLabelImage(Label label,byte [] bimage){
		InputStream is=new ByteArrayInputStream(bimage);
		setLabelImage(label,is);
	}
	
	
	public String []getColumnName(){
		return columnName;
	}
	public List<TableItem> getTableItem(Table table){
		List<TableItem>list=new ArrayList<TableItem>();
		//table.getItemCount()
		for(int i=0;i<table.getItemCount();i++){
			list.add(table.getItem(i));
			
		}
		return list;
	}
	public Connection getCon(){
		Connection con=null;
		try {
			con=DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),"scott","a");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	public void colseAll
	(Connection con,Statement stmt,PreparedStatement pstmt,ResultSet rs){
			try {
				if(con!=null)
				con.close();
				if(stmt!=null)
					stmt.close();
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}	

	public int doUpdate(String sql,List<Object>params){
		int result=-1;
		try {
			Connection con=getCon();
			con.setAutoCommit(true);
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的  1: 有  但是数目不确定   类型也不确定
			doParams(ps,params);
			result=ps.executeUpdate();
			colseAll(con, null, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	/*public <T> List<T> findAll(String sql,List<Object> params,Class<T> c){
		//就把这个T看成是一个对象
		List<T> list=new ArrayList<T>();
		try {
			//获取连接
			Connection con=getCon();
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的，1、可能没有参数     2、有，但是数量不固定    但是类型不固定  类型导致方法不同
			doParams(ps,params);
			//开始运行
			ResultSet rs=ps.executeQuery();
			
			//第一个，数据库里面的列，对应我们Map里面的键
			ResultSetMetaData rsmd=rs.getMetaData();		//获取到这个数据库相关的元数据
			String[] columnName=new String[ rsmd.getColumnCount()];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1); 
				//System.out.println(columnName[i]);
			}//得到数据库里面table的列名      // 数据库里面的列名都是大写
			
			//1、我们先得到这个对象里面的所有的方法
			Method[] ms=c.getMethods();
			//2、得到对象的事例，然后一个一个去匹配  
			//列是MONEY   我们想要的方法是:setMoney();
			//  "set"+M+oney ->  	 setMoney
			T t;					//对象的事例    ReaderInfo ri;
			String mname="";		//方法名
			String cname="";		//列明
			String ctypeName="";	//类型名
			while( rs.next() ){
				t=c.newInstance();		//ri=new ReaderInfo();
				//循环方法名，去一个一个的查找
				for(int i=0;i<columnName.length;i++){
					//得到列名字
					cname=columnName[i];
					//去比较方法名   money     ->   setMoney()
					//         rid    ->   setRid()
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					for(Method m : ms){
						//得到方法名
						mname=m.getName();
						//找到方法名，但是，同时，值不能为空
						if( cname.equals(mname)  ){
							//int   getInt      double   getDouble    String   getString
							//一定要判断类型	
							
							
							if(rs.getObject(columnName[i]) == null || "".equals(rs.getObject(columnName[i]))){
								continue;
							}
							
							if("int".equals(m.getReturnType().toString())){
								m.invoke(t, rs.getInt(columnName[i]));
							}else if("class java.lang.Double".equals(m.getReturnType().toString())){
								m.invoke(t, rs.getDouble(columnName[i]));
							}else if("class [B".equals(m.getReturnType().toString())){
								m.invoke(t, rs.getBytes(columnName[i]));
							}else if("class java.lang.String".equals(m.getReturnType().toString())){
								m.invoke(t, rs.getString(columnName[i]));
							}
						}
					}
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return list;
	}*/
	//基于对象的查询
		//你能不能确定，到时候传进来的是哪一个对象？
		//sorry 不确定，但是，不管你是哪一个对象，我都知道，你是一个对象，就是不知道具体的类型
		// T 泛型               规定了，传进来的是一个对象，至于具体的对象，OK，又传进来的那个人决定
		//返回类型？   也是由泛型决定，你传进来什么，我返回什么          返回的对象不止一个，可能是多个，所以用集合
		//    声明泛型
		public <T> List<T> findAll(String sql,List<Object> params,Class<T> c){
			//就把这个T看成是一个对象
			List<T> list=new ArrayList<T>();
			try {
				//获取连接
				Connection con=getCon();
				//预处理
				PreparedStatement ps=con.prepareStatement(sql);
				//参数是问题最大的，1、可能没有参数     2、有，但是数量不固定    但是类型不固定  类型导致方法不同
				doParams(ps,params);
				//开始运行
				ResultSet rs=ps.executeQuery();
				
				//第一个，数据库里面的列，对应我们Map里面的键
				ResultSetMetaData rsmd=rs.getMetaData();		//获取到这个数据库相关的元数据
				String[] columnName=new String[ rsmd.getColumnCount()  ];
				for(int i=0;i<columnName.length;i++){
					columnName[i]=rsmd.getColumnName(i+1); 
					//System.out.println(columnName[i]);
				}
				
				//System.out.println("--------------");
				
				//1、我们先得到这个对象里面的所有的方法
				Method[] ms=c.getMethods();
				//2、得到对象的事例，然后一个一个去匹配  
				//列是MONEY   我们想要的方法是：   setMoney();
				//  "set"+M+oney ->   setMoney
				T t;		//对象的事例    ReaderInfo ri;
				String mname="";		//方法名
				String cname="";		//列明
				String ctypeName="";	//类型名
				while( rs.next() ){
					t=c.newInstance();		//ri=new ReaderInfo();
					//循环方法名，去一个一个的查找
					for(int i=0;i<columnName.length;i++){
						//得到列名字
						cname=columnName[i];
						//去比较方法名   money     ->   setMoney()
						//         rid    ->   setRid()
						cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
						for(Method m : ms){
							//得到方法名
							mname=m.getName();
							//找到方法名，但是，同时，值不能为空
							if( cname.equals(mname)  ){
								//int   getInt      double   getDouble    String   getString
								//一定要判断类型	
								if(rs.getObject(columnName[i]) == null || "".equals(rs.getObject(columnName[i]))){
									continue;
								}
								ctypeName=rs.getObject(columnName[i]).getClass().getName();								
								if("java.lang.Integer".equals(ctypeName)){
									m.invoke(t, rs.getInt(columnName[i]));
								}else if("java.math.BigDecimal".equals(ctypeName)){
									try{
										java.math.BigDecimal b= (BigDecimal) rs.getObject(columnName[i]);
										m.invoke(t,Integer.parseInt(b.toString()));
									}catch (Exception e){
										java.math.BigDecimal b=(java.math.BigDecimal) rs.getObject(columnName[i].toUpperCase());
										double doub= Double.parseDouble(b.toString());
										m.invoke(t,doub);
									}
								}else if("java.lang.String ".equals(ctypeName)){
									m.invoke(t, rs.getString(columnName[i]));
								}else if("java.sql.Timestamp".equals(ctypeName)){
									m.invoke(t, rs.getString(columnName[i]));
								}else if("java.sql.Date".equals(ctypeName)){
									m.invoke(t, rs.getDate(columnName[i]));
								}else if("image".equals(ctypeName)){
									m.invoke(t, rs.getBlob(columnName[i]));
								}else if("oracle.sql.BLOB".equals(ctypeName)){
									BufferedInputStream is=null;
									byte[] bytes=null;
									//因为在oracle里面存的是blob，它是无法直接转换为字节数组
									Blob blob=rs.getBlob(columnName[i]);
									is=new BufferedInputStream(blob.getBinaryStream());
									bytes=new byte[(int)blob.length()];//数组的长度
									//把io流里面的所有的数据全部读取到bytes里面去
									is.read(bytes);
									m.invoke(t, bytes);
									
								}else{
									m.invoke(t, rs.getString(columnName[i]));
									
								}
							}
						}
					}
					list.add(t);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return list;
		}

	public void doParams(PreparedStatement ps, List<Object> params) throws SQLException {
		if(params!=null&&params.size()>0){
			int lenth=params.size();
			
			for(int i=0;i<lenth;i++){
				Object o=params.get(i);
				
				if(o instanceof String){
					ps.setString(i+1, (String)o);
				}else if(o instanceof Integer ){
					ps.setInt(i+1, (Integer)o);
				}else if(o instanceof Double){
					ps.setDouble(i+1, (Double)o);
				}else if (o instanceof Boolean){
					ps.setBoolean(i+1,(Boolean)o );
				}else {
					ps.setBytes(i+1, (byte[])o);
				}
				
			}
		}
	}
	public List<Map<String ,String>> findAll(String sql,List<Object> params){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		try {
			Connection con=getCon();
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的  1: 有  但是数目不确定   类型也不确定
			doParams(ps,params);
			ResultSet rs=ps.executeQuery();
			// 把resultset 里面的值转化为 list<map<String,String>>
			
			
			//第一个,数据库里面的列  对应我们map 里面的键
			ResultSetMetaData rsmd=rs.getMetaData();//获取到这个数据库相关的元数据
			columnName=new String[rsmd.getColumnCount()];
			
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1);//得到键的名字
			}
		
			//得到值
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				//根据列名来取值
				for(int i=0;i<columnName.length;i++){
					String key=columnName[i];
					String ctypename="";
					if(rs.getObject(key)!=null){
					 ctypename=rs.getObject(key).getClass().getName();
					}
					
					if("oracle.sql.BLOB".equals(ctypename)){
						Blob blob=rs.getBlob(key);
						byte[]bytes=new byte[(int)blob.length()];
						map.put(key, bytes.toString());
					}else{
						String value=rs.getString(key);
						
						map.put(key, value);
					}
					
				}
				list.add(map);
				
			}
			colseAll(con, null, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

    public List<QQ> findAllQQ(String sql,List<Object> params,int tkey){
    	List<QQ> list =new ArrayList<QQ>();
    	try {
    		tkey=10;
			Connection con=getCon();
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的  1: 有  但是数目不确定   类型也不确定
			doParams(ps,params);
			ResultSet rs=ps.executeQuery();
			// 把resultset 里面的值转化为 list<map<String,String>>
			
			//第一个,数据库里面的列  对应我们map 里面的键
			ResultSetMetaData rsmd=rs.getMetaData();//获取到这个数据库相关的元数据
			columnName=new String[rsmd.getColumnCount()];
			
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1);//得到键的名字
			}
			/**
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
	
			 */
			while(rs.next()){
				QQ q=new QQ();			
				q.qid=rs.getString("QID");
				q.pwd=rs.getString("PWD");
				q.qname=rs.getString("QNAME");
				q.qimage=rs.getBytes("QIMAGE");
				q.motto=rs.getString("MOTTO");
				q.email = rs.getString("EMAIL");
				q.addr=rs.getString("ADRR");
				q.sex=rs.getString("SEX");
				q.age=rs.getInt("AGE");
				q.birthday=rs.getString("BIRTHDAY");

				list.add(q);
			}
			colseAll(con, null, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}  	  	
    	return list;
    }
  
}

