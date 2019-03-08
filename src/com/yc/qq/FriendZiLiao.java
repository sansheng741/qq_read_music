package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.QQ;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;

import org.eclipse.swt.layout.FillLayout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class FriendZiLiao {
	private Helper help =new Helper();
	private int flag=0;
	private MyUtils mu = new MyUtils();
	protected Shell shell;
	private Text text;
	private String qid;
	private String qname;
	private String beizu;
	private String friendname;

	/**
	 * Launch the application.
	 * @param args
	 */
	public FriendZiLiao(){
		
	}
	 public FriendZiLiao(String qid,String beizu){
			this.qid=qid;
			this.beizu=beizu;
		
		}
	
	
  public FriendZiLiao(String qid){
		this.qid=qid;
	}
	
	
	public static void main(String[] args) {
		try {
			FriendZiLiao window = new FriendZiLiao();
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
		shell.setImage(SWTResourceManager.getImage(FriendZiLiao.class, "/images/qq.png"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(785, 540);
		shell.setText("\u597D\u53CB\u8D44\u6599");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setBackgroundImage(SWTResourceManager.getImage(FriendZiLiao.class, "/images/qqlogin2.png"));
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		composite_3.setLayout(null);
		
		Label lblNewLabel = new Label(composite_3, SWT.BORDER);
		lblNewLabel.setBounds(10, 48, 121, 111);
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setBounds(151, 49, 37, 23);
		label_2.setText("\u5907\u6CE8:");
		
		text = new Text(composite_3, SWT.NONE);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text.setBounds(193, 46, 182, 23);
		text.setEnabled(false);
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setBounds(301, 97, 74, 21);
		lblNewLabel_3.setText("  \u4FEE\u6539");
		sashForm_1.setWeights(new int[] {295, 203});
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				flag++;
				if(flag%2==1){
					friendname=text.getText();
					lblNewLabel_3.setText("±£´æÐÞ¸Ä");
					text.setEnabled(true);
				}else if(flag%2==0){
					lblNewLabel_3.setText("ÐÞ¸Ä");
					if(text.getText()==null||"".equals(text.getText())){
						text.setText(friendname);
						return ;
					}
					
					
					String sql="update qfriend set beizu=?  where qid1=? and qid2=?";
					List<Object>params=new ArrayList<Object>();
					params.add(text.getText());
					params.add(QQDL.qid);
					params.add(qid);
					help.doUpdate(sql, params);
					Main.beizu=text.getText();
					try {
						Thread.currentThread().sleep(600);
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
					text.setEnabled(false);
				}
			}
		});
		
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setText("QQ:");
		lblNewLabel_1.setBounds(35, 29, 61, 27);
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setBounds(129, 29, 208, 27);
		
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setText("\u7F51\u540D:");
		label.setBounds(35, 79, 61, 27);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		
		label_1.setBounds(129, 79, 208, 27);
		
		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setText("\u7535\u8BDD:");
		label_4.setBounds(35, 179, 61, 27);
		
		Label label_5 = new Label(composite_2, SWT.NONE);
		label_5.setBounds(129, 179, 208, 27);
		
		Label label_6 = new Label(composite_2, SWT.NONE);
		label_6.setText("\u5730\u5740:");
		label_6.setBounds(35, 227, 61, 27);
		
		Label label_7 = new Label(composite_2, SWT.NONE);
		label_7.setBounds(129, 227, 208, 27);
		
		Label label_8 = new Label(composite_2, SWT.NONE);
		label_8.setText("\u7B7E\u540D:");
		label_8.setBounds(35, 272, 40, 27);
		
		Label label_9 = new Label(composite_2, SWT.WRAP);
		label_9.setBounds(129, 272, 208, 72);
		
		Label label_10 = new Label(composite_2, SWT.NONE);
		label_10.setText("\u6027\u522B:");
		label_10.setBounds(35, 128, 61, 27);
		
		Label label_11 = new Label(composite_2, SWT.NONE);
		
		label_11.setBounds(129, 128, 208, 27);
		sashForm.setWeights(new int[] {385, 381});
		String sql="select * from qq where qid='"+qid+"'";
		List<QQ>list=help.findAllQQ(sql, null, 0);
		
		if(list.size()>0){
			int width,height;
			width=lblNewLabel.getBounds().width;
			height=lblNewLabel.getBounds().height;
			InputStream is=new ByteArrayInputStream(list.get(0).qimage);
			ImageData id=new ImageData(is);
			id=id.scaledTo(width, height);
			Image image=new Image(null,id);
			lblNewLabel.setImage(image);
			lblNewLabel_2.setText(list.get(0).qid);
			label_1.setText(list.get(0).qname);
			if(beizu != null && !"".equals(beizu)){
				text.setText(beizu);
			}		
			label_5.setText(list.get(0).birthday);
			label_7.setText(list.get(0).addr);
			label_9.setText(list.get(0).motto);
			label_11.setText(list.get(0).sex);
			
		}

	}
}
