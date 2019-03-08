package com.yc.music;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class DelSongSheet {

	protected Shell shell;
	private MyUtils mu=new MyUtils();
	private DBHelper db = new DBHelper();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DelSongSheet window = new DelSongSheet();
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
		shell = new Shell(SWT.NONE);
		shell.setSize(285, 134);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(DelSongSheet.class, "/images/\u5220\u9664\u6B4C\u53552.png"));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);		
		lblNewLabel.setBounds(51, 89, 81, 31);
		
		Label label = new Label(composite, SWT.NONE);		
		label.setBounds(145, 88, 81, 31);
		
		/**
		 * 确认
		 */
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String sql = "delete from list where lname = '"+ a.delSongSheetName +"' and qid = " + a.qid;
				db.doUpdate(sql, null);
				shell.dispose();
			}
		});
		
		/**
		 * 取消
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
	}

}
