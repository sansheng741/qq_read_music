package com.yc.music;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.utils.MyUtils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class LoginManager {

	protected Shell shell;
	private MyUtils mu=new MyUtils();
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginManager window = new LoginManager();
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(SWTResourceManager.getImage(LoginManager.class, "/images/\u56FE\u4E66.jpg"));
		shell.setImage(SWTResourceManager.getImage(LoginManager.class, "/images/QQ\u97F3\u4E501.png"));
		shell.setSize(450, 300);
		shell.setText("\u7BA1\u7406\u5458\u767B\u5165");
		
		mu.centerShell(shell);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(149, 47, 207, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(76, 50, 38, 20);
		lblNewLabel.setText("\u8D26\u53F7\uFF1A");
		
		Label lblM = new Label(shell, SWT.NONE);
		lblM.setText("\u5BC6\u7801\uFF1A");
		lblM.setBounds(76, 111, 38, 20);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);		
		text_1.setBounds(149, 108, 207, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);		
		btnNewButton.setBounds(270, 180, 98, 30);
		btnNewButton.setText("\u786E\u5B9A");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);		
		btnNewButton_1.setBounds(87, 180, 98, 30);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		/**
		 * 取消
		 */
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		/**
		 * 确定
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String admin = text.getText();
				String pwd = text_1.getText();
				if("admin".equals(admin) && "password".equals(pwd)){
					shell.dispose();
					ManagerUi mui = new ManagerUi();
					mui.open();	
				}else{
					mu.alert(shell, "提示信息", "账号或密码错误");
				}
			}
		});
		/***************ck**********************/
		/**
		 * Enter 登入
		 */
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296){
					String admin = text.getText();
					String pwd = text_1.getText();
					if("admin".equals(admin) && "password".equals(pwd)){
						shell.dispose();
						ManagerUi mui = new ManagerUi();
						mui.open();	
					}else{
						mu.alert(shell, "提示信息", "账号或密码错误");
					}
				}
			}
		});
		/*************************************/
	}
}
