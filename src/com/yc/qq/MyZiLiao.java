package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ck.bean.QQ;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.wb.swt.SWTResourceManager;

public class MyZiLiao {

	protected Shell shell;
	private int flag;
	private Helper help=new Helper();
	private MyUtils mu = new MyUtils();
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo;
	private Combo combo_1;
	private Combo combo_2;
	private Combo combo_3;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MyZiLiao window = new MyZiLiao();
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
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(MyZiLiao.class, "/images/qq.png"));
		shell.setSize(797, 549);
		shell.setText("\u6211\u7684\u8D44\u6599");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		String sql="select * from qq where qid='"+QQDL.qid+"'";
		List<QQ>list=help.findAllQQ(sql, null, 0);
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Label lblNewLabel = new Label(sashForm_1, SWT.NONE);		
		lblNewLabel.setImage(SWTResourceManager.getImage(MyZiLiao.class, "/images/qqlogin2.png"));
		
		int height =lblNewLabel.getBounds().height;
		int width=lblNewLabel.getBounds().width;

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		
		Label lblNewLabel_5 = new Label(composite_2, SWT.BORDER);
		lblNewLabel_5.setBounds(16, 34, 100, 100);
		
		
		
		text = new Text(composite_2, SWT.NONE);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		text.setBounds(145, 44, 122, 27);
		text.setEnabled(false);
		text.setText(list.get(0).qname);
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_1.setBounds(145, 89, 213, 23);
		text_1.setEnabled(false);
		text_1.setTouchEnabled(true);
		if(!"".equals(list.get(0).motto) && list.get(0).motto != null){
			text_1.setText(list.get(0).motto);
		}else{
			text_1.setText("这个人很懒，还没有设置签名");
		}
		InputStream is= new ByteArrayInputStream(list.get(0).qimage);
		ImageData id= new ImageData(is);
		id=id.scaledTo(lblNewLabel_5.getBounds().width, lblNewLabel_5.getBounds().height);
		Image image=new Image(null,id);	
		lblNewLabel_5.setImage(image);
		sashForm_1.setWeights(new int[] {307, 200});
		
		//点击头像 可以更换头像
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				
				//打开一个文件选择框,可以选择文件
				String filepath="";
				FileDialog fd=new FileDialog(shell);
				// 控制你选择的后缀名为  :...
				fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
				fd.open();
				if(fd!=null && !"".equals(fd.getFilterPath())){
					//文件已经有了
					 filepath=fd.getFilterPath()+"\\"+fd.getFileName();
					 Image image=null;
					  try {
						File f=new File(filepath);
						//得到io 流
						
						InputStream is=new FileInputStream(f);
						ImageData id=new ImageData(is);
						int width,height;
						width=lblNewLabel_5.getBounds().width;
						height=lblNewLabel_5.getBounds().height;
						//缩放
						id=id.scaledTo(width, height);
						//通过ImageData 创建一个Image
						image=new Image(null,id);
						is.close();
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}	
					  
					lblNewLabel_5.setImage(image);
					
					
					String sql="update qq set qimage =? where qid='"+QQDL.qid+"' ";
					List<Object> params=new ArrayList<Object>();
					
					
					byte []bt=null;
					
						try {
							InputStream is=new FileInputStream(new File(filepath));
							bt=new byte[is.available()];
							is.read(bt);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
						params.add(bt);
					    help.doUpdate(sql, params);
				}
			}
		});
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(26, 104, 36, 17);
		lblNewLabel_1.setText("\u6027\u522B\uFF1A");
		
		
		
		
		
		
		combo = new Combo(composite, SWT.NONE);
		combo.setEnabled(false);
		combo.setTouchEnabled(true);
		combo.setBounds(86, 101, 88, 25);
		combo.add("男");
		combo.add("女");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(26, 155, 42, 17);
		lblNewLabel_2.setText("\u51FA\u751F:");
		if(list.get(0).sex==null ||"".equals(list.get(0).sex)){
			combo.setText("");
		}
		
		else if(list.get(0).sex.equals("男")){
			combo.select(0);
		}else{
			combo.select(1);
		}
		
		Date date=new Date();

		combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setEnabled(false);
		combo_1.setTouchEnabled(true);
		combo_1.setBounds(86, 152, 88, 25);
		
		
		
		
		
		for(int i=2018;i>=1900;i--){
			combo_1.add(""+i);
		}
		combo_2 = new Combo(composite, SWT.NONE);
		combo_2.setEnabled(false);
		combo_2.setTouchEnabled(true);
		combo_2.setBounds(180, 152, 88, 25);
		
		for(int i=1;i<=12;i++){
			combo_2.add(""+i);
		}
		combo_3 = new Combo(composite, SWT.NONE);
		
		combo_3.setEnabled(false);
		combo_3.setTouchEnabled(true);
		combo_3.setBounds(274, 152, 88, 25);
		if(list.get(0).birthday==null ||"".equals(list.get(0).birthday)){
			combo_1.setText("");
			combo_2.setText("");
			combo_3.setText("");
		}else{
			String birthday[]= list.get(0).birthday.split("-");
			combo_1.setText(birthday[0]);
			combo_2.setText(birthday[1]);
			combo_3.setText(birthday[2]);
		}

		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(26, 203, 42, 17);
		lblNewLabel_3.setText("\u5E74\u9F84:");
		
		
		text_2 = new Text(composite, SWT.NONE);
		text_2.setEnabled(false);
		text_2.setText(""+list.get(0).age);
		text_2.setBounds(87, 203, 73, 23);
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBounds(26, 250, 42, 17);
		lblNewLabel_4.setText("\u5730\u5740:");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setEnabled(false);
		text_3.setTouchEnabled(true);
		text_3.setBounds(88, 247, 180, 23);
		
		if(list.get(0).addr==null || "".equals(list.get(0).addr)){
			text_3.setText("");
		}else{
			text_3.setText(list.get(0).addr);
		}
		
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setBounds(259, 54, 80, 27);
		btnNewButton.setText("\u7F16\u8F91\u8D44\u6599");
		
		Label lblQq = new Label(composite, SWT.NONE);
		lblQq.setText("QQ\uFF1A");
		lblQq.setBounds(26, 39, 36, 17);
		
		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setBounds(82, 39, 105, 17);
		sashForm.setWeights(new int[] {385, 393});
		lblNewLabel_6.setText(list.get(0).qid);
		
		combo_3.addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				combo_3.removeAll();
				Calendar cd=Calendar.getInstance();
				cd.set(Calendar.YEAR,Integer.parseInt(combo_1.getText()));
				
				cd.set(Calendar.MONTH,Integer.parseInt(combo_2.getText())-1);	
				int number =cd.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				for ( int i=1;i<=number;i++){
					combo_3.add(""+i);
				}
			}
		});
		
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flag++;
				if(flag%2==1){
					btnNewButton.setText("保存");
					combo.setEnabled(true);
					combo_1.setEnabled(true);
					combo_2.setEnabled(true);
					text.setEnabled(true);
					
					text_1.setEnabled(true);
					text_3.setEnabled(true);
					combo_3.setEnabled(true);

				}else{
					
					btnNewButton.setText("编辑资料");
					/**
						 *   qid varchar2(32) primary key,-- qq号
						       pwd varchar2(32) not null,--密码
						       qimage blob ,-- 头像
						       qname varchar2(50) not null ,-- 网名
						       motto varchar2(86),-- 签名
						       age int ,--年龄
						       sex varchar2(4) constraint CK_sex check( sex in('男','女')),-- 性别
						       birthday varchar2(20),--生日
						       adrr varchar2(64)-- 地址
					 */
					String qname =text.getText();
					String motto=text_1.getText();
					
					String sex=combo.getText();
					String birthday=combo_1.getText()+"-"+combo_2.getText()+"-"+combo_3.getText();
					
					Calendar cd=Calendar.getInstance();
					Calendar cd1=Calendar.getInstance();
					
					int year=cd.get(Calendar.YEAR);
					int tyear=Integer.parseInt(combo_1.getText());
				
					int month=cd.get(Calendar.MONTH - 1);
					int tmonth=Integer.parseInt(combo_2.getText());
					
					int day=cd.get(Calendar.DAY_OF_MONTH);
					int tday=Integer.parseInt(combo_3.getText());
					
					int t=year-tyear;
					int age=0;
				
					if((month-tmonth)>0){
						age=t;
					}else if(month==tmonth){
						if(day>=tday){
							age=t;
						}else{
							age=t-1;
						}
					}else{
						age=t-1;
					}
					
					String adrr=text_3.getText();
					
					List<Object>params=new ArrayList<Object>();
					
					String sql="update qq set adrr=? ,age=? ,sex=? ,birthday=? ,qname=? ,motto=? where qid=?";
					params.add(adrr);
					params.add(age);
					params.add(sex);
					params.add(birthday);
					params.add(qname);
					params.add(motto);
					params.add(QQDL.qid);
					help.doUpdate(sql, params);
					
					
					text.setText(qname);
					text_1.setText(motto);
					combo.setText(sex);
					combo_1.setText(""+tyear);
					combo_2.setText(""+tmonth);
					combo_3.setText(""+tday);
					text_3.setText(adrr);
					text_2.setText(""+age);
					
					
					
					combo.setEnabled(false);
					combo_1.setEnabled(false);
					combo_2.setEnabled(false);
					text.setEnabled(false);
					
					text_1.setEnabled(false);
					text_3.setEnabled(false);
					combo_3.setEnabled(false);
				}
			}
		});

	}
}
