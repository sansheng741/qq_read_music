package com.yc.music;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.layout.FillLayout;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SelectMusic {

	protected Shell shell;
	private Table table;
	private TableColumn tableColumn;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private TableColumn tableColumn_3;
	private TableColumn tableColumn_4;
	private TableColumn tableColumn_5;
	private DBHelper db = new DBHelper();
	private MyUtils mu=new MyUtils();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SelectMusic window = new SelectMusic();
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
		shell.setImage(SWTResourceManager.getImage(SelectMusic.class, "/images/QQ\u97F3\u4E501.png"));
		shell.setSize(935, 518);
		shell.setText("\u9009\u62E9\u97F3\u4E50");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(46);
		tableColumn.setText("\u5E8F\u53F7");
		
		tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(293);
		tableColumn_1.setText("\u6B4C\u540D");
		
		tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(205);
		tableColumn_2.setText("\u6B4C\u624B");
		
		tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(155);
		tableColumn_3.setText("\u4E13\u8F91");
		
		tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(114);
		tableColumn_4.setText("\u65F6\u957F");
		
		tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(101);
		tableColumn_5.setText("\u5927\u5C0F");
		
		
		String sql = "select * from music order by mid";
		List<MusicInfo> list = db.findAll(sql, null ,MusicInfo.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).getMsong(),list.get(i).getMsinger()
						,list.get(i).getMalbum(),list.get(i).getMtime(),list.get(i).getMsize()});
			}
		}
		/**
		 * Ñ¡ÔñÒôÀÖ
		 */
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelectionCount()> 0){
					TableItem ti = table.getSelection()[0]; 
					a.songName = ti.getText(1);
					a.songSinger = ti.getText(2);
					shell.dispose();
				}
			}
		});
	}
}
