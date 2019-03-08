package com.ck.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.Librarybooks;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

public class MyBook extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private Menu menu;
	private MyUtils mu = new MyUtils();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyBook(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(211);
		tblclmnNewColumn.setText("\u5C01\u9762");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(227);
		tblclmnNewColumn_1.setText("\u4E66\u540D");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(182);
		tblclmnNewColumn_2.setText("\u4F5C\u8005");
		sashForm.setWeights(new int[] {316});
		
		showTable();
		
		//定义鼠标右击菜单
		menu = new Menu(table);
		MenuItem readBook = new MenuItem(menu,SWT.NONE);
		readBook.setText("阅读");
		MenuItem itemView = new MenuItem(menu,SWT.NONE);
		itemView.setText("查看详情");
		MenuItem del = new MenuItem(menu,SWT.NONE);
		del.setText("取消收藏");
		MenuItem refresh = new MenuItem(menu,SWT.NONE);
		refresh.setText("刷新");
		/**
		 * 鼠标 - 右键 - down
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//System.out.println(e.button);
				// 1-左键  2-滑轮 3-右键
				if(e.button == 3){
					//显示菜单
					table.setMenu(menu);
				}				
			}
		});
		/**
		 * 菜单 - 阅读
		 */
		readBook.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//得到用户点击的那一行
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					String bname = ti.getText(1);
					String path = "src/" + bname + ".txt";
					File f = new File(path);
					if(f.isFile()){
						try {
							Desktop.getDesktop().open(f);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}	
			}			
		});
		/**
		 * 菜单 - 查看详情
		 */
		itemView.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//得到用户点击的那一行
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					a.bname = ti.getText(1);
					a.flag = true;
					BookInfo bi = new BookInfo();				
					bi.open();
				}	
			}			
		});
		/**
		 * 菜单 - 取消收藏
		 */
		del.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					//得到bid
					String sql = "select bid from librarybooks where bname = '"+ ti.getText(1) +"'";
					List<Object> params = new ArrayList<Object>();	
					List<Map<String, String>> list1 = db.findAll(sql, null);
					if(list1.size() > 0){
						String bid = list1.get(0).get("BID");
						String qid = a.qid;
						//先去收藏表查是否已经收藏	
						sql = "select * from blend where bid = ? and qid = ?";											
						//清空集合
						params.clear();
						//注入参数
						params.add(bid);
						params.add(qid);
						List<Map<String, String>> list = db.findAll(sql, params);
						if(list.size() > 0){				
							sql = "delete blend where bid = ? and qid = ?";
							//清空集合
							params.clear();
							//注入参数
							params.add(bid);
							params.add(qid);
							int result2 = db.doUpdate(sql, params);				
							if(result2 > 0){
								//刷新
								table.removeAll();
								showTable();
							}
						}		
					}						
				}
			}
		});
		/**
		 * 菜单 - 刷新
		 */
		refresh.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				table.removeAll();
				showTable();
			}
			
		});
	}
	
	private void showTable() {
		//查收藏表
		String sql = "select * from blend,librarybooks where blend.bid = librarybooks.bid and qid = " + a.qid;
		List<Librarybooks> list = db.findAll(sql, null, Librarybooks.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				InputStream is = new ByteArrayInputStream(list.get(i).getPicture());
				ImageData id = new ImageData(is);
				id = id.scaledTo(170, 200);
				Image image = new Image(null,id);
				ti.setImage(0,image);
				ti.setText(1,list.get(i).getBname());
				ti.setText(2,list.get(i).getAuthor());				
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
