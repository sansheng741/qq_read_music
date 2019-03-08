package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;


import com.ck.bean.QQ;
import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;
import com.ck.utils.RegisterUtil;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Composite;

import java.awt.MouseInfo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class QQDL{
	static String qid;
	static String pwd;
	static String qname;
	protected Shell shell;
	private Text text_1;
	private String filePath;
	private Helper help=new Helper();
	private DBHelper db = new DBHelper();
	private Boolean dargFlag = false;
	private int x,y; //鼠标点击时的坐标
	private Composite composite_2;
	private MyUtils mu = new MyUtils();
	private Combo combo;
	private Button btnCheckButton_1;
	private List<Map<String, String>> AccountList;
	private Map<String,String> map;
	private RegisterUtil ru = new RegisterUtil();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QQDL window = new QQDL();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	//  开出来的 新线程
	
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
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(QQDL.class, "/images/qq.png"));
		shell.setSize(541, 418);
		shell.setText("QQ");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		  
		composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_FORCE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		Image image = null;
		try {
			InputStream is = new FileInputStream(new File("src/images/qqlogin2.png"));
			ImageData id = new ImageData(is);
			id = id.scaledTo(541,214);
			image = new Image(shell.getDisplay(),id);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		composite_2.setBackgroundImage(image);
		
		ToolBar toolBar = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(471, -4, 70, 26);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setImage(SWTResourceManager.getImage(QQDL.class, "/images/btn_mini_normal.png"));
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setImage(SWTResourceManager.getImage(QQDL.class, "/images/btn_close_normal.png"));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage(QQDL.class, "/images/qq (3).png"));
		lblNewLabel.setBounds(147, 55, 70, 89);
		
		Label lblNewLabel_5 = new Label(composite_2, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("楷体", 38, SWT.NORMAL));
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(223, 81, 202, 63);
		lblNewLabel_5.setText("QQ2018");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		combo = new Combo(composite_1, SWT.NONE);	
		
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		combo.setBounds(163, 13, 268, 28);
		
		text_1 = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		
		text_1.setBounds(163, 57, 268, 28);
		
		Button btnCheckButton = new Button(composite_1, SWT.CHECK);
		btnCheckButton.setBounds(162, 107, 69, 20);
		btnCheckButton.setText("\u81EA\u52A8\u767B\u5F55");
		
		btnCheckButton_1 = new Button(composite_1, SWT.CHECK);		
		btnCheckButton_1.setAlignment(SWT.CENTER);
		btnCheckButton_1.setBounds(271, 107, 69, 20);
		btnCheckButton_1.setText("\u8BB0\u4F4F\u5BC6\u7801");
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);		
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBounds(373, 109, 58, 20);
		lblNewLabel_1.setText("\u627E\u56DE\u5BC6\u7801");
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);		
		lblNewLabel_2.setBounds(67, 11, 88, 76);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton.setBounds(163, 140, 268, 39);
		btnNewButton.setText("\u767B\u5F55");
		
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBounds(10, 180, 76, 20);
		lblNewLabel_3.setText("\u6CE8\u518C\u8D26\u53F7");
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_4.setBounds(437, 12, 96, 25);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(438, 57, 96, 25);
		
		ToolBar toolBar_2 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setBounds(515, 180, 23, 22);
		
		ToolItem toolItem_2 = new ToolItem(toolBar_2, SWT.NONE);		
		toolItem_2.setToolTipText("\u5173\u4E8EQQ2018");
		toolItem_2.setImage(SWTResourceManager.getImage(QQDL.class, "/images/\u5173\u4E8E\u6211\u4EEC.png"));
		sashForm.setWeights(new int[] {211, 202});
		
		//账号框的 点击事件
		
		//面板拖拽
		composite_2.addMouseMoveListener(new MouseMoveListener() {
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
		
		
		composite_2.addMouseListener(new MouseAdapter() {
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
			System.exit(0);
			}
		});
		/**
		 * 关于QQ2018
		 */
		toolItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AboutUs au = new AboutUs();
				au.open();
			}
		});
		/**
		 * 账号选择
		 */
		String	sqls = "select qid from qq";
		AccountList = db.findAll(sqls, null);
		String[] s = new String[AccountList.size()]; 
		if(AccountList.size() > 0){
			for(int i = 0; i < AccountList.size(); i++){
				s[i] = AccountList.get(i).get("QID");	
			}
			combo.setItems(s);
			combo.select(0);
		}
		
		remeberPwd();
		
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sqlt="select * from  qq where qid = '"+combo.getText()+"'";
				
				List<QQ>listt =help.findAllQQ(sqlt, null, 1);
				
				//里面有账号就显示头像
				if(listt.size()>0){
					int width,height;
					width=lblNewLabel_2.getBounds().width;
					height=lblNewLabel_2.getBounds().height;
					Image imaget = null;
					InputStream ist=new ByteArrayInputStream(listt.get(0).qimage);
					ImageData idt=new ImageData(ist);
					idt=idt.scaledTo(width, height);
					imaget = new Image(shell.getDisplay(),idt);
					lblNewLabel_2.setImage(imaget);	
					
					
					String uname = combo.getText().trim();
					//查看注册表中是否存在
					if(map != null && map.size() > 0){
						if(map.containsKey(uname)){
							//如果存在则将用户的密码直接显示在密码框，且必须将记住密码勾上
							text_1.setText(map.get(uname));
							btnCheckButton_1.setSelection(true); //自动选中记住密码
						}else{
							text_1.setText("");
							btnCheckButton_1.setSelection(false);
						}
					}else{
						text_1.setText("");
						btnCheckButton_1.setSelection(false);
					}
				}
			}
		});
		/*//当用户名输入框失去焦点时
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//获取当前用户名
				String uname = combo.getText().trim();
				//查看注册表中是否存在
				if(map != null && map.size() > 0){
					if(map.containsKey(uname)){
						//如果存在则将用户的密码直接显示在密码框，且必须将记住密码勾上
						text_1.setText(map.get(uname));
						btnCheckButton_1.setSelection(true); //自动选中记住密码
					}else{
						text_1.setText("");
						btnCheckButton_1.setSelection(false);
					}
				}else{
					text_1.setText("");
					btnCheckButton_1.setSelection(false);
				}
			}
		});
		*/
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			//注册账号
			public void mouseDown(MouseEvent e) {
				ReAccount ra=new ReAccount();
				ra.open();
				if(a.qid != null && !"".equals(qid)){		
					combo.setText(a.qid);
					text_1.setText("");
				}
			}
		});
		
		/**
		 * 键盘按下时 - 登陆
		 */
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296){
					qid=combo.getText();
					pwd=text_1.getText();
					 
					if(combo.getText()==null ||combo.getText().equals("")){
						lblNewLabel_4.setText("账号不能为空");
						
					}
					if(text_1.getText()==null ||"".equals(text_1.getText())){
						label.setText("账号不能为空");
					}
					 
					String sql="select * from qq where qid=? and pwd=?";
					List <Object> params=new ArrayList<Object>();
					params.add(qid);
					params.add(pwd);
					List<QQ> list =help.findAllQQ(sql, params, 1);
					if(list.size()>0){
						String sqlt="select * from qq where qid='"+qid+"' and flag=1 ";
						List<QQ> listt =help.findAllQQ(sqlt,null , 1);
						
						if(listt.size()==0){							
							try {
								Thread.currentThread().sleep(500);
								btnNewButton .setText("  登录中...");
								Thread.currentThread().sleep(500);
								btnNewButton .setText("  登录失败...");
								Thread.currentThread().sleep(500);
								btnNewButton .setText("  登录...");
								label.setText("此账号已经登录了");
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}else{
							qname=list.get(0).qname;
							//判断是否要记住密码
							if(btnCheckButton_1.getSelection()){
								//如果需要，则写入注册表
								Map<String,String> map1 = new HashMap<String,String>();
								map1.put(qid, pwd);
								ru.add(map1);
							}else{
								ru.remove(qid);
							}
							Main m=null;
							try{
								m=new Main();
								try {
									Thread.currentThread().sleep(500);
									btnNewButton .setText("  登录中...");
									Thread.currentThread().sleep(500);
									btnNewButton .setText("  登录成功...");
									// 最后去弄
									String sqll="update qq set flag=0 where qid='"+qid+"'";
									help.doUpdate(sqll, null);
									Thread.currentThread().sleep(500);
								} catch (InterruptedException e1) {
									
									e1.printStackTrace();
								}
								shell.dispose();
								m.open();
								
							}finally{
								sql="update qq set flag=1 where qid='"+QQDL.qid+"'";
								help.doUpdate(sql, null);
							}
							
							
							
						}
					}
					else{
						try {
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录中...");
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录失败...");
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录...");
							label.setText("密码或者账号错误");
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}				
			}
		});
		
		if(combo.getText()==null ||"".equals( combo.getText())){
			
		}else{
			String sqlt="select * from  qq where qid = '"+combo.getText()+"'";
			
			List<QQ>listt =help.findAllQQ(sqlt, null, 1);
			
			//里面有账号就显示头像
			if(listt.size()>0){
				int width,height;
				width=lblNewLabel_2.getBounds().width;
				height=lblNewLabel_2.getBounds().height;
				Image imaget = null;
				InputStream ist=new ByteArrayInputStream(listt.get(0).qimage);
				ImageData idt=new ImageData(ist);
				idt=idt.scaledTo(width, height);
				imaget = new Image(shell.getDisplay(),idt);
				lblNewLabel_2.setImage(imaget);											
			}
		}
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			//登录  
			public void widgetSelected(SelectionEvent e) {
				

				qid=combo.getText();
				pwd=text_1.getText();
				 
				if(combo.getText()==null ||combo.getText().equals("")){
					lblNewLabel_4.setText("账号不能为空");
					
				}
				if(text_1.getText()==null ||"".equals(text_1.getText())){
					label.setText("账号不能为空");
					
				}
				if(combo.getText()==null ||combo.getText().equals("")){
			           return ;
					
				}
				if(text_1.getText()==null ||"".equals(text_1.getText())){
					return ;
					
				}
				 
				String sql="select * from qq where qid=? and pwd=?";
				List <Object> params=new ArrayList<Object>();
				params.add(qid);
				params.add(pwd);
				List<QQ> list =help.findAllQQ(sql, params, 1);
				if(list.size()>0){
					String sqlt="select * from qq where qid='"+qid+"' and flag=1 ";
					List<QQ> listt =help.findAllQQ(sqlt,null , 1);
					
					if(listt.size()==0){							
						try {  
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录中...");
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录失败...");
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录...");
							label.setText("此账号已经登录了");
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}else{
						qname=list.get(0).qname;
						//判断是否要记住密码
						if(btnCheckButton_1.getSelection()){
							//如果需要，则写入注册表
							Map<String,String> map1 = new HashMap<String,String>();
							map1.put(qid, pwd);
							ru.add(map1);
						}else{
							ru.remove(qid);
						}
						Main m=new Main();
						try {
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录中...");
							Thread.currentThread().sleep(500);
							btnNewButton .setText("  登录成功...");
							String sqll="update qq set flag=0 where qid='"+qid+"'";
							help.doUpdate(sqll, null);
							Thread.currentThread().sleep(500);
						} catch (InterruptedException e1) {
							
							e1.printStackTrace();
						}
						shell.dispose();
						m.open();
					}
				}
				else{
					try {
						Thread.currentThread().sleep(500);
						btnNewButton .setText("  登录中...");
						Thread.currentThread().sleep(500);
						btnNewButton .setText("  登录失败...");
						Thread.currentThread().sleep(500);
						btnNewButton .setText("  登录...");
						label.setText("密码或者账号错误");
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		combo.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				if(combo.getText()==null ||combo.getText().equals("")){
					lblNewLabel_4.setText("账号不能为空");
					return ;
				}
				//if()
				
			
				qid=combo.getText();
				String sql="select * from  qq where qid = '"+qid+"'";
				
				List<QQ>list =help.findAllQQ(sql, null, 1);
				
				//里面有账号就显示头像
				if(list.size()>0){
					int width,height;
					width=lblNewLabel_2.getBounds().width;
					height=lblNewLabel_2.getBounds().height;
					Image image = null;
					InputStream is=new ByteArrayInputStream(list.get(0).qimage);
					ImageData id=new ImageData(is);
					id=id.scaledTo(width, height);
					image = new Image(shell.getDisplay(),id);
					lblNewLabel_2.setImage(image);											
				}else{
					lblNewLabel_2.setImage(null);
					lblNewLabel_4.setText("账号不存在");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_4.setText("");
				label.setText("");
			
			}
		});
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(text_1.getText()==null ||"".equals(text_1.getText())){
					label.setText("账号不能为空");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label.setText("");
			}
		});
		/**
		 * 找回密码
		 */
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				a.qid = combo.getText();
				Retrieve re = new Retrieve();
				re.open();
				remeberPwd();
				if(a.qid != null && !"".equals(qid)){		
					combo.setText(a.qid);
					text_1.setText("");
				}				
			}
		});		
	}

	private void remeberPwd() {
		/**
		 * 记住密码
		 */
		ru = new RegisterUtil();
		//程序一开始就，查看注册表有没有记住过的密码
		boolean flag = true; //标记是否忘combo里面添加值
		int index = 0;
		map = ru.getAll();		
		if(map != null && map.size() > 0){ //说明有记录
			Set<String> keys = map.keySet();
			for(String key : keys){
				//将用户添加到下拉列表中
				for(int i = 0; i < AccountList.size(); i++){
					if( key.equals(combo.getItem(i)) ){
						flag = false;
						index = i;
						break;
					}else{
						flag = true;
					}				
				}
				if(flag){
					combo.add(key);
				}
			}
			
			btnCheckButton_1.setSelection(true); //自动选中记住密码
			//密码框显示第一个账号和密码
			if(flag){
				text_1.setText(map.get(combo.getItem(AccountList.size())));
				combo.select(AccountList.size()); //默认选择第一个
			}else{
				text_1.setText(map.get(combo.getItem(index)));
				combo.select(index); //默认选择第一个
			}
		}	
	}
}
