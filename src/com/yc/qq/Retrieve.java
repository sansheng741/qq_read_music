package com.yc.qq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Retrieve {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private MyUtils mu=new MyUtils();
	private Helper helper=new Helper();
	private DBHelper db = new DBHelper();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Retrieve window = new Retrieve();
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
		shell.setBackgroundImage(SWTResourceManager.getImage(Retrieve.class, "/image/\u8F69.PNG"));
		shell.setImage(SWTResourceManager.getImage(Retrieve.class, "/images/qq.png"));
		shell.setSize(527, 392);
		shell.setText("\u627E\u56DE\u5BC6\u7801");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(null);
		
		Label lblQq = new Label(composite, SWT.NONE);
		lblQq.setAlignment(SWT.RIGHT);
		lblQq.setBounds(71, 36, 80, 23);
		lblQq.setText("QQ\u53F7\u7801\uFF1A");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(185, 36, 205, 23);
		text.setText(a.qid);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("\u7535\u5B50\u90AE\u7BB1\uFF1A");
		label.setAlignment(SWT.RIGHT);
		label.setBounds(71, 82, 80, 23);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(185, 82, 205, 23);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u9A8C\u8BC1\u7801\uFF1A");
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(71, 130, 80, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(185, 130, 205, 23);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("\u91CD\u7F6E\u5BC6\u7801\uFF1A");
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(71, 180, 80, 23);
		
		text_3 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_3.setBounds(185, 180, 205, 23);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_3.setAlignment(SWT.RIGHT);
		label_3.setBounds(71, 230, 80, 23);
		
		text_4 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_4.setBounds(185, 230, 205, 23);
		
		Button button = new Button(composite, SWT.NONE);
		
		button.setBounds(213, 288, 80, 27);
		button.setText("\u786E\u8BA4");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setBounds(408, 82, 80, 27);
		btnNewButton.setText("\u83B7\u53D6\u9A8C\u8BC1\u7801");
		
		//验证码
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mu.sendMail(text_1.getText());
					mu.alert(shell, "邮件信息", "邮件发送成功，请注意查收");
				} catch (Exception e1) {
					mu.alert(shell,  "邮件信息", "邮件发送失败，请检查网络");
				}
			}
		});
		
		//修改密码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String qid=text.getText();
				String email=text_1.getText();
				String code=text_2.getText();
				String pwd=text_3.getText();
				String rpwd=text_4.getText();
				
				if("".equals(qid)||"".equals(pwd)||"".equals(rpwd)||"".equals(email)||"".equals(code)){
					mu.alert(shell, "提示信息", "信息没有填写完整，请验证后重新输入");
				}else if(!pwd.equals(rpwd)){
					mu.alert(shell, "提示信息", "两次密码输入不一致，请验证后重新输入");
				}else if(!code.equals(a.code)){
					mu.alert(shell, "提示信息", "验证码输入错误，请验证后重新输入");
				}else{
					String sql="select email from qq where qid=?";
					List<Object> params=new ArrayList<Object>();
					params.add(qid);
					List<Map<String, String>>  list=helper.findAll(sql, params);
					if(list.size()>0&&list.get(0).get("EMAIL").equals(email)){				
						String sql1="update qq set pwd=? where qid=?";
						List<Object> params1=new ArrayList<Object>();
						params1.add(pwd);
						params1.add(qid);
						int result=helper.doUpdate(sql1, params1);
						//将注册表中的密码删除
						sql1 = "delete from registers where qid = " + qid;
						int result1 = db.doUpdate(sql1, null);
						if(result > 0 && result1 > 0){
							a.qid = qid;
							mu.alert(shell, "提示信息", "重置密码成功，请用新密码登陆");			
							shell.dispose();
							
						}else{
							mu.alert(shell, "提示信息", "重置密码失败");
						}
						
					}else{
						mu.alert(shell,  "提示信息", "此QQ号不存在或者绑定邮箱错误");
					}
				}
				
				
				
			}
		});
		

	}
}
