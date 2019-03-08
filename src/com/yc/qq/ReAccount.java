package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.dao.Helper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

public class ReAccount {

	protected Shell shlQq;
	private boolean flag=true;
	private String filePath;
	private Text text;
	private Text text_1;
	private Text text_2;
	private MyUtils mu=new MyUtils();
	private Helper helper=new Helper();
	private Text text_3;
	private Text text_4;
	private Helper help=new Helper();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReAccount window = new ReAccount();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean CheckQQ(String t){
		boolean b=t.matches("[1~9][\\d]{5,9}");
		return b;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlQq.open();
		shlQq.layout();
		while (!shlQq.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlQq = new Shell();
		
		shlQq.setSize(510, 512);
		shlQq.setImage(SWTResourceManager.getImage(ReAccount.class, "/images/qq.png"));
		shlQq.setBackgroundImage(SWTResourceManager.getImage(ReAccount.class, "/image/\u74F6\u5B50.PNG"));
		shlQq.setText("QQ\u6CE8\u518C");
		shlQq.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shlQq);
		
		Composite composite = new Composite(shlQq, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		lblNewLabel.setBounds(72, 10, 161, 40);
		lblNewLabel.setText("\u6B22\u8FCE\u6CE8\u518CQQ\r\n");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("\u6BCF\u4E00\u5929\uFF0C\u4E50\u5728\u6C9F\u901A\u3002");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_3.setBounds(72, 56, 154, 27);
		
		Label lblQq = new Label(composite, SWT.NONE);
		lblQq.setAlignment(SWT.RIGHT);
		lblQq.setBounds(48, 139, 75, 23);
		lblQq.setText("QQ\u5BC6\u7801\uFF1A");
		
		text = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text.setBounds(160, 139, 211, 30);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label.setAlignment(SWT.RIGHT);
		label.setBounds(48, 190, 75, 23);
		
		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_1.setBounds(160, 190, 211, 30);
		
		Label lblQq_1 = new Label(composite, SWT.NONE);
		lblQq_1.setText("QQ\u6635\u79F0\uFF1A");
		lblQq_1.setAlignment(SWT.RIGHT);
		lblQq_1.setBounds(48, 243, 75, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_2.setBounds(160, 240, 211, 30);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u7535\u5B50\u90AE\u7BB1\uFF1A");
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(48, 293, 75, 23);
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_3.setBounds(160, 290, 211, 30);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnNewButton_1.setBackgroundImage(SWTResourceManager.getImage(ReAccount.class, "/image/\u7D2B.PNG"));
		btnNewButton_1.setBounds(389, 292, 80, 27);
		btnNewButton_1.setText("\u83B7\u53D6\u9A8C\u8BC1\u7801");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("\u9A8C\u8BC1\u7801\uFF1A");
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(48, 340, 75, 23);
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_4.setBounds(160, 340, 211, 30);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("楷体", 24, SWT.NORMAL));
		
		btnNewButton.setBackgroundImage(SWTResourceManager.getImage(ReAccount.class, "/image/\u7D2B.PNG"));
		btnNewButton.setBounds(133, 397, 227, 40);
		btnNewButton.setText("\u7ACB\u5373\u6CE8\u518C");
		
				
				
				//注册的点击事件
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Random r=new Random();
						int qid=r.nextInt(100000)+10000;
						String sql="select * from qq where qid=?";
						List<Object> params=new ArrayList<Object>();
						params.add(qid);
						List<Map<String, String>> list=helper.findAll(sql, params);
						while(list.size()>0){
							qid=r.nextInt(100000)+10000;
							params.add(qid);
							list=helper.findAll(sql, params);
						}
						String pwd=text.getText();
						String rpwd=text_1.getText();
						String qname=text_2.getText();
						String email=text_3.getText();
						String code=text_4.getText();
						if("".equals(qname)||"".equals(pwd)||"".equals(rpwd)||"".equals(email)){
								mu.alert(shlQq, "提示信息", "信息没有填写完整，请验证后重新注册");
							}else if(!pwd.equals(rpwd)){
								mu.alert(shlQq, "提示信息", "两次密码输入不一致，请验证后重新注册");
							}else if(!code.equalsIgnoreCase(a.code)){
								mu.alert(shlQq, "提示信息", "验证码输入错误，请验证后重新注册");
							}else{
								String sql1="insert into qq(qid,pwd,qimage,qname,motto,email) values(?,?,?,?,'这个人很懒，还没有设置签名',?)";
								List<Object> params1=new ArrayList<Object>();
								//用byte[],存放图片, 默认头像
								byte[] bt = null;
								try {
									InputStream is = new FileInputStream(new File("src/images/qq (3).png"));
									bt = new byte[is.available()];
									is.read(bt);
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}								
								params1.add(qid);
								params1.add(pwd);
								params1.add(bt);
								params1.add(qname);					
								params1.add(email);	
								int result=helper.doUpdate(sql1, params1);
								if(result>0){							
									mu.alert(shlQq, "提示信息", "注册成功,你的QQ号为："+qid);
									String sql2="insert into qfriend values(seq_qfid.nextval,'"+qid+"','我的好友','','')";
									help.doUpdate(sql2, null);
									a.qid = qid+"";
									shlQq.dispose();
								}else{
									mu.alert(shlQq, "提示信息", "注册失败");
								}
								
								}
					}
				});
		
		
		//获取验证码
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mu.sendMail(text_3.getText());
					mu.alert(shlQq, "邮件信息", "邮件发送成功，请注意查收");
				} catch (Exception e1) {
					mu.alert(shlQq,  "邮件信息", "邮件发送失败，请检查网络");
				}
			}
		});
		
		
		
	}
}
