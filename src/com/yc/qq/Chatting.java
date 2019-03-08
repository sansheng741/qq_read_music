package com.yc.qq;

import java.awt.MouseInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.QQ;
import com.ck.dao.Helper;

import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;

public class Chatting {

	protected Shell shell;
	private boolean bool;
	private Boolean dargFlag = false;
	private int x, y; // 鼠标点击时的坐标
	private Menu menu;
	private Text text;
	private Text txtn;
	private String qid;
	private Helper help = new Helper();
	private Label lblNewLabel;
	private String qname;
	private String beizu;
	private Lock lock=new ReentrantLock();
	private Label lblNewLabel_1;
	
	private boolean flag = false;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public Chatting(String qid) {
		this.qid = qid;
	}
	public Chatting() {

	}

	public static void main(String[] args) {
		try {
			Chatting window = new Chatting();
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
		flag=false;
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
				
			}
		}
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		shell = new Shell(SWT.NONE);
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				flag = true;
			}
		});
		shell.setSize(831, 685);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		shell.setLocation(400, 190);
		
		

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(Chatting.class, "/images/qqlogin2.png"));

		lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FriendZiLiao fz;
				if(beizu==null ||"".equals(beizu)){
					 fz=new FriendZiLiao(qid,qname);
				}else{
					 fz=new FriendZiLiao(qid,beizu);
				}
				
				fz.open();
				shuaxin();
			}
		});
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 16, SWT.NORMAL));
		lblNewLabel.setBounds(338, 10, 122, 29);

		ToolBar toolBar = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(762, -5, 70, 26);

		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setImage(SWTResourceManager.getImage(Chatting.class, "/images/btn_mini_normal.png"));

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setImage(SWTResourceManager.getImage(Chatting.class, "/images/btn_close_normal.png"));

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		txtn = new Text(composite_3, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		
		txtn.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
			}
		});
		
		Menu menu=new Menu(txtn);   // 给tree创建添加菜单
		
		MenuItem itemXiaoXi=new MenuItem(menu,SWT.PUSH);
		itemXiaoXi.setText("清屏");
		
		MenuItem itemAllXiaoXi=new MenuItem(menu,SWT.PUSH);
		itemAllXiaoXi.setText("清空本地消息");
		
		/*MenuItem itemDelXiaoXi=new MenuItem(menu,SWT.PUSH);
		itemDelXiaoXi.setText("删除此消息");
		
		itemDelXiaoXi.addListener(SWT.Selection,new Listener(){
			public void handleEvent(Event agr0){
				txtn.clearSelection();
				//System.out.println(txtn.getSelectionText());
				//txtn.setText("");
			}
		});*/
		
		//清空页面消息
		itemXiaoXi.addListener(SWT.Selection,new Listener(){
			public void handleEvent(Event agr0){
				System.out.println(txtn.getSelectionText());
				txtn.setText("");
			}
		});
		
		/*itemAllXiaoXi.addListener(SWT.Selection,new Listener(){
			public void handleEvent(Event agr0){
				// 清空数据库  你发给好友的
				String sql="select * from qid1=? and qid2=?";
				List<Object>params=new ArrayList<Object>();
				params.add(QQDL.qid);
				params.add(qid);
				help.doUpdate(sql, params);
				params.clear();
				
				params.add(qid);
				params.add(QQDL.qid);
				
			}
		});*/
		txtn.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				
				if(e.button==3){
					//显示菜单
					txtn.setMenu(menu);
				}
			}
		});

		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		SashForm sashForm_2 = new SashForm(composite_4, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);
		//text = new Text(sashForm_2, SWT.WRAP | SWT.V_SCROLL);

		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_5.setLayout(null);
		
		text= new Text(composite_5, SWT.BORDER);
		
		text.setBounds(0, 27, 829, 114);
		
		lblNewLabel_1 = new Label(composite_5, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(540, 3, 248, 24);
		
		Label lblNewLabel_2 = new Label(composite_5, SWT.NONE);
		
		
		lblNewLabel_2.setImage(SWTResourceManager.getImage(Chatting.class, "/images/xiaoxi.png"));
		lblNewLabel_2.setBounds(794, -1, 34, 29);
		
		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
		
				Button btnNewButton = new Button(composite_6, SWT.NONE);
				
				btnNewButton.setBounds(662, 8, 80, 27);
				btnNewButton.setText("\u5173\u95ED(C)");
				
						Button btnNewButton_1 = new Button(composite_6, SWT.NONE);
						btnNewButton_1.setBounds(748, 8, 80, 27);
						btnNewButton_1.setText("\u53D1\u9001(S)");
						sashForm_2.setWeights(new int[] {116, 32});
						sashForm_1.setWeights(new int[] {445, 183});
						
						// 点击发送
						btnNewButton_1.addSelectionListener(new SelectionAdapter() {
							
							public void widgetSelected(SelectionEvent e) {
								
								if(text.getText()==null ||"".equals(text.getText())){
									lblNewLabel_1.setText("发送的信息不能为空   请重新输入");
									return ;
								}
								
								String sql = "insert into qcontent values(seq_qcid.nextval,?,?,?,sysdate,1)";
								List<Object> params = new ArrayList<Object>();
								params.add(QQDL.qid);
								params.add(qid);
								params.add(text.getText());
								help.doUpdate(sql, params);
								
							
								sql="select * from qq where qid='"+QQDL.qid+"'";
								List<QQ>tlist=help.findAllQQ(sql, null, 0);
								txtn.append(tlist.get(0).qname+" "+new Date()+"\n");
								txtn.append(text.getText()+"\n\n");
								
								text.setText("");
							
							}
						});
				// 点击关闭
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shell.dispose();
					}
				});
				sashForm.setWeights(new int[] { 49, 631 });
			 shuaxin();
			 oldXiaoXin(0);
			 
			 //text 的焦距得到   是提示信息消失
			 text.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						lblNewLabel_1.setText("");
						
					}
				});
			 
		     new Thread(new Runnable(){
			   public synchronized void run(){				   
				   while(!flag){
						  
						   Display.getDefault().asyncExec(new Runnable(){
							   public void run(){
								   getXiao();
							   }
						   }); 
						   
						   try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
						   
				   }
			   }
		   }).start();

		// 面板拖拽
		composite_1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if (dargFlag) {
					// MouseInfo.getPointerInfo().getLocation().x 距桌面左侧距离
					// x 距面板左侧距离
					// 相减后得到面板距桌面左侧距离
					shell.setLocation(MouseInfo.getPointerInfo().getLocation().x - x,
							MouseInfo.getPointerInfo().getLocation().y - y);
					// 与鼠标移动先坐标相减，的到移动距离
				}
			}
		});
		//历史消息的查询
		
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				OldXiaoXi oxx=new OldXiaoXi(qid,qname,beizu);
				oxx.open();
				
			}
		});
		
		composite_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// 鼠标按下，可以移动
				dargFlag = true;
				x = e.x;
				y = e.y;
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// 鼠标松开，不能移动
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
				shell.dispose();
			}
		});
		
		//键盘 按enter 发送
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.stateMask==SWT.ALT && e.keyCode==(int)'c'){
					if(text.getText()==null||"".equals(text.getText())){
						shell.dispose();
					}
				}

				if(e.keyCode==SWT.CR  ||(e.keyCode==SWT.CR && e.stateMask==SWT.CTRL)){
					if(text.getText()==null ||"".equals(text.getText())){
						return ;
					}
					
					String sql = "insert into qcontent values(seq_qcid.nextval,?,?,?,sysdate,1)";
					List<Object> params = new ArrayList<Object>();
					params.add(QQDL.qid);
					params.add(qid);
					params.add(text.getText());
					help.doUpdate(sql, params);
	
					sql="select * from qq where qid='"+QQDL.qid+"'";
					List<QQ>tlist=help.findAllQQ(sql, null, 0);
					//System.out.println(tlist.get(0).qname);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String datet=sdf.format(new Date());
					txtn.append(tlist.get(0).qname+"  "+datet+"\n");
					txtn.append(text.getText()+"\n\n");
					text.setText("");
					
					if(text.getText()==null ||"".equals( text.getText())){
						
					}else{
						text.setText("");
					}
				}
			}
		});
	}

	public void shuaxin() {

		List<Object> params = new ArrayList<Object>();
		// 查找 消息里面树 的 qid 的 去friend 好友信息
		String sqlt = "select * from qfriend where qid1=? and qid2=?";//
		params.add(QQDL.qid);
		params.add(qid);
		List<Map<String, String>> listt = help.findAll(sqlt, params);
		params.clear();
		// 查找 消息树 里面的qid 的所有 qq 信息

		if (listt.get(0).get("BEIZU") == null || "".equals(listt.get(0).get("BEIZU"))) {
			String sql = "select * from qq where qid='" + qid + "'";
			List<QQ> list = help.findAllQQ(sql, null, 0);
			lblNewLabel.setText(list.get(0).qname);
			qname = list.get(0).qname;
			beizu="";
		} else {
			lblNewLabel.setText(listt.get(0).get("BEIZU"));
			beizu = listt.get(0).get("BEIZU");
			qname="";
		}
	}
	//去刷新 数据库 别人发给你 还没有读的信息
	public void getXiao() {
		List<Object> params = new ArrayList<Object>();
		
		//查询数据库 别人发话给你的内容
		
		String sql = "select * from qcontent where qid1 =? and qid2=? and flag=1 order by qcid";
		params.add(qid);
		params.add(QQDL.qid);

		List<Map<String, String>> list = help.findAll(sql, params);

		for (int i = 0; i < list.size(); i++) {
			
				if (beizu == null || "".equals(beizu)) {

					txtn.append( qname + "  " + list.get(i).get("QDATE")+"\n");
				} else {

					txtn.append( beizu + "  " + list.get(i).get("QDATE")+"\n");
				}
			txtn.append( list.get(i).get("TEXT")+"\n\n");
		}
		
		sql="update qcontent set flag=0 where  qid1 =? and qid2=?";
		help.doUpdate(sql, params);
	}
	
	
	
	
	public void oldXiaoXin(int start){
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from qcontent where qid1 =? and qid2=? and flag=0 or  qid1 =? and qid2=?  order by qcid";
		params.add(qid);
		params.add(QQDL.qid);

		params.add(QQDL.qid);
		params.add(qid);

		List<Map<String, String>> list = help.findAll(sql, params);
		
		
		if(list.size()>6){
			start=list.size()-6;
		}else{
			start=0;
		}

		for (; start < list.size(); start++) {
		
			
			if(list.get(start).get("QID1").equals(QQDL.qid)){// 代表你发给好友的
				String at="select q.qname from qq q where qid='"+QQDL.qid+"'";
				List<Map<String,String>>listqq=help.findAll(at, null);
				
				txtn.append(listqq.get(0).get("QNAME")+ " " + list.get(start).get("QDATE")+"\n");
				
			}else{
				if (beizu == null || "".equals(beizu)) {

					txtn.append( qname + " " + list.get(start).get("QDATE")+"\n");
				} else {

					txtn.append( beizu + " " + list.get(start).get("QDATE")+"\n");
				}
			}
			txtn.append( list.get(start).get("TEXT")+"\n\n");
		}
		
		/*txtn.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				if(arg0.keyCode==8 &&arg0.stateMask==0){
					
				}
				
				
			}
		});*/
		
	}
}
