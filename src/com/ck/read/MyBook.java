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
		
		//��������һ��˵�
		menu = new Menu(table);
		MenuItem readBook = new MenuItem(menu,SWT.NONE);
		readBook.setText("�Ķ�");
		MenuItem itemView = new MenuItem(menu,SWT.NONE);
		itemView.setText("�鿴����");
		MenuItem del = new MenuItem(menu,SWT.NONE);
		del.setText("ȡ���ղ�");
		MenuItem refresh = new MenuItem(menu,SWT.NONE);
		refresh.setText("ˢ��");
		/**
		 * ��� - �Ҽ� - down
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//System.out.println(e.button);
				// 1-���  2-���� 3-�Ҽ�
				if(e.button == 3){
					//��ʾ�˵�
					table.setMenu(menu);
				}				
			}
		});
		/**
		 * �˵� - �Ķ�
		 */
		readBook.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//�õ��û��������һ��
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
		 * �˵� - �鿴����
		 */
		itemView.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//�õ��û��������һ��
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
		 * �˵� - ȡ���ղ�
		 */
		del.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					//�õ�bid
					String sql = "select bid from librarybooks where bname = '"+ ti.getText(1) +"'";
					List<Object> params = new ArrayList<Object>();	
					List<Map<String, String>> list1 = db.findAll(sql, null);
					if(list1.size() > 0){
						String bid = list1.get(0).get("BID");
						String qid = a.qid;
						//��ȥ�ղر���Ƿ��Ѿ��ղ�	
						sql = "select * from blend where bid = ? and qid = ?";											
						//��ռ���
						params.clear();
						//ע�����
						params.add(bid);
						params.add(qid);
						List<Map<String, String>> list = db.findAll(sql, params);
						if(list.size() > 0){				
							sql = "delete blend where bid = ? and qid = ?";
							//��ռ���
							params.clear();
							//ע�����
							params.add(bid);
							params.add(qid);
							int result2 = db.doUpdate(sql, params);				
							if(result2 > 0){
								//ˢ��
								table.removeAll();
								showTable();
							}
						}		
					}						
				}
			}
		});
		/**
		 * �˵� - ˢ��
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
		//���ղر�
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
