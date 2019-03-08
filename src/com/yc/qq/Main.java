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
	private int x,y; //鼠标点击时的坐标
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
	    
	    //自动刷新 消息面板
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
	  //面板拖拽
	    composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if( dargFlag ){
										//MouseInfo.getPointerInfo().getLocation().x 距桌面左侧距离
										//x  距面板左侧距离
										//相减后得到面板距桌面左侧距离
					shell.setLocation(MouseInfo.getPointerInfo().getLocation().x - x,
							MouseInfo.getPointerInfo().getLocation().y - y	);
										//与鼠标移动先坐标相减，的到移动距离
				}
			}
		});
	    composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//鼠标按下，可以移动
				dargFlag = true;
				x = e.x;
				y = e.y;
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				//鼠标松开，不能移动
				dargFlag = false;
			}
		});
	    /**
		 * 最小化 -按钮
		 */
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setMinimized(true);
			}
		});
		/**
		 * 关闭 - 按钮
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
			//联系人
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
				
				
					ArrayList<String>arr=new ArrayList<String>();//保存列名 
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
					
					ArrayList <TreeItem> t=new ArrayList<TreeItem>();//保存他们的分组根
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
						Menu menu=new Menu(tree_2);   // 给tree创建添加菜单
						MenuItem itemAdd=new MenuItem(menu,SWT.PUSH);
						itemAdd.setText("新建");
						MenuItem itemDel=new MenuItem(menu,SWT.PUSH);
						itemDel.setText("删除");
						MenuItem itemUpd=new MenuItem(menu,SWT.PUSH);
						itemUpd.setText("重命名");
						MenuItem itemBeiZu=new MenuItem(menu,SWT.PUSH);
						itemBeiZu.setText("修改备注");
						MenuItem itemZiLiao=new MenuItem(menu,SWT.PUSH);
						itemZiLiao.setText("查看好友资料");
						MenuItem itemYiDong=new MenuItem(menu,SWT.PUSH);
						itemYiDong.setText("移动到");
						
						//移动好友
						itemYiDong.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								TreeItem ti=tree_2.getSelection()[0];
							//	System.out.println(ti.getData("qname"));
								if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){
							         return ;
					            }
								
								YiDongFriend ydf=new YiDongFriend();
								ydf.open();
								//更新数据库
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
									//得到选择的那个列的名称
									
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
						//查看好友资料
						itemZiLiao.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								//用户点击到的这一个列名或者 人们
								TreeItem ti=tree_2.getSelection()[0];
								if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){// 选择的是 列表
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
						//修改备注
						itemBeiZu.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								//用户点击到的这一个列名或者 人们
								TreeItem ti=tree_2.getSelection()[0];
								TreeItem[] tichild=ti.getItems();//得到分组的孩子
								// 如果点击的是 分组 先去判断它是否有孩子  
								if(tichild.length>0){
									
									mu.alert(shell, "提示信息", "不能此操作");
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
						//创建一个新的分组
						itemAdd.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								
								//用户点击到的这一个列名或者 人们
								
								// 如果点击的是 分组 先去判断它是否有孩子  
								TreeItem trtmNewTreeitem_3 = new TreeItem(tree_2, SWT.NONE);
								TiShi th=new TiShi();
								th.open();
								
								if(TiShi.atext==null || "".equals(TiShi.atext)){
									return ;
								}
								if(TiShi.atext.equals("我的好友")){
									mu.alert(shell, "提示","不能创建此分组");
								}
								
								for(int i=0;i<list.size();i++){
									if(TiShi.atext.equals(list.get(i).get("GROUPNAME"))){
										mu.alert(shell, "提示", "分组已经存在");
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
						//删除一个分组
						itemDel.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								List<Map<String,String>> list=help.findAll(sql, null);
								
								//用户点击到的这一个列名或者 人们
								TreeItem ti=tree_2.getSelection()[0];
								TreeItem[] tichild=ti.getItems();//得到分组的孩子
								if(tichild.length>0){ // 分组里面有孩子
									if("我的好友".equals(ti.getText())){
										mu.alert(shell, "提示信息", "我的好友 這個分組不能刪除");
										return ;
									}else{
										sql="update  qfriend set groupname='我的好友' where qid1=? and groupname =?";
										List<Object> params3=new ArrayList<Object>();
										params3.add(QQDL.qid);
										params3.add(ti.getText());
										help.doUpdate(sql, params3);
									}
								}else{
									if("我的好友".equals(ti.getText())){
										mu.alert(shell, "提示信息", "我的好友 這個分組不能刪除");
										return ;
									}
									//  可能是删好友 或者是删除分组
									if(ti.getData("qid")==null ||"".equals(ti.getData("qid"))){//删除的是空分组
									sql="delete qfriend where qid1=? and groupname =?";
										List<Object> params3=new ArrayList<Object>();
										params3.add(QQDL.qid);
										params3.add(ti.getText());
										help.doUpdate(sql, params3);
									}else{
										
										
										// 删除的是 好友   删除了就会双向 删除
										
										DelFriend df=new DelFriend(ti.getData("qid").toString());
										
										df.open();
									}
								}
								
								getNewTree();
								// 如果点击的是 分组 先去判断它是否有孩子  
								/*if(tichild.length>0){ // 有孩子  代表删除的是
									if("我的好友".equals(ti.getText())){
										mu.alert(shell, "提示信息", "我的好友 這個分組不能刪除");
										return ;
									}
										for(int i=0;i<t.size();i++){
											
											if(t.get(i).getText().equals("我的好友")){
												for(int j=0;j<tichild.length;j++){
													 TreeItem trtmNewTreeitem_3 = new TreeItem(t.get(i), SWT.NONE);
													 trtmNewTreeitem_3.setText(tichild[j].getText());
													 trtmNewTreeitem_3.setData("qid", tichild[j].getData("qid"));
													 
												}
											}
										}
										//更新数据库   将删除列下面的 全部删除   将删除的 全部添加到我的好友  这个列里面
										for(int k=0;k<list.size();k++){
											if(list.get(k).get("GROUPNAME").equals(ti.getText())){
												String sql3="update  qfriend set groupname='我的好友' where qid1=? and groupname =?";
												String sql4="delete  qfriend where qid1=? and groupname =?";
												List<Object> params3=new ArrayList<Object>();
												params3.add(QQDL.qid);
												params3.add(ti.getText());
												help.doUpdate(sql3, params3);
												
												help.doUpdate(sql4, params3);
											}
										}
										ti.dispose();
										
								}else{// 没孩子   可能是一个好友 可能是一个分组
									
									
									if(ti.getText().equals("我的好友")){
										mu.alert(shell, "提示信息", "我的好友 這個分組不能刪除");
										return ;
									}
									if(ti.getData("qid")==null||"".equals(ti.getData("qid"))){//分组
										String sql2="delete from qfriend where qid1=? and groupname =?";
										List<Object> params2=new ArrayList<Object>();
										params2.add(QQDL.qid);
										params2.add(ti.getText());
										help.doUpdate(sql2, params2);
									}else{// 为人
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
						// 重名一个分组
						itemUpd.addListener(SWT.Selection,new Listener(){
							public void handleEvent(Event agr0){
								sql="select * from qfriend where qid1='"+QQDL.qid+"'";
								
								List<Map<String,String>> list=help.findAll(sql, null);
								//用户点击到的这一个列名或者 人们
								TreeItem ti=tree_2.getSelection()[0];
								
								TreeItem[] tichild=ti.getItems();//得到分组的孩子     没有的话 得到自己家本身
								// 如果点击的是 分组 先去判断它是否有孩子  
								if(ti.getText().equals("我的好友")){
									mu.alert(shell, "提示信息", "我的好友 這個分組不能更名");
									return ;
								}
								if(ti.getData("qid")==null||"".equals(ti.getData("qid"))){
									
								}else{
									mu.alert(shell, "提示","不能此操作");
									return ;
								}
								TiShi th=new TiShi();
								th.open();
								if(TiShi.atext==null ||"".equals(TiShi.atext)){
									return ;
								}
								for(int i=0;i<list.size();i++){
									if(TiShi.atext.equals(list.get(i).get("GROUPNAME"))){
										mu.alert(shell, "提示", "分组已经存在");
										return ;
									}
								}
								
								
								if(TiShi.atext.equals("我的好友")){
									mu.alert(shell, "提示","不能创建此分组");
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
						
						//鼠标按下事件
						tree_2.addMouseListener(new MouseAdapter(){
							public void mouseDown(MouseEvent e){
								
								if(e.button==3){
									//显示菜单
									tree_2.setMenu(menu);
								}
							}
						});
						tree_2.addSelectionListener(new SelectionAdapter() {
							@Override
							//点网名  生成一个回话窗口
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
			//好友动态
			public void widgetSelected(SelectionEvent e) {
				
				//搜索  所有好友的qid
				sql = "select * from shuoshuo where qid in (select qid2 from qfriend where qid1 = '"+QQDL.qid+"') "
						+ "or qid = '"+QQDL.qid+"' order by qtime desc";
				List<Shuoshuo> listshuo2 = db.findAll(sql, null, Shuoshuo.class);
				if (listshuo2.size() > 0) {
					composite_4 = new Composite(scrolledComposite, SWT.NONE);
					scrolledComposite.setContent(composite_4);							
					// 堆栈布局显示
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
			//消息界面
			public void widgetSelected(SelectionEvent e) {
				
			//	flist.setVisible(false);
				composite_5.setVisible(false);
				scrolledComposite.setVisible(false);
				
				tree.removeAll();
				//ntai.setVisible(false);
			
				//点击消息  自动去数据库搜索  好友表里面的申请信息   int flag  为1 代表未读    有就刷新 一个添加框
				//没有的话就不执行
				//将已经打开的回话表的 treeitem  的gettext的值  设置   flag   为1  代表已经存在了  为零就在显示出来
				// 搜索回话表  别人发给你这个id 的回话     建立一个 int flag  为1代表来没有接受  
				//在数据库里面的好友表去搜索        qname 为本值得  flag 为0   代表申请  还有同意   同意了就更新数据库  将flag=1
				//再   更新一个自己的数据库
				
				// 查看申请表的
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
							sql="select * from qq  where qid='"+list.get(i).get("QID1")+"'";//等到申请的网名
							List<QQ>list1=help.findAllQQ(sql, null, 0);
							ti.setText(list1.get(0).qname+"  好友申请");
							ti.setData("qid",list1.get(0).qid);
						}

					}
				}
				
				
				//查出会话表里面 有几个是自己没有读的
			    sql="select * from qcontent where qid2='"+QQDL.qid+"'";
				List<Map<String,String>>list2=help.findAll(sql, null);  //得到发话人的 qid
				
				//开始判重    一个人多次给你发话     你需要他的  qID 
				ArrayList<String> qid=new ArrayList<String>();// 保存 去重之后所有发话人的qid
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
						List<QQ>list3=help.findAllQQ(sql, params3, 0);//得到发话人的所有的消息
						
						// 如果你删除别人    是接受不了别人回话的   
						sql="select * from qfriend where qid1=? and qid2=?";
						List<Object >params4=new ArrayList<Object>();
						params4.add(QQDL.qid);
						params4.add(qid.get(i));
						List <Map<String,String>>list4=help.findAll(sql, params4);//得到 发消息人的备注和网名
						
						if(list4.size()>0){
							
							//在树上 只显示你的   备注 或者 qq网名
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
	     * 应用
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
			//添加好友界面
			public void mouseDown(MouseEvent e) {
				TianJia tj=new TianJia();
				tj.open();
			}
		});
		//点击搜索  显示带提示信息的好友  根据    网名或者 扣扣号 或者备注
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
				 Menu menu=new Menu(tree_1);   // 给tree创建添加菜单
					/*MenuItem itemAdd=new MenuItem(menu,SWT.PUSH);
					itemAdd.setText("");*/
				    MenuItem itemBeiZu=new MenuItem(menu,SWT.PUSH);
					itemBeiZu.setText("修改备注");
					MenuItem itemZiLiao=new MenuItem(menu,SWT.PUSH);
					itemZiLiao.setText("查看好友资料");
					MenuItem itemYiDong=new MenuItem(menu,SWT.PUSH);
					itemYiDong.setText("移动到");
					//移动
					
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
							//更新数据库
							sql="update qfriend set groupname=? where qid2=? and qid1=?";
							List<Object>params=new ArrayList<Object>();
							params.add(t);
							params.add(ti.getData("qid"));
							params.add(QQDL.qid);
							help.doUpdate(sql, params);
							/*for(int i=0;i<t.size();i++){
								//得到选择的那个列的名称
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
					//查看好友资料
					itemZiLiao.addListener(SWT.Selection,new Listener(){
						public void handleEvent(Event agr0){
							
							//用户点击到的这一个列名或者 人们
							TreeItem ti=tree_1.getSelection()[0];
							
								beizu=ti.getText();
								//friendQQ=(String)ti.getData("qid");
							//	System.out.println(friendQQ);
								FriendZiLiao fzl=new FriendZiLiao((String)ti.getData("qid"));
								fzl.open();
								ti.setText(beizu);
						}
					});
					
					//修改备注
					itemBeiZu.addListener(SWT.Selection,new Listener(){
						public void handleEvent(Event agr0){
							
			                sql="select * from qfriend where qid1='"+QQDL.qid+"'";
							List<Map<String,String>> list=help.findAll(sql, null);
							//用户点击到的这一个列名或者 人们
							TreeItem ti=tree_1.getSelection()[0];
							TreeItem[] tichild=ti.getItems();//得到分组的孩子
							// 如果点击的是 分组 先去判断它是否有孩子  
							if(tichild.length>0){
								mu.alert(shell, "提示信息", "不能此操作");
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
							//显示菜单
							tree_1.setMenu(menu);
						}
					}
				});
				composite_5.setVisible(true);
				
			}
		});
		//头像的鼠标点击事件
		// 打开
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
		// 点击发表说说
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				SendDynamic sd=new SendDynamic();
				sd.open();
				
			}
		});
		// 消息面板树的点击事件 
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
	    		
	    		if(list1.size()>0){// 点击的是 好友申请的消息
	    			//friendQQ=ti.getData("qid").toString();
	    			tysq=new TongYiShengQing(ti.getData("qid").toString());
	    			if(tysq.shell==null){
	    			tysq.open();
	    			getNewXiaoXi();
	    			}else if(tysq.shell.isDisposed()){
	    				tysq.open();
		    			getNewXiaoXi();
	    			}
	    		}else{  // 好友消息的面板
	    			
	    			if(chatmap.get(ti.getData("qid")).shell==null)
	    			chatmap.get(ti.getData("qid")).open();
	    			else if(chatmap.get(ti.getData("qid")).shell.isDisposed()){
	    				chatmap.get(ti.getData("qid")).open();
	    			}
	    		}
		 		
		 		
		 	}
		    });
		
		//点击消息
		/* tree.addSelectionListener(new SelectionAdapter() {
		    	@Override
		    	
		    	
		    	
		    	
		    	public void widgetSelected(SelectionEvent e) {
		    		TreeItem ti=(TreeItem)e.item;
		    		
		    		TreeItem t[]=ti.getItems();
		    		
		    		if(t.length>0){//有申请 添加好友
		    		}else if(ti.getData("qid")!=null ||"".equals(ti.getData("qid"))){//点击的 别人申请
		    			friendQQ=ti.getData("qid").toString();
		    			TongYiShengQing tysq=new TongYiShengQing();
		    			tysq.open();
		    			getNewXiaoXi();
		    			
		    		}else{//是收到的消息
		    			friendqname=(String )ti.getData("qid");
		    			HuiHua hh=new HuiHua();
		    			hh.open();
		    		}
		    	}
		    });*/
		 
		 
		 //点击联系人
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
	    			
	    			
	    			
	    			
	    			//联系人
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
		    			
		    			//联系人
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
			lblNewLabel_4.setText("这个人很懒，还没有设置签名");
		}
	}
	
	public void getNewXiaoXi(){
		
		    tree.removeAll();
			//ntai.setVisible(false);
			scrolledComposite.setVisible(false);
			//点击消息  自动去数据库搜索  好友表里面的申请信息   int flag  为1 代表未读    有就刷新 一个添加框
			//没有的话就不执行
			//将已经打开的回话表的 treeitem  的gettext的值  设置   flag   为1  代表已经存在了  为零就在显示出来
			// 搜索回话表  别人发给你这个id 的回话     建立一个 int flag  为1代表来没有接受  
			//在数据库里面的好友表去搜索        qname 为本值得  flag 为0   代表申请  还有同意   同意了就更新数据库  将flag=1
			//再   更新一个自己的数据库
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
						sql="select * from qq  where qid='"+list.get(i).get("QID1")+"'";//等到申请的网名
						List<QQ>list1=help.findAllQQ(sql, null, 0);
						ti.setText(list1.get(0).qname+"  好友申请");
						ti.setData("qid",list1.get(0).qid);
					}

				}
			}
			//查出回话表里面 有几个是自己没有读的
			//查出会话表里面 有几个是自己没有读的
			sql="select * from qcontent where qid2='"+QQDL.qid+"'";
			List<Map<String,String>>list2=help.findAll(sql, null);  //得到发话人的 qid
			
			//开始判重    一个人多次给你发话     你需要他的  qID 
			ArrayList<String> qid=new ArrayList<String>();// 保存 去重之后所有发话人的qid
			
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
					List<QQ>list3=help.findAllQQ(sql, params3, 0);//得到发话人的所有的消息
					
					
					// 如果你删除别人    是接受不了别人回话的   
					String sql4="select * from qfriend where qid1=? and qid2=?";
					List<Object >params4=new ArrayList<Object>();
					params4.add(QQDL.qid);
					params4.add(qid.get(i));
					List <Map<String,String>>list4=help.findAll(sql4, params4);//得到 发消息人的备注和网名
					
					if(list4.size()>0){
						
						//在树上 只显示你的   备注 或者 qq网名
						
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
		
		
			ArrayList<String>arr=new ArrayList<String>();//保存列名 
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
			
			ArrayList <TreeItem> t=new ArrayList<TreeItem>();//保存他们的分组根
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
	
		
			ArrayList<String>arr=new ArrayList<String>();//保存列名 
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
			
			ArrayList <TreeItem> t=new ArrayList<TreeItem>();//保存他们的分组根
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
