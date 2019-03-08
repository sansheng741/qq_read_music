package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.ck.bean.QQ;
import com.ck.dao.Helper;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class TongYiShengQing {
	private Helper help=new Helper();

	protected Shell shell;
	private String  groupname;
	private Text text;
	private FormData fd_combo;
	private String qid;

	/**
	 * Launch the application.
	 * @param args
	 */
	public TongYiShengQing(){
		
	}
public TongYiShengQing(String qid){
		this.qid=qid;
	}
	public static void main(String[] args) {
		try {
			TongYiShengQing window = new TongYiShengQing();
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
		shell.setImage(SWTResourceManager.getImage(TongYiShengQing.class, "/images/qq.png"));
		shell.setSize(277, 346);
		shell.setText("\u597D\u53CB\u7533\u8BF7");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(TongYiShengQing.class, "/images/qqlogin2.png"));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		composite.setLayout(new FormLayout());
		
		
		Combo combo = new Combo(composite, SWT.NONE);
		fd_combo = new FormData();
		fd_combo.right = new FormAttachment(0, 61);
		fd_combo.height = 30;
		combo.setLayoutData(fd_combo);
		FormData fd_combo1 = new FormData();
		fd_combo1.top = new FormAttachment(10, 10);
		fd_combo1.left = new FormAttachment(0, 72);
		fd_combo1.right = new FormAttachment(0, 160);		
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		fd_combo.top = new FormAttachment(lblNewLabel, 6);
		fd_combo.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 3);
		fd_lblNewLabel.right = new FormAttachment(100, -251);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("\u8BF7\u9009\u62E9");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(combo, 17);
		fd_btnNewButton.right = new FormAttachment(100, -15);
		fd_btnNewButton.top = new FormAttachment(combo, -2, SWT.TOP);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("\u786E\u8BA4");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 307);
		fd_composite_1.right = new FormAttachment(0, 261);
		fd_composite_1.top = new FormAttachment(0, 146);
		fd_composite_1.left = new FormAttachment(0);
		composite_1.setLayoutData(fd_composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(composite_1, SWT.BORDER);
		tree.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.right = new FormAttachment(combo, 0, SWT.RIGHT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("\u7533\u8BF7\u7406\u7531:");
		
		text = new Text(composite, SWT.BORDER | SWT.WRAP);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		fd_lblNewLabel_1.top = new FormAttachment(text, 3, SWT.TOP);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(btnNewButton, 0, SWT.RIGHT);
		fd_text.bottom = new FormAttachment(composite_1, -35);
		fd_text.top = new FormAttachment(btnNewButton, 15);
		fd_text.left = new FormAttachment(btnNewButton, 0, SWT.LEFT);
		text.setLayoutData(fd_text);
		
		String sql3="select * from getfriend where qid2 ='"+QQDL.qid+"' and qid1='"+qid+"'";
		List<Map<String,String>>list3=help.findAll(sql3, null);
		if(list3.get(0).get("LIYOU")==null ||"".equals(list3.get(0).get("LIYOU"))){
			text.setText("");
		}else{
			text.setText(list3.get(0).get("LIYOU"));
		}
		groupname=list3.get(0).get("GROUPNAME");
		
		combo.add("同意");
		combo.add("拒绝");
		combo.select(0);
		
		//按钮选择 事件
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("同意")){
					tree.removeAll();
					
					//打印好友分组
					String sql="select * from qfriend where qid1='"+QQDL.qid+"'";
	   				
	   				List<Map<String,String>> list=help.findAll(sql, null);
	   				boolean flag;
	   					ArrayList<String>arr=new ArrayList<String>();//保存列名 
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
	   						for(int i=0;i<arr.size();i++){
	   							TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
	   							trtmNewTreeitem_2.setText(arr.get(i));
	   				     }
				}else{
					String sql3="delete  getfriend where qid1='"+qid+"' and qid2='"+QQDL.qid+"'";
					int result =help.doUpdate(sql3, null);
					
					shell.dispose();
				}
			}
		});
		//树的点击事件   选择分组
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti=(TreeItem)e.item;
				//申请人的扣扣 查询
				String sqll="select * from qq where qid='"+qid+"'";
				List<QQ>listl=help.findAllQQ(sqll, null, 0);
				
				/*String sql2="select * from qfriend where qid=? and qname =? ";
				List<Object>paramss=new ArrayList<Object>();
				paramss.add(qid);
				paramss.add(QQDL.qid);
				List<Map<String,String>>list2=help.findAll(sql2, paramss);*/
				
				String tgroupname=ti.getText();
				String sql="insert into qfriend values(seq_qfid.nextval,?,?,?,?)";
				List<Object>params=new ArrayList<Object>();
				params.add(QQDL.qid);
				params.add(tgroupname);
				params.add(listl.get(0).qid);
				params.add(listl.get(0).qname);
				help.doUpdate(sql, params);
				
				String sql2="insert into qfriend values(seq_qfid.nextval,?,?,?,?)";
				List<Object>params2=new ArrayList<Object>();
				params2.add(listl.get(0).qid);
				params2.add(groupname);
				params2.add(QQDL.qid);
				String sql3="select q.qname from qq q where qid='"+QQDL.qid+"'";
				List<Map<String,String>> list3=help.findAll(sql3, null);
				params2.add(list3.get(0).get("QNAME"));
				help.doUpdate(sql2, params2);
				
				String sql4="delete getfriend where qid2='"+QQDL.qid+"' and qid1='"+qid+"'";
				help.doUpdate(sql4, null);
				
				shell.dispose();
			}
		});
	}
}
