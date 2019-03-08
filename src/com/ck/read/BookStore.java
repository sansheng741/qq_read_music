package com.ck.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.Librarybooks;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BookStore extends Composite {
	//面板 660 * 780	
	private DBHelper db = new DBHelper();
	private int x = 0, y = 0;
	private Table table;
	private Text text;
	private Text text_1;
	private String sql;
	private Menu menu;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookStore(Composite parent, int style) {
		super(parent, SWT.NONE);		
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("\u4E66\u540D\uFF1A");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label.setBounds(30, 15, 45, 20);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(83, 15, 104, 23);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u4F5C\u8005\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_1.setBounds(247, 15, 45, 20);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(300, 15, 104, 23);
		
		Button button = new Button(composite, SWT.NONE);		
		button.setText("\u67E5\u627E");
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		button.setBounds(501, 15, 94, 27);
		
		table = new Table(sashForm, SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(160);
		tblclmnNewColumn.setText("\u4E66\u540D");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(139);
		tblclmnNewColumn_1.setText("\u4F5C\u8005");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(166);
		tblclmnNewColumn_2.setText("\u4EF7\u683C");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(180);
		tblclmnNewColumn_3.setText("\u51FA\u7248\u793E");
		sashForm.setWeights(new int[] {53, 727});
		//查找所有书籍
		sql = "select * from librarybooks";
		List<Librarybooks> list = db.findAll(sql, null, Librarybooks.class);		
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{list.get(i).getBname(),list.get(i).getAuthor(),
						list.get(i).getBprice()+"",list.get(i).getPub()});		
			}		
		}
		/**
		 * 查询
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bname = text.getText();
				String author = text_1.getText();
				
				boolean bnameb = "".equals(bname);
				boolean authorb = "".equals(author);
				
				//查询
				sql = "";
				List<Object> params = new ArrayList<Object>();
				if(!bnameb && !authorb){
					sql = "select * from librarybooks where bname like ? and author like ?";
					params.add("%" + bname + "%");
					params.add("%" + author + "%");
				}else if(!bnameb && authorb){
					sql = "select * from librarybooks where bname like ?";
					//模糊查询
					params.add("%" + bname + "%");
				}else if(bnameb && !authorb){
					sql = "select * from librarybooks where author like ?";
					params.add("%" + author + "%");
				}else{
					//全为空，查全部
					sql = "select * from librarybooks";
				}
				List<Librarybooks> lists = db.findAll(sql, params, Librarybooks.class);
				//添加之前删除table中的数据
				table.removeAll();
				for(int i = 0; i < lists.size(); i++){
					TableItem ti = new TableItem(table,SWT.NONE);
					ti.setText(new String[]{lists.get(i).getBname(),lists.get(i).getAuthor(),
							lists.get(i).getBprice()+"",lists.get(i).getPub()});		
				}	
			}
		});
		//定义鼠标右击菜单
		menu = new Menu(table);
		MenuItem itemView = new MenuItem(menu,SWT.NONE);
		itemView.setText("查看详情");
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
		 * 菜单 - 查看详情
		 */
		itemView.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//得到用户点击的那一行
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					a.bname = ti.getText(0);
					a.flag = false;
					BookInfo bi = new BookInfo();				
					bi.open();
				}	
			}			
		});
		/**
		 * 菜单 - 刷新
		 */
		refresh.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//添加之前删除table中的数据
				table.removeAll();
				//查找所有书籍
				sql = "select * from librarybooks";
				List<Librarybooks> list = db.findAll(sql, null, Librarybooks.class);		
				if(list.size() > 0){
					for(int i = 0; i < list.size(); i++){
						TableItem ti = new TableItem(table,SWT.NONE);
						ti.setText(new String[]{list.get(i).getBname(),list.get(i).getAuthor(),
								list.get(i).getBprice()+"",list.get(i).getPub()});		
					}		
				}
			}
			
		});
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
