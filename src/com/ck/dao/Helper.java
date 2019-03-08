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
	
	/*//��windows�ı���  ��ѡ����ļ�ת��Ϊ �ֽ�����
	public byte[] getImageByte(Shell shell){
		//��һ���ļ�ѡ���,����ѡ���ļ�
				String filepath="";
				FileDialog fd=new FileDialog(shell);
				// ������ѡ��ĺ�׺��Ϊ  :...
				//fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
				fd.open();
				
				InputStream is=null;
				//ָ���� ��ѡ���·��  ��Ϊ��     ��˼������ͻȻ���뻻�� �ǿ���ֱ�ӷ��ص�
				if(fd!=null && !"".equals(fd.getFilterPath())){
					//�ļ��Ѿ�����
					 filepath=fd.getFilterPath()+"\\"+fd.getFileName();
					 Image image=null;
					  try {
						File file=new File(filepath);
						//�õ�io ��
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
	
	//��windows��һ���ı���   ���Ұ�ѡ��õ� ͼƬ����Ϊ �㴫���� lebel�ı���ͼƬ 
	public void setLabelImageAndOpenFileDialog(Shell shell,Label label,List<Object> params){
		//��һ���ļ�ѡ���,����ѡ���ļ�
		String filepath="";
		FileDialog fd=new FileDialog(shell);
		// ������ѡ��ĺ�׺��Ϊ  :...
		//fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
		fd.open();
		
		InputStream is=null;
		//ָ���� ��ѡ���·��  ��Ϊ��     ��˼������ͻȻ���뻻�� �ǿ���ֱ�ӷ��ص�
		if(fd!=null && !"".equals(fd.getFilterPath())){
			//�ļ��Ѿ�����
			 filepath=fd.getFilterPath()+"\\"+fd.getFileName();
			 Image image=null;
			  try {
				File file=new File(filepath);
				//�õ�io ��
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
	
	//�õ���������label��ͼƬ
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
	
	
	//�����label ����Ҫ���õ�label  bimage��������ݿ� �õ����� ���ı���ȡ���� ͼƬ�ֽ�����
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
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//��������������  1: ��  ������Ŀ��ȷ��   ����Ҳ��ȷ��
			doParams(ps,params);
			result=ps.executeUpdate();
			colseAll(con, null, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	/*public <T> List<T> findAll(String sql,List<Object> params,Class<T> c){
		//�Ͱ����T������һ������
		List<T> list=new ArrayList<T>();
		try {
			//��ȡ����
			Connection con=getCon();
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//�������������ģ�1������û�в���     2���У������������̶�    �������Ͳ��̶�  ���͵��·�����ͬ
			doParams(ps,params);
			//��ʼ����
			ResultSet rs=ps.executeQuery();
			
			//��һ�������ݿ�������У���Ӧ����Map����ļ�
			ResultSetMetaData rsmd=rs.getMetaData();		//��ȡ��������ݿ���ص�Ԫ����
			String[] columnName=new String[ rsmd.getColumnCount()];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1); 
				//System.out.println(columnName[i]);
			}//�õ����ݿ�����table������      // ���ݿ�������������Ǵ�д
			
			//1�������ȵõ����������������еķ���
			Method[] ms=c.getMethods();
			//2���õ������������Ȼ��һ��һ��ȥƥ��  
			//����MONEY   ������Ҫ�ķ�����:setMoney();
			//  "set"+M+oney ->  	 setMoney
			T t;					//���������    ReaderInfo ri;
			String mname="";		//������
			String cname="";		//����
			String ctypeName="";	//������
			while( rs.next() ){
				t=c.newInstance();		//ri=new ReaderInfo();
				//ѭ����������ȥһ��һ���Ĳ���
				for(int i=0;i<columnName.length;i++){
					//�õ�������
					cname=columnName[i];
					//ȥ�ȽϷ�����   money     ->   setMoney()
					//         rid    ->   setRid()
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					for(Method m : ms){
						//�õ�������
						mname=m.getName();
						//�ҵ������������ǣ�ͬʱ��ֵ����Ϊ��
						if( cname.equals(mname)  ){
							//int   getInt      double   getDouble    String   getString
							//һ��Ҫ�ж�����	
							
							
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
	//���ڶ���Ĳ�ѯ
		//���ܲ���ȷ������ʱ�򴫽���������һ������
		//sorry ��ȷ�������ǣ�����������һ�������Ҷ�֪��������һ�����󣬾��ǲ�֪�����������
		// T ����               �涨�ˣ�����������һ���������ھ���Ķ���OK���ִ��������Ǹ��˾���
		//�������ͣ�   Ҳ���ɷ��;������㴫����ʲô���ҷ���ʲô          ���صĶ���ֹһ���������Ƕ���������ü���
		//    ��������
		public <T> List<T> findAll(String sql,List<Object> params,Class<T> c){
			//�Ͱ����T������һ������
			List<T> list=new ArrayList<T>();
			try {
				//��ȡ����
				Connection con=getCon();
				//Ԥ����
				PreparedStatement ps=con.prepareStatement(sql);
				//�������������ģ�1������û�в���     2���У������������̶�    �������Ͳ��̶�  ���͵��·�����ͬ
				doParams(ps,params);
				//��ʼ����
				ResultSet rs=ps.executeQuery();
				
				//��һ�������ݿ�������У���Ӧ����Map����ļ�
				ResultSetMetaData rsmd=rs.getMetaData();		//��ȡ��������ݿ���ص�Ԫ����
				String[] columnName=new String[ rsmd.getColumnCount()  ];
				for(int i=0;i<columnName.length;i++){
					columnName[i]=rsmd.getColumnName(i+1); 
					//System.out.println(columnName[i]);
				}
				
				//System.out.println("--------------");
				
				//1�������ȵõ����������������еķ���
				Method[] ms=c.getMethods();
				//2���õ������������Ȼ��һ��һ��ȥƥ��  
				//����MONEY   ������Ҫ�ķ����ǣ�   setMoney();
				//  "set"+M+oney ->   setMoney
				T t;		//���������    ReaderInfo ri;
				String mname="";		//������
				String cname="";		//����
				String ctypeName="";	//������
				while( rs.next() ){
					t=c.newInstance();		//ri=new ReaderInfo();
					//ѭ����������ȥһ��һ���Ĳ���
					for(int i=0;i<columnName.length;i++){
						//�õ�������
						cname=columnName[i];
						//ȥ�ȽϷ�����   money     ->   setMoney()
						//         rid    ->   setRid()
						cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
						for(Method m : ms){
							//�õ�������
							mname=m.getName();
							//�ҵ������������ǣ�ͬʱ��ֵ����Ϊ��
							if( cname.equals(mname)  ){
								//int   getInt      double   getDouble    String   getString
								//һ��Ҫ�ж�����	
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
									//��Ϊ��oracle��������blob�������޷�ֱ��ת��Ϊ�ֽ�����
									Blob blob=rs.getBlob(columnName[i]);
									is=new BufferedInputStream(blob.getBinaryStream());
									bytes=new byte[(int)blob.length()];//����ĳ���
									//��io����������е�����ȫ����ȡ��bytes����ȥ
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
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//��������������  1: ��  ������Ŀ��ȷ��   ����Ҳ��ȷ��
			doParams(ps,params);
			ResultSet rs=ps.executeQuery();
			// ��resultset �����ֵת��Ϊ list<map<String,String>>
			
			
			//��һ��,���ݿ��������  ��Ӧ����map ����ļ�
			ResultSetMetaData rsmd=rs.getMetaData();//��ȡ��������ݿ���ص�Ԫ����
			columnName=new String[rsmd.getColumnCount()];
			
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1);//�õ���������
			}
		
			//�õ�ֵ
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				//����������ȡֵ
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
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//��������������  1: ��  ������Ŀ��ȷ��   ����Ҳ��ȷ��
			doParams(ps,params);
			ResultSet rs=ps.executeQuery();
			// ��resultset �����ֵת��Ϊ list<map<String,String>>
			
			//��һ��,���ݿ��������  ��Ӧ����map ����ļ�
			ResultSetMetaData rsmd=rs.getMetaData();//��ȡ��������ݿ���ص�Ԫ����
			columnName=new String[rsmd.getColumnCount()];
			
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1);//�õ���������
			}
			/**
			 * 	
			 qid varchar2(32) primary key,-- qq��
		       pwd varchar2(32) not null,--����
		       qimage blob ,-- ͷ��
		       qname varchar2(50) not null ,-- ����
		       motto varchar2(86),-- ǩ��
		       age int ,--����
		       sex varchar2(4) constraint CK_sex check( sex in('��','Ů')),-- �Ա�
		       birthday varchar2(20),--����
		       adrr varchar2(64)-- ��ַ
	
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

