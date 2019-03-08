package com.ck.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.BookType;
import com.ck.bean.Librarybooks;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Type extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private TreeItem trtmNewTreeitem;
	private TreeItem ti;
	private Menu menu;
	private String type;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Type(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(composite, SWT.BORDER);		
		tree.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		tree.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		
		trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		trtmNewTreeitem.setText("\u5168\u90E8");
		trtmNewTreeitem.setExpanded(true);
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(133);
		tblclmnNewColumn_1.setText("\u4E66\u540D");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(168);
		tblclmnNewColumn_2.setText("\u4F5C\u8005");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(135);
		tblclmnNewColumn_3.setText("\u51FA\u7248\u793E");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(84);
		tblclmnNewColumn_4.setText("\u4EF7\u683C");
		sashForm_1.setWeights(new int[] {545});
		//展示树，注意顺序
		showTree();
		trtmNewTreeitem.setExpanded(true);
		sashForm.setWeights(new int[] {120, 527});
		/**
		 * 类别 - 树的点击事件
		 */
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ti = (TreeItem) e.item;
				type = ti.getText();	
				table.removeAll();
				showTable(type);
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
				showTable(type);				
			}			
		});
	}
	
	protected void showTable(String type) {
		//查数据库
		String sql = "";
		List<Object> params = new ArrayList<Object>();	
		if(type.equals("全部")){
			sql = "select * from librarybooks";
		}else{
			sql = "select * from librarybooks where typename = ?";
			params.add(type);
		}
		List<Librarybooks> list = db.findAll(sql, params, Librarybooks.class);
		if(list.size() > 0){
			for(Librarybooks lb : list){
				TableItem ti1 = new TableItem(table,SWT.NONE);
				ti1.setText(new String[]{lb.getBname(),lb.getAuthor(),
						lb.getPub(),lb.getBprice()+""});
			}					
		}		
	}

	private void showTree() {
		//查类别表
		String sql = "select * from type";
		List<BookType> list = db.findAll(sql, null, BookType.class);
		if(list.size() > 0){
			for(BookType bt : list){
				TreeItem ti = new TreeItem(trtmNewTreeitem,SWT.NONE);
				ti.setText( bt.getTypename() );
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
