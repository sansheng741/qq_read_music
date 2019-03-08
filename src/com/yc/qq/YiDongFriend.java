package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.ck.dao.Helper;
import com.ck.utils.MyUtils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class YiDongFriend  implements Runnable{
	private Helper help=new Helper();
	private MyUtils mu=new MyUtils();
	static String GroupName;
	private boolean tflag;

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public void close(){
		Display.getDefault().syncExec(new Runnable(){
			public void run(){
				
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				shell.close();
				shell.dispose();
			}
		});
	}
	public void run(){
		
	}
	
	public static void main(String[] args) {
		try {
			YiDongFriend window = new YiDongFriend();
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
		tflag=false;
		shell.open();
		shell.layout();
		
		//run22();
		if(tflag){
			shell.dispose();
		}
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
		shell.setImage(SWTResourceManager.getImage(YiDongFriend.class, "/images/qq.png"));
		shell.setSize(316, 300);
		shell.setText("\u9009\u62E9\u5206\u7EC4");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x/2, Display.getCurrent().getClientArea().height / 2 - shell.getSize().y/2);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(composite, SWT.BORDER);
		
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
					/*tree.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							
							//mu.alert(shell, "提示信息", "添加成功");
						//	
							//System.exit(0);
						}
					});*/
					GroupName="";
					tree.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							TreeItem ti=tree.getSelection()[0];
							GroupName=ti.getText();
							
							shell.dispose();
						}
					});

	}
	
}
