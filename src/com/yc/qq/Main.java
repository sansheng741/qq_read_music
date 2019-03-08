package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.awt.MouseInfo;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.QQ;
import com.ck.bean.Shuoshuo;
import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class Main {

	protected Shell shell;
	static String beizu;
	static String friendqname;
	private Boolean dargFlag = false;
	private TongYiShengQing tysq;
	private int x,y; //�����ʱ������
	private Menu menu;
	private Label lblNewLabel_1;
	private Tree tree;
	private DBHelper db=new DBHelper();
	private Composite composite_4;
	private String sql="";

	private Tree tree_2 ;
	private Text text;
	private Table table;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private StackLayout stackLayou=new StackLayout();
	private ScrolledComposite scrolledComposite;
	private Helper help=new Helper();
	private Composite composite_3;
	private Composite composite_5;
	private Tree tree_1;
	private MyUtils mu=new MyUtils();
	private Map<String,Chatting> chatmap=new HashMap<String,Chatting>();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}		
		sql="update qq set flag=1 where qid='"+QQDL.qid+"'";
		help.doUpdate(sql, null);
	}
	
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				sql="update qq set flag=1 where qid='"+QQDL.qid+"'";
				help.doUpdate(sql, null);
			}
		});
		shell.setImage(SWTResourceManager.getImage(Main.class, "/images/qq.png"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(317, 617);
		shell.setText("QQ");
		shell.setLayout(new FormLayout());
		//shell.setLocation(1380, 210);
		mu.centerShell(shell);
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Main.class, "/images/qqlogin2.png"));
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 117);
		fd_composite.right = new FormAttachment(0, 315);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		
	    lblNewLabel_1 = new Label(composite, SWT.NONE);
		
		lblNewLabel_1.setBounds(15, 19, 70, 65);
		lblNewLabel_1.setText("\u5934\u50CF");
		sql="select * from qq where qid=? and pwd=?";
		List <Object> params=new ArrayList<Object>();
		params.add(QQDL.qid);
		params.add(QQDL.pwd);
		List<QQ> list =help.findAllQQ(sql, params, 1);
			
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		
		lblNewLabel_2.setBounds(10, 93, 61, 17);
		lblNewLabel_2.setText("\u53D1\u52A8\u6001");
		
		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100);
		fd_text.left = new FormAttachment(0, 32);
		text.setLayoutData(fd_text);
		
		Link link = new Link(shell, SWT.NONE);
		
		FormData fd_link = new FormData();
		fd_link.top = new FormAttachment(composite, 5);
		
		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(249, -5, 70, 29);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setImage(SWTResourceManager.getImage(Main.class, "/images/btn_mini_normal.png"));
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setImage(SWTResourceManager.getImage(Main.class, "/images/btn_close_normal.png"));
		
		lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		lblNewLabel_3.setBounds(96, 26, 138, 29);

		
		lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBounds(97, 61, 155, 17);
		
		fd_link.left = new FormAttachment(0, 1);
		link.setLayoutData(fd_link);
		link.setText("<a> \u641C\u7D22</a>");
		
		
		
		table = new Table(shell, SWT.FULL_SELECTION);
		fd_link.bottom = new FormAttachment(table, -3);
		fd_text.bottom = new FormAttachment(table, -1);
		FormData fd_table = new FormData();
		fd_table.left = new FormAttachment(0);
		fd_table.right = new FormAttachment(100);
		fd_table.top = new FormAttachment(0, 143);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		
		tblclmnNewColumn.setWidth(92);
		tblclmnNewColumn.setText("      \u6D88\u606F");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		
		
		tableColumn.setWidth(118);
		tableColumn.setText("      \u8054\u7CFB\u4EBA");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		
		tblclmnNewColumn_1.setWidth(103);
		tblclmnNewColumn_1.setText("\u52A8\u6001");
		
		Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.left = new FormAttachment(0);
		fd_composite_1.right = new FormAttachment(100);
		fd_composite_1.bottom = new FormAttachment(100);
		composite_1.setLayoutData(fd_composite_1);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(7, 8, 30, 17);
		lblNewLabel.setText("\u6DFB\u52A0");
		//private ScrolledComposite scrolledComposite;
		Composite composite_2 = new Composite(shell, SWT.NONE);
		fd_table.bottom = new FormAttachment(composite_2);
		fd_composite_1.top = new FormAttachment(0, 578);
		composite_2.setLayout(stackLayou);
		
		
		FormData fd_composite_2 = new FormData();
		fd_composite_2.left = new FormAttachment(0);
		fd_composite_2.right = new FormAttachment(100);
		fd_composite_2.top = new FormAttachment(0, 171);
		fd_composite_2.bottom = new FormAttachment(composite_1);
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);		
		lblNewLabel_5.setBounds(276, 8, 30, 17);
		lblNewLabel_5.setText("\u5E94\u7528");
		composite_2.setLayoutData(fd_composite_2);
		
		composite_3 = new Composite(composite_2, SWT.NONE);
		
	   
	    
	//    ntai= new NewDongTai(composite_2, SWT.NONE);
	    stackLayou.topControl=composite_3;
	    composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
	    tree = new Tree(composite_3, SWT.NONE);

	   
	  //  flist = new FriendList(shell,composite_2, SWT.NONE);
	    
	 //   flist.setLayoutData(new FormData());
	    scrolledComposite = new ScrolledComposite(composite_2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    scrolledComposite.setExpandHorizontal(true);
	    scrolledComposite.setExpandVertical(true);
	    
	     composite_5 = new Composite(composite_2, SWT.NONE);
	    composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
	    tree_1 = new Tree(composite_5, SWT.BORDER);
	   
	    
	    Composite composite_6 = new Composite(composite_2, SWT.NONE);
	    composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
	    
	    tree_2 = new Tree(composite_6, SWT.BORDER);
	    
	 
	    
	    
	    getNewTree();
	    getXinXi();
	    
	    //�Զ�ˢ�� ��Ϣ���
	  /* new Thread(new Runnable(){
		   public void run(){
			   
			   while(true){
				   try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				   Display.getDefault().asyncExec(new Runnable(){
					   public void run(){
						   getNewXiaoXi();
					   }
				   });
				   
			   }
		   }
	   }).start();
	    */
	  //�����ק
	    composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if( dargFlag ){
										//MouseInfo.getPointerInfo().getLocation().x ������������
										//x  �����������
										//�����õ���������������
					shell.setLocation(MouseInfo.getPointerInfo().getLocation().x - x,
							MouseInfo.getPointerInfo().getLocation().y - y	);
										//������ƶ�������������ĵ��ƶ�����
				}
			}
		});
	    composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//��갴�£������ƶ�
				dargFlag = true;
				x = e.x;
				y = e.y;
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				//����ɿ��������ƶ�
				dargFlag = false;
			}
		});
	    /**
		 * ��С�� -��ť
		 */
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setMinimized(true);
			}
		});
		/**
		 * �ر� - ��ť
		 */
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sql="update qq set flag=1 where qid='"+QQDL.qid+"'";
				help.doUpdate(sql, null);
				System.exit(1);
			}
		});
	    
	    tableColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			//��ϵ��
			public void widgetSelected(SelectionEvent e) {
				composite_3.setVisible(false);
				composite_5.setVisible(false);
				scrolledComposite.setVisible(false);
				//flist.setVisible(true);
				//tree_2.setBackgroundImage(SWTResourceManager.getImage("D:\\image\\123.jpg"));
				tree_2.removeAll();
				List<Object>paramstt=new ArrayList<Object>();

				sql="select * from qfriend where qid1=? order by qfid asc";
				paramstt.add(QQDL.qid);
				List<Map<String,String>> list=help.findAll(sql,paramstt);
				
				
					ArrayList<String>arr=new ArrayList<String>();//�������� 
					boolean flag;
					for(int i=0;i<list.size();i++){
						flag=true;
						
						for(int j=0;j<arr.size();j++){
							if(list.get(i).get("GROUPNAME").equals(arr.get(j))){
								flag=false;
								break;
							}
						}
						if(flag){
							arr.add(list.get(i).get("GROUPNAME"));
						}
						
					}
					
					ArrayList <TreeItem> t=new ArrayList<TreeItem>();//�������ǵķ����
						for(int i=0;i<arr.size();i++){
							
							TreeItem trtmNewTreeitem_2 = new TreeItem(tree_2, SWT.NONE);
							trtmNewTreeitem_2.setText(arr.get(i));
							t.add(trtmNewTreeitem_2);
							for(int j=0;j<list.size();j++){
								if(arr.get(i).equals(list.get(j).get("GROUPNAME"))){
									if(list.get(j).get("QID2")==null ||"".equals(list.get(j).get("QID2"))){
										
									}else{
										sql="select * from qq where qid='"+list.get(j).get("QID2")+"'";
										List<QQ> tt=help.findAllQQ(sql, null,1);
										
										 TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem_2 , SWT.NONE);
										 if(list.get(j).get("BEIZU")==null ||"".equals(list.get(j).get("BEIZU"))){
											 trtmNewTreeitem_3.setText(tt.get(0).qname);
										 }else{
											 trtmNewTreeitem_3.setText(list.get(j).get("BEIZU"));
										 }
										// System.out.println(chatmap.get(tt.get(0).qid));
										 if(chatmap.get(tt.get(0).qid)==null){
											 
											 chatmap.put(tt.get(0).qid, new Chatting(tt.get(0).qid));
										 }
										 trtmNewTreeitem_3.setData("qid",tt.get(0).qid);
									}
								}
							}	
						}
						//tree.setHeaderVisible(true);
						//tree.setLinesVisible(true);
						Menu menu=new Menu(tree_2);   // ��tree������Ӳ˵�
						MenuItem itemAdd=new MenuItem(menu,SWT.PUSH);
						itemAdd.setText("�½�");
						MenuItem itemDel=new MenuItem(menu,SWT.PUSH);
						itemDel.setText("ɾ��");
						MenuItem itemUpd=new MenuItem(menu,SWT.PUSH);
						itemUpd.setText("������");
						MenuItem itemBeiZu=new MenuItem(menu,SWT.PUSH);
						itemBeiZu.setText("�޸ı�ע");
						MenuItem itemZiLiao=new MenuItem(menu,SWT.PUSH);
						itemZiLiao.setText("�鿴��������");
						MenuItem itemYiDong=new MenuItem(menu,SWT.PUSH);
						itemYiDong.setText("�ƶ���");
						
						//�ƶ�����
						itemYiDong.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								TreeItem ti=tree_2.getSelection()[0];
							//	System.out.println(ti.getData("qname"));
								if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){
							         return ;
					            }
								
								YiDongFriend ydf=new YiDongFriend();
								ydf.open();
								//�������ݿ�
								if(YiDongFriend.GroupName==null || "".equals(YiDongFriend.GroupName)){
									return ;
								}
								
								
								sql="update qfriend set groupname=? where  qid1=?  and qid2=?";
								List<Object>params=new ArrayList<Object>();
								
								params.add(YiDongFriend.GroupName);
								
								params.add(QQDL.qid);
								params.add(ti.getData("qid"));
								help.doUpdate(sql, params);
								for(int i=0;i<t.size();i++){
									//�õ�ѡ����Ǹ��е�����
									
									if(t.get(i).getText().equals(YiDongFriend.GroupName)){
											 TreeItem trtmNewTreeitem_3 = new TreeItem(t.get(i), SWT.NONE);
											 trtmNewTreeitem_3.setText(ti.getText());
											 trtmNewTreeitem_3.setData("qid",ti.getData("qid"));
											 break;
									}
								}
								ti.dispose();
								
								getNewTree();
							}
						});
						//�鿴��������
						itemZiLiao.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								//�û����������һ���������� ����
								TreeItem ti=tree_2.getSelection()[0];
								if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){// ѡ����� �б�
									return ;
								}else{
									beizu=ti.getText();
									//friendQQ=(String)ti.getData("qid");
									FriendZiLiao fzl=new FriendZiLiao((String)ti.getData("qid"),beizu);
									fzl.open();
									ti.setText(beizu);
								}
							}
						});
						//�޸ı�ע
						itemBeiZu.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								//�û����������һ���������� ����
								TreeItem ti=tree_2.getSelection()[0];
								TreeItem[] tichild=ti.getItems();//�õ�����ĺ���
								// ���������� ���� ��ȥ�ж����Ƿ��к���  
								if(tichild.length>0){
									
									mu.alert(shell, "��ʾ��Ϣ", "���ܴ˲���");
									return ;
								}else{
									
									
									if(ti.getData("qid")==null ||"".equals( ti.getData("qid"))){
										return ;
									}
									for(int i=0;i<list.size();i++){
										if(ti.getData("qid").equals(list.get(i).get("QID2"))){
											
											TiShi th=new TiShi();
											th.open();
											
											if(TiShi.atext==null ||"".equals(TiShi.atext)){
												return ;
											}
											
											sql="update qfriend set beizu=? where qid1=? and qid2=?";
											List<Object>paramsss=new ArrayList<Object>();
											paramsss.add(TiShi.atext);
											paramsss.add(QQDL.qid);
											paramsss.add(ti.getData("qid"));
											help.doUpdate(sql, paramsss);
											ti.setText(TiShi.atext);
											break;
										}
									}
								}
							}
							});
						//����һ���µķ���
						itemAdd.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								
								//�û����������һ���������� ����
								
								// ���������� ���� ��ȥ�ж����Ƿ��к���  
								TreeItem trtmNewTreeitem_3 = new TreeItem(tree_2, SWT.NONE);
								TiShi th=new TiShi();
								th.open();
								
								if(TiShi.atext==null || "".equals(TiShi.atext)){
									return ;
								}
								if(TiShi.atext.equals("�ҵĺ���")){
									mu.alert(shell, "��ʾ","���ܴ����˷���");
								}
								
								for(int i=0;i<list.size();i++){
									if(TiShi.atext.equals(list.get(i).get("GROUPNAME"))){
										mu.alert(shell, "��ʾ", "�����Ѿ�����");
										return ;
									}
								}
								
								trtmNewTreeitem_3.setText(TiShi.atext);
								t.add(trtmNewTreeitem_3);
								arr.add(TiShi.atext);
								sql="insert into qfriend values(seq_qfid.nextval,?,?,'','')";
								List<Object >paramsadd=new ArrayList<Object>();
								paramsadd.add(QQDL.qid);
								paramsadd.add(TiShi.atext);
								help.doUpdate(sql, paramsadd);
								getNewTree();
							}
						});
						//ɾ��һ������
						itemDel.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								
								//�û����������һ���������� ����
								TreeItem ti=tree_2.getSelection()[0];
								TreeItem[] tichild=ti.getItems();//�õ�����ĺ���
								if(tichild.length>0){ // ���������к���
									if("�ҵĺ���".equals(ti.getText())){
										mu.alert(shell, "��ʾ��Ϣ", "�ҵĺ��� �@���ֽM���܄h��");
										return ;
									}else{
										sql="update  qfriend set groupname='�ҵĺ���' where qid1=? and groupname =?";
										List<Object> params3=new ArrayList<Object>();
										params3.add(QQDL.qid);
										params3.add(ti.getText());
										help.doUpdate(sql, params3);
									}
								}else{
									if("�ҵĺ���".equals(ti.getText())){
										mu.alert(shell, "��ʾ��Ϣ", "�ҵĺ��� �@���ֽM���܄h��");
										return ;
									}
									//  ������ɾ���� ������ɾ������
									if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){//ɾ�����ǿշ���
									sql="delete qfriend where qid1=? and groupname =?";
										List<Object> params3=new ArrayList<Object>();
										params3.add(QQDL.qid);
										params3.add(ti.getText());
										help.doUpdate(sql, params3);
									}else{
										
										
										// ɾ������ ����   ɾ���˾ͻ�˫�� ɾ��
										
										DelFriend df=new DelFriend(ti.getData("qid").toString());
										
										df.open();
									}
								}
								
								getNewTree();
								// ���������� ���� ��ȥ�ж����Ƿ��к���  
								/*if(tichild.length>0){ // �к���  ����ɾ������
									if("�ҵĺ���".equals(ti.getText())){
										mu.alert(shell, "��ʾ��Ϣ", "�ҵĺ��� �@���ֽM���܄h��");
										return ;
									}
										for(int i=0;i<t.size();i++){
											
											if(t.get(i).getText().equals("�ҵĺ���")){
												for(int j=0;j<tichild.length;j++){
													 TreeItem trtmNewTreeitem_3 = new TreeItem(t.get(i), SWT.NONE);
													 trtmNewTreeitem_3.setText(tichild[j].getText());
													 trtmNewTreeitem_3.setData("qid", tichild[j].getData("qid"));
													 
												}
											}
										}
										//�������ݿ�   ��ɾ��������� ȫ��ɾ��   ��ɾ���� ȫ����ӵ��ҵĺ���  ���������
										for(int k=0;k<list.size();k++){
											if(list.get(k).get("GROUPNAME").equals(ti.getText())){
												String sql3="update  qfriend set groupname='�ҵĺ���' where qid1=? and groupname =?";
												String sql4="delete  qfriend where qid1=? and groupname =?";
												List<Object> params3=new ArrayList<Object>();
												params3.add(QQDL.qid);
												params3.add(ti.getText());
												help.doUpdate(sql3, params3);
												
												help.doUpdate(sql4, params3);
											}
										}
										ti.dispose();
										
								}else{// û����   ������һ������ ������һ������
									
									
									if(ti.getText().equals("�ҵĺ���")){
										mu.alert(shell, "��ʾ��Ϣ", "�ҵĺ��� �@���ֽM���܄h��");
										return ;
									}
									if(ti.getData("qid")==null||"".equals(ti.getData("qid"))){//����
										String sql2="delete from qfriend where qid1=? and groupname =?";
										List<Object> params2=new ArrayList<Object>();
										params2.add(QQDL.qid);
										params2.add(ti.getText());
										help.doUpdate(sql2, params2);
									}else{// Ϊ��
										String sql2="delete from qfriend where qid1=? and  qid2=?";
										List<Object> params2=new ArrayList<Object>();
										params2.add(QQDL.qid);
										params2.add(ti.getData("qid"));
										help.doUpdate(sql2, params2);
									}
									
									ti.dispose();
								}*/
							}
						});
						// ����һ������
						itemUpd.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								
								List<Map<String,String>> list=help.findAll(sql, null);
								//�û����������һ���������� ����
								TreeItem ti=tree_2.getSelection()[0];
								
								TreeItem[] tichild=ti.getItems();//�õ�����ĺ���     û�еĻ� �õ��Լ��ұ���
								// ���������� ���� ��ȥ�ж����Ƿ��к���  
								if(ti.getText().equals("�ҵĺ���")){
									mu.alert(shell, "��ʾ��Ϣ", "�ҵĺ��� �@���ֽM���ܸ���");
									return ;
								}
								if(ti.getData("qid")==null||"".equals(ti.getData("qid"))){
									
								}else{
									mu.alert(shell, "��ʾ","���ܴ˲���");
									return ;
								}
								TiShi th=new TiShi();
								th.open();
								if(TiShi.atext==null ||"".equals(TiShi.atext)){
									return ;
								}
								for(int i=0;i<list.size();i++){
									if(TiShi.atext.equals(list.get(i).get("GROUPNAME"))){
										mu.alert(shell, "��ʾ", "�����Ѿ�����");
										return ;
									}
								}
								
								
								if(TiShi.atext.equals("�ҵĺ���")){
									mu.alert(shell, "��ʾ","���ܴ����˷���");
								}
								
		
									sql="update qfriend set groupname =?  where qid1=? and groupname=?";
									List<Object >paramsadd=new ArrayList<Object>();
									paramsadd.add(TiShi.atext);
									paramsadd.add(QQDL.qid);
									paramsadd.add(ti.getText());
									ti.setText(TiShi.atext);
									help.doUpdate(sql, paramsadd);
							}
						});
						
						//��갴���¼�
						tree_2.addMouseListener(new MouseAdapter(){
							public void mouseDown(MouseEvent e){
								
								if(e.button==3){
									//��ʾ�˵�
									tree_2.setMenu(menu);
								}
							}
						});
						tree_2.addSelectionListener(new SelectionAdapter() {
							@Override
							//������  ����һ���ػ�����
							public void widgetSelected(SelectionEvent e) {
								
							}
						});
				
				composite_6.setVisible(true);
			}
		});
	    
	    
	    tree_1.addSelectionListener(new SelectionAdapter() {
	    	
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    	}
	    	@Override
	    	public void widgetDefaultSelected(SelectionEvent e) {
	    		TreeItem ti=(TreeItem) e.item;
	    		if(chatmap.get(ti.getData("qid")).shell==null)
	    		chatmap.get(ti.getData("qid")).open();
	    		else if(chatmap.get(ti.getData("qid")).shell.isDisposed()){
    				chatmap.get(ti.getData("qid")).open();
    			}
	    	}
	    });
	    
	    
	    tblclmnNewColumn_1.addSelectionListener(new SelectionAdapter(){
			//���Ѷ�̬
			public void widgetSelected(SelectionEvent e) {
				
				//����  ���к��ѵ�qid
				sql = "select * from shuoshuo where qid in (select qid2 from qfriend where qid1 = '"+QQDL.qid+"') "
						+ "or qid = '"+QQDL.qid+"' order by qtime desc";
				List<Shuoshuo> listshuo2 = db.findAll(sql, null, Shuoshuo.class);
				if (listshuo2.size() > 0) {
					composite_4 = new Composite(scrolledComposite, SWT.NONE);
					scrolledComposite.setContent(composite_4);							
					// ��ջ������ʾ
					stackLayou.topControl = scrolledComposite;
					scrolledComposite.setVisible(true);
					composite_3.setVisible(false);
					// flist.setVisible(false);
					composite_5.setVisible(false);
					composite_6.setVisible(false);
					scrolledComposite.setMinSize(280, listshuo2.size() * 300);

					AotoGroup ag = new AotoGroup();
					for (int j = 0; j < listshuo2.size(); j++) {
						ag.createGroup(composite_4, listshuo2.get(j));
					}
				}					
			}
		});
	
	    tblclmnNewColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			//��Ϣ����
			public void widgetSelected(SelectionEvent e) {
				
			//	flist.setVisible(false);
				composite_5.setVisible(false);
				scrolledComposite.setVisible(false);
				
				tree.removeAll();
				//ntai.setVisible(false);
			
				//�����Ϣ  �Զ�ȥ���ݿ�����  ���ѱ������������Ϣ   int flag  Ϊ1 ����δ��    �о�ˢ�� һ����ӿ�
				//û�еĻ��Ͳ�ִ��
				//���Ѿ��򿪵Ļػ���� treeitem  ��gettext��ֵ  ����   flag   Ϊ1  �����Ѿ�������  Ϊ�������ʾ����
				// �����ػ���  ���˷��������id �Ļػ�     ����һ�� int flag  Ϊ1������û�н���  
				//�����ݿ�����ĺ��ѱ�ȥ����        qname Ϊ��ֵ��  flag Ϊ0   ��������  ����ͬ��   ͬ���˾͸������ݿ�  ��flag=1
				//��   ����һ���Լ������ݿ�
				
				// �鿴������
				sql="select * from getfriend where qid2 ='"+QQDL.qid+"'";
				List<Map<String,String>>list=help.findAll(sql, null);
				List<String>aa=new ArrayList<String>();
				
				if(list.size()>0){
					int a;
					for(int i=0;i<list.size();i++){
						 a=1;
						for(int j=0;j<aa.size();j++){
							if(list.get(i).get("QID1").equals(aa.get(j))){
								a=2;
								break;
							}
						}
						if(a==1){
							aa.add(list.get(i).get("QID1"));
							TreeItem ti=new TreeItem(tree,SWT.NONE);
							sql="select * from qq  where qid='"+list.get(i).get("QID1")+"'";//�ȵ����������
							List<QQ>list1=help.findAllQQ(sql, null, 0);
							ti.setText(list1.get(0).qname+"  ��������");
							ti.setData("qid",list1.get(0).qid);
						}

					}
				}
				
				
				//����Ự������ �м������Լ�û�ж���
			    sql="select * from qcontent where qid2='"+QQDL.qid+"'";
				List<Map<String,String>>list2=help.findAll(sql, null);  //�õ������˵� qid
				
				//��ʼ����    һ���˶�θ��㷢��     ����Ҫ����  qID 
				ArrayList<String> qid=new ArrayList<String>();// ���� ȥ��֮�����з����˵�qid
				boolean f;
				for(int i=0;i<list2.size();i++){
					f=true;
					for(int j=0;j<qid.size();j++){
						if(list2.get(i).get("QID1").equals(qid.get(j))){
							f=false;
							break;
						}
					}
					if(f)
					qid.add(list2.get(i).get("QID1"));
				}
				
				if(qid.size()>0){
					
					for(int i=0;i<qid.size();i++){
						
						sql="select * from qq where qid=?";
						List<Object >params3=new ArrayList<Object>();
						params3.add(qid.get(i));
						List<QQ>list3=help.findAllQQ(sql, params3, 0);//�õ������˵����е���Ϣ
						
						// �����ɾ������    �ǽ��ܲ��˱��˻ػ���   
						sql="select * from qfriend where qid1=? and qid2=?";
						List<Object >params4=new ArrayList<Object>();
						params4.add(QQDL.qid);
						params4.add(qid.get(i));
						List <Map<String,String>>list4=help.findAll(sql, params4);//�õ� ����Ϣ�˵ı�ע������
						
						if(list4.size()>0){
							
							//������ ֻ��ʾ���   ��ע ���� qq����
							TreeItem ti=new TreeItem(tree,SWT.NONE);
							if(list4.get(0).get("BEIZU")==null||"".equals(list4.get(0).get("BEIZU"))){
								ti.setText(list4.get(0).get("QNAME"));
								ti.setData("qid", list4.get(0).get("QID2"));
							}else{
								ti.setText(list4.get(0).get("BEIZU"));
								ti.setData("qid", list4.get(0).get("QID2"));
							}
						}
					}
				}
				
				composite_3.setVisible(true);
			}
		});
	    /**
	     * Ӧ��
	     */
	    lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				AppUi au = new AppUi();
				a.qid = QQDL.qid;
				au.open();
			}
		});
	    
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			//��Ӻ��ѽ���
			public void mouseDown(MouseEvent e) {
				TianJia tj=new TianJia();
				tj.open();
			}
		});
		//�������  ��ʾ����ʾ��Ϣ�ĺ���  ����    �������� �ۿۺ� ���߱�ע
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			    tree_1.removeAll();
				composite_3.setVisible(false);
				
				scrolledComposite.setVisible(false);
			//	flist.setVisible(false);
				composite_6.setVisible(false);
				//String ttext=text.getText();
				if(text.getText()==null||"".equals(text.getText())){
					sql="select * from qfriend where qid1='"+QQDL.qid+"' and qid2 is not null";
					List<Map<String,String>>sousuolist=help.findAll(sql, null);

					for(int i=0;i<sousuolist.size();i++){
						
						TreeItem ti=new TreeItem(tree_1,SWT.NONE);
						
						if(sousuolist.get(i).get("BEIZU")==null||"".equals(sousuolist.get(i).get("BEIZU"))){
							sql="select q.qname from qq q where qid='"+sousuolist.get(i).get("QID2")+"'";
							List<Map<String,String>> at=help.findAll(sql, null);
							
							ti.setText(at.get(0).get("QNAME"));
						}else{
							ti.setText(sousuolist.get(i).get("BEIZU"));
						}
						ti.setData("qid", sousuolist.get(i).get("QID2"));
					}
				}else{
					sql="select * from qfriend where qid1='"+QQDL.qid+"' and qid2 like '%"+text.getText()+"%'";
					List<Map<String,String>>sousuolist=help.findAll(sql, null);

					for(int i=0;i<sousuolist.size();i++){
						
						sql="select q.qname from qq q where qid='"+sousuolist.get(i).get("QID2")+"'";
						List<Map<String,String>> at=help.findAll(sql, null);
						
						TreeItem ti=new TreeItem(tree_1,SWT.NONE);
						
						if(sousuolist.get(i).get("BEIZU")==null||"".equals(sousuolist.get(i).get("BEIZU"))){
							ti.setText(at.get(0).get("QNAME"));
						}else{
							ti.setText(sousuolist.get(i).get("BEIZU"));
						}
						ti.setData("qid", sousuolist.get(i).get("QID2"));
					}
				}
				 Menu menu=new Menu(tree_1);   // ��tree������Ӳ˵�
					/*MenuItem itemAdd=new MenuItem(menu,SWT.PUSH);
					itemAdd.setText("");*/
				    MenuItem itemBeiZu=new MenuItem(menu,SWT.PUSH);
					itemBeiZu.setText("�޸ı�ע");
					MenuItem itemZiLiao=new MenuItem(menu,SWT.PUSH);
					itemZiLiao.setText("�鿴��������");
					MenuItem itemYiDong=new MenuItem(menu,SWT.PUSH);
					itemYiDong.setText("�ƶ���");
					//�ƶ�
					
					itemYiDong.addListener(SWT.Selection,new Listener(){
						public void handleEvent(Event agr0){
							TreeItem ti=tree_1.getSelection()[0];
						//	System.out.println(ti.getData("qname"));
							if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){
						         return ;
				            }
							YiDongFriend ydf=new YiDongFriend();
							ydf.open();
							String t=ydf.GroupName;
							if(t==null || "".equals(t)){
								return ;
							}
							
							
							sql="select * from qfriend where qid1=? and qid2=?";
							List<Object>tparams=new ArrayList<Object>();
							tparams.add(QQDL.qid);
							tparams.add(ti.getData("qid"));
							List<Map<String,String>>listt=help.findAll(sql, tparams);
							//�������ݿ�
							sql="update qfriend set groupname=? where qid2=? and qid1=?";
							List<Object>params=new ArrayList<Object>();
							params.add(t);
							params.add(ti.getData("qid"));
							params.add(QQDL.qid);
							help.doUpdate(sql, params);
							/*for(int i=0;i<t.size();i++){
								//�õ�ѡ����Ǹ��е�����
								if(t.get(i).getText().equals(YiDongFriend.GroupName)){
										 TreeItem trtmNewTreeitem_3 = new TreeItem(t.get(i), SWT.NONE);
										 trtmNewTreeitem_3.setText(ti.getText());
										 trtmNewTreeitem_3.setData("qname",ti.getData("qname"));
										 break;
								}
							}*/
							//ti.dispose();
						}
					});
					//�鿴��������
					itemZiLiao.addListener(SWT.Selection,new Listener(){
						public void handleEvent(Event agr0){
							
							//�û����������һ���������� ����
							TreeItem ti=tree_1.getSelection()[0];
							
								beizu=ti.getText();
								//friendQQ=(String)ti.getData("qid");
							//	System.out.println(friendQQ);
								FriendZiLiao fzl=new FriendZiLiao((String)ti.getData("qid"));
								fzl.open();
								ti.setText(beizu);
						}
					});
					
					//�޸ı�ע
					itemBeiZu.addListener(SWT.Selection,new Listener(){
						public void handleEvent(Event agr0){
							
			                sql="select * from qfriend where qid1='"+QQDL.qid+"'";
							List<Map<String,String>> list=help.findAll(sql, null);
							//�û����������һ���������� ����
							TreeItem ti=tree_1.getSelection()[0];
							TreeItem[] tichild=ti.getItems();//�õ�����ĺ���
							// ���������� ���� ��ȥ�ж����Ƿ��к���  
							if(tichild.length>0){
								mu.alert(shell, "��ʾ��Ϣ", "���ܴ˲���");
							}else{
								for(int i=0;i<list.size();i++){
									
									if(ti.getData("qid").equals(list.get(i).get("QID2"))){
										sql="update qfriend set beizu=? where qid2=? and qid1=?";
										List<Object>paramsss=new ArrayList<Object>();
										TiShi th=new TiShi();
										th.open();
										if(TiShi.atext==null ||"".equals(TiShi.atext)){
											return ;
										}
										
										paramsss.add(TiShi.atext);
										paramsss.add(ti.getData("qid"));
										paramsss.add(QQDL.qid);
										help.doUpdate(sql, paramsss);
										ti.setText(TiShi.atext);
										
										break;
									}
								}
							}
						}
						});
					
				tree_1.addMouseListener(new MouseAdapter(){
					public void mouseDown(MouseEvent e){
						
						if(e.button==3){
							//��ʾ�˵�
							tree_1.setMenu(menu);
						}
					}
				});
				composite_5.setVisible(true);
				
			}
		});
		//ͷ���������¼�
		// ��
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MyZiLiao mzl=new MyZiLiao();
				mzl.open();
				getXinXi();
				
				/*String sql="select * from qq where qid=?";
				List <Object> params=new ArrayList<Object>();
				params.add(QQDL.qid);
				List<QQ> list =help.findAllQQ(sql, params, 1);
				int width,height;
				width=lblNewLabel_1.getBounds().width;
				height=lblNewLabel_1.getBounds().height;
				InputStream is=new ByteArrayInputStream(list.get(0).qimage);
				ImageData id=new ImageData(is);
				id=id.scaledTo(width, height);
				Image image=new Image(null,id);
				lblNewLabel_1.setImage(image);
				text_1.setText(list.get(0).qname);*/
				
			}
		});
		// �������˵˵
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				SendDynamic sd=new SendDynamic();
				sd.open();
				
			}
		});
		// ��Ϣ������ĵ���¼� 
		 tree.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseDown(MouseEvent e) {
		    		
		    		
		    	}
		 	@Override
		 	public void mouseDoubleClick(MouseEvent e) {
		 		if(tree.getSelection().length==0){
	    			return ;
	    		}
	    		
	    		TreeItem ti=tree.getSelection()[0];	
	    		
	    		
	    		List<Object> params1=new ArrayList<Object>();
	    		sql="select * from getfriend where qid2=? and qid1=?";
	    		params1.add(QQDL.qid);
	    		params1.add(ti.getData("qid"));
	    		List<Map<String,String>>list1=help.findAll(sql, params1);
	    		
	    		if(list1.size()>0){// ������� �����������Ϣ
	    			//friendQQ=ti.getData("qid").toString();
	    			tysq=new TongYiShengQing(ti.getData("qid").toString());
	    			if(tysq.shell==null){
	    			tysq.open();
	    			getNewXiaoXi();
	    			}else if(tysq.shell.isDisposed()){
	    				tysq.open();
		    			getNewXiaoXi();
	    			}
	    		}else{  // ������Ϣ�����
	    			
	    			if(chatmap.get(ti.getData("qid")).shell==null)
	    			chatmap.get(ti.getData("qid")).open();
	    			else if(chatmap.get(ti.getData("qid")).shell.isDisposed()){
	    				chatmap.get(ti.getData("qid")).open();
	    			}
	    		}
		 		
		 		
		 	}
		    });
		
		//�����Ϣ
		/* tree.addSelectionListener(new SelectionAdapter() {
		    	@Override
		    	
		    	
		    	
		    	
		    	public void widgetSelected(SelectionEvent e) {
		    		TreeItem ti=(TreeItem)e.item;
		    		
		    		TreeItem t[]=ti.getItems();
		    		
		    		if(t.length>0){//������ ��Ӻ���
		    		}else if(ti.getData("qid")!=null ||"".equals(ti.getData("qid"))){//����� ��������
		    			friendQQ=ti.getData("qid").toString();
		    			TongYiShengQing tysq=new TongYiShengQing();
		    			tysq.open();
		    			getNewXiaoXi();
		    			
		    		}else{//���յ�����Ϣ
		    			friendqname=(String )ti.getData("qid");
		    			HuiHua hh=new HuiHua();
		    			hh.open();
		    		}
		    	}
		    });*/
		 
		 
		 //�����ϵ��
		 tree_2.addSelectionListener(new SelectionAdapter() {

	    	public void widgetDefaultSelected(SelectionEvent e) {
	    		TreeItem ti=(TreeItem)e.item;
	    		if(ti.getData("qid")==null|| "".equals(ti.getData("qid"))){
	    			
	    		}else{
	    			//System.out.println(chatmap.get(ti.getData("qid")));
	    			
	    			if(chatmap.get(ti.getData("qid")).shell==null){
	    				
	    				chatmap.get(ti.getData("qid")).open();
	    			}else if(chatmap.get(ti.getData("qid")).shell.isDisposed()){
	    				chatmap.get(ti.getData("qid")).open();
	    			}
	    			
	    			
	    			
	    			
	    			//��ϵ��
//	    			friendqname=ti.getData("qid").toString();
//	    			HuiHua hh=new HuiHua();
//	    			hh.open();
	    		}
	    	}
	   
		    	/*public void widgetSelected(SelectionEvent e) {
		    		TreeItem ti=(TreeItem)e.item;
		    		if(ti.getData("qid")==null|| "".equals(ti.getData("qid"))){
		    			
		    		}else{
		    			System.out.println(chatmap.get(ti.getData("qid")));
		    			chatmap.get(ti.getData("qid")).open();
		    			
		    			//��ϵ��
//		    			friendqname=ti.getData("qid").toString();
//		    			HuiHua hh=new HuiHua();
//		    			hh.open();
		    		}
		    	}*/
		    });

	}
	public void getXinXi(){
		
		sql="select * from qq where qid=? and pwd=?";
		List <Object> params=new ArrayList<Object>();
		params.add(QQDL.qid);
		params.add(QQDL.pwd);
		List<QQ> list =help.findAllQQ(sql, params, 1);		
		int width,height;
		width=lblNewLabel_1.getBounds().width;
		height=lblNewLabel_1.getBounds().height;
		InputStream is=new ByteArrayInputStream(list.get(0).qimage);
		ImageData id=new ImageData(is);
		id=id.scaledTo(width, height);
		Image image=new Image(null,id);
		lblNewLabel_1.setImage(image);
		lblNewLabel_3.setText(list.get(0).qname);
		if(!"".equals(list.get(0).motto) && list.get(0).motto != null){
			lblNewLabel_4.setText(list.get(0).motto);
		}else{
			lblNewLabel_4.setText("����˺�������û������ǩ��");
		}
	}
	
	public void getNewXiaoXi(){
		
		    tree.removeAll();
			//ntai.setVisible(false);
			scrolledComposite.setVisible(false);
			//�����Ϣ  �Զ�ȥ���ݿ�����  ���ѱ������������Ϣ   int flag  Ϊ1 ����δ��    �о�ˢ�� һ����ӿ�
			//û�еĻ��Ͳ�ִ��
			//���Ѿ��򿪵Ļػ���� treeitem  ��gettext��ֵ  ����   flag   Ϊ1  �����Ѿ�������  Ϊ�������ʾ����
			// �����ػ���  ���˷��������id �Ļػ�     ����һ�� int flag  Ϊ1������û�н���  
			//�����ݿ�����ĺ��ѱ�ȥ����        qname Ϊ��ֵ��  flag Ϊ0   ��������  ����ͬ��   ͬ���˾͸������ݿ�  ��flag=1
			//��   ����һ���Լ������ݿ�
			sql="select * from getfriend where qid2 ='"+QQDL.qid+"'";
			List<Map<String,String>>list=help.findAll(sql, null);
			List<String>aa=new ArrayList<String>();
			
			if(list.size()>0){
				int a;
				for(int i=0;i<list.size();i++){
					 a=1;
					for(int j=0;j<aa.size();j++){
						if(list.get(i).get("QID1").equals(aa.get(j))){
							a=2;
							break;
						}
					}
					if(a==1){
						aa.add(list.get(i).get("QID1"));
						TreeItem ti=new TreeItem(tree,SWT.NONE);
						sql="select * from qq  where qid='"+list.get(i).get("QID1")+"'";//�ȵ����������
						List<QQ>list1=help.findAllQQ(sql, null, 0);
						ti.setText(list1.get(0).qname+"  ��������");
						ti.setData("qid",list1.get(0).qid);
					}

				}
			}
			//����ػ������� �м������Լ�û�ж���
			//����Ự������ �м������Լ�û�ж���
			sql="select * from qcontent where qid2='"+QQDL.qid+"'";
			List<Map<String,String>>list2=help.findAll(sql, null);  //�õ������˵� qid
			
			//��ʼ����    һ���˶�θ��㷢��     ����Ҫ����  qID 
			ArrayList<String> qid=new ArrayList<String>();// ���� ȥ��֮�����з����˵�qid
			
			for(int i=0;i<list2.size();i++){
				
				for(int j=0;j<qid.size();j++){
					if(list2.get(i).get("QID1").equals(qid.get(j))){
						break;
					}
				}
				qid.add(list2.get(i).get("QID1"));
			}
			
			if(qid.size()>0){
				
				for(int i=0;i<qid.size();i++){
					
					sql="select * from qq where qid=?";
					List<Object >params3=new ArrayList<Object>();
					params3.add(qid.get(i));
					List<QQ>list3=help.findAllQQ(sql, params3, 0);//�õ������˵����е���Ϣ
					
					
					// �����ɾ������    �ǽ��ܲ��˱��˻ػ���   
					String sql4="select * from qfriend where qid1=? and qid2=?";
					List<Object >params4=new ArrayList<Object>();
					params4.add(QQDL.qid);
					params4.add(qid.get(i));
					List <Map<String,String>>list4=help.findAll(sql4, params4);//�õ� ����Ϣ�˵ı�ע������
					
					if(list4.size()>0){
						
						//������ ֻ��ʾ���   ��ע ���� qq����
						
						TreeItem ti=new TreeItem(tree,SWT.NONE);
						if(list4.get(0).get("BEIZU")==null||"".equals(list4.get(0).get("BEIZU"))){
							ti.setText(list4.get(0).get("QNAME"));
							ti.setData("qid", list4.get(0).get("QID2"));
						}else{
							ti.setText(list4.get(0).get("BEIZU"));
							ti.setData("qid", list4.get(0).get("QID2"));
						}
					}
				}
			}
	
	}
	public void getNewTree(){
		tree_2.removeAll();
		chatmap.clear();
		List<Object>paramstt=new ArrayList<Object>();

		sql="select * from qfriend where qid1=? order by qfid asc";
		paramstt.add(QQDL.qid);
		List<Map<String,String>> list=help.findAll(sql,paramstt);
		
		
			ArrayList<String>arr=new ArrayList<String>();//�������� 
			boolean flag;
			for(int i=0;i<list.size();i++){
				flag=true;
				
				for(int j=0;j<arr.size();j++){
					if(list.get(i).get("GROUPNAME").equals(arr.get(j))){
						flag=false;
						break;
					}
				}
				if(flag){
					arr.add(list.get(i).get("GROUPNAME"));
				}
				
			}
			
			ArrayList <TreeItem> t=new ArrayList<TreeItem>();//�������ǵķ����
				for(int i=0;i<arr.size();i++){
					
					TreeItem trtmNewTreeitem_2 = new TreeItem(tree_2, SWT.NONE);
					trtmNewTreeitem_2.setText(arr.get(i));
					t.add(trtmNewTreeitem_2);
					for(int j=0;j<list.size();j++){
						if(arr.get(i).equals(list.get(j).get("GROUPNAME"))){
							if(list.get(j).get("QID2")==null ||"".equals(list.get(j).get("QID2"))){
								
							}else{
								sql="select * from qq where qid='"+list.get(j).get("QID2")+"'";
								List<QQ> tt=help.findAllQQ(sql, null,1);
								
								 TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem_2 , SWT.NONE);
								 if(list.get(j).get("BEIZU")==null ||"".equals(list.get(j).get("BEIZU"))){
									 trtmNewTreeitem_3.setText(tt.get(0).qname);
								 }else{
									 trtmNewTreeitem_3.setText(list.get(j).get("BEIZU"));
								 }
								// System.out.println(chatmap.get(tt.get(0).qid));
									 chatmap.put(tt.get(0).qid, new Chatting(tt.get(0).qid));
								 trtmNewTreeitem_3.setData("qid",tt.get(0).qid);
							}
						}
					}	
				}
		
		
		
		 /* 
		List<Object>paramstt=new ArrayList<Object>();

		String sql="select * from qfriend where qid1=? order by qfid asc";
		paramstt.add(QQDL.qid);
		List<Map<String,String>> list=help.findAll(sql,paramstt);
	
		
			ArrayList<String>arr=new ArrayList<String>();//�������� 
			boolean flag;
			for(int i=0;i<list.size();i++){
				flag=true;
				
				for(int j=0;j<arr.size();j++){
					if(list.get(i).get("GROUPNAME").equals(arr.get(j))){
						flag=false;
						break;
					}
				}
				if(flag){
					arr.add(list.get(i).get("GROUPNAME"));
				}
				
			}
			
			ArrayList <TreeItem> t=new ArrayList<TreeItem>();//�������ǵķ����
				for(int i=0;i<arr.size();i++){
					
					TreeItem trtmNewTreeitem_2 = new TreeItem(tree_2, SWT.NONE);
					trtmNewTreeitem_2.setText(arr.get(i));
					t.add(trtmNewTreeitem_2);
					for(int j=0;j<list.size();j++){
						if(arr.get(i).equals(list.get(j).get("GROUPNAME"))){
							if(list.get(j).get("QID2")==null ||"".equals(list.get(j).get("QID2"))){
								
							}else{
								String sqlt="select * from qq where qid='"+list.get(j).get("QID2")+"'";
								List<QQ> tt=help.findAllQQ(sqlt, null,1);
								
								 TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem_2 , SWT.NONE);
								 if(list.get(j).get("BEIZU")==null ||"".equals(list.get(j).get("BEIZU"))){
									 trtmNewTreeitem_3.setText(tt.get(0).qname);
								 }else{
									 trtmNewTreeitem_3.setText(list.get(j).get("BEIZU"));
								 }
								 chatmap.put(tt.get(0).qid, new Chatting(tt.get(0).qid));
								 trtmNewTreeitem_3.setData("qid",tt.get(0).qid);
							}
						}
					}	
				}
				*/
	}
}
