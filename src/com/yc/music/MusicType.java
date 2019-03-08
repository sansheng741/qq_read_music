package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.ClassifyInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MusicType extends Composite {
	private Table table;
	private Table table_1;
	private DBHelper db = new DBHelper();
	private MyUtils mu=new MyUtils();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MusicType(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u5E8F\u53F7");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(810);
		tblclmnNewColumn_1.setText("\u6B4C\u66F2\u5206\u7C7B\u540D");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);		
		btnNewButton.setBounds(320, 14, 107, 27);
		btnNewButton.setText("\u6DFB\u52A0\u6B4C\u66F2\u5206\u7C7B");
		
		Button button = new Button(composite_1, SWT.NONE);		
		button.setText("\u5220\u9664\u6B4C\u66F2\u5206\u7C7B");
		button.setBounds(485, 14, 107, 27);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("\u5E8F\u53F7");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(260);
		tblclmnNewColumn_3.setText("\u6B4C\u540D");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(221);
		tblclmnNewColumn_4.setText("\u6B4C\u624B");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(148);
		tblclmnNewColumn_5.setText("\u4E13\u8F91");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("\u65F6\u957F");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_7.setWidth(85);
		tblclmnNewColumn_7.setText("\u5927\u5C0F");
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnNewButton_1 = new Button(composite_3, SWT.NONE);		
		btnNewButton_1.setBounds(224, 18, 80, 27);
		btnNewButton_1.setText("\u6DFB\u52A0\u6B4C\u66F2");
		
		Button button_1 = new Button(composite_3, SWT.NONE);		
		button_1.setText("\u5220\u9664\u6B4C\u66F2");
		button_1.setBounds(597, 18, 80, 27);
		sashForm.setWeights(new int[] {201, 57, 201, 57});
		
		showTypeTable();
		/**
		 * table
		 */
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelectionCount()> 0){
					TableItem ti = table.getSelection()[0];
					showMusicTable(ti.getText(1));
				}
			}
		});
		
		/**
		 * 添加歌曲分类
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddType at = new AddType();
				at.open();
				showTypeTable();
			}
		});
		/**
		 * 删除歌曲分类
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//得到用户点击的那一行
				if(table.getSelectionCount()> 0){
					TableItem ti = table.getSelection()[0]; 
					//数据库操作
					//把这个分类中所有的歌曲分类全部设为空
					String sql = "update music set cid = null where cid = (select cid from classify where cname = '"+ti.getText(1)+"')";
					List<Object> params = new ArrayList<Object>();
					int result1 = db.doUpdate(sql, null);
					sql = "delete from classify where cname = ?";
					params.clear();
					params.add(ti.getText(1));
					int result = db.doUpdate(sql, params); 
					if(result > 0 && result1 >= 0){
						mu.alert(a.shell, "提示信息", "删除成功");
					}else{
						mu.alert(a.shell, "提示信息", "删除失败");
					}
					//刷新
					showTypeTable();
					table_1.removeAll();
				}
			}
		});
		/**
		 * 添加歌曲
		 */
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//得到用户点击的那一行
				if(table.getSelectionCount()> 0){
					TableItem ti = table.getSelection()[0]; 
					String typename = ti.getText(1);
					SelectMusic sm = new SelectMusic();
					sm.open();
					//修改music中cid
					String sql = "update music set cid = (select cid from classify where cname = '"+ typename +"') where msong = ? and msinger = ?";
					List<Object> params = new ArrayList<Object>();
					params.add(a.songName);
					params.add(a.songSinger);
					int result = db.doUpdate(sql, params);
					if(result > 0){
						mu.alert(a.shell, "提示信息", "添加成功");
					}else{
						mu.alert(a.shell, "提示信息", "添加失败");
					}
					showMusicTable(typename);
				}
			}
		});
		/**
		 * 删除歌曲
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelectionCount()> 0){
					TableItem ti = table.getSelection()[0];
					String typename = ti.getText(1);
					//得到用户点击的那一行
					if(table_1.getSelectionCount()> 0){
						ti = table_1.getSelection()[0]; 
						String sql = "update music set cid = null where msong = ? and msinger = ?";
						List<Object> params = new ArrayList<Object>();
						params.add(ti.getText(1));
						params.add(ti.getText(2));
						int result = db.doUpdate(sql, params);
						if(result > 0){
							mu.alert(a.shell, "提示信息", "删除成功");
						}else{
							mu.alert(a.shell, "提示信息", "删除失败");
						}
						showMusicTable(typename);
					}
				}
			}
		});

	}

	public void showMusicTable(String typename) {
		table_1.removeAll();
		String sql = "select m.* from music m,classify c where m.cid = c.cid and c.cname = '"+ typename +"'";
		List<Map<String, String>> list = db.findAll(sql, null);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table_1,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).get("MSONG"),
						list.get(i).get("MSINGER"),list.get(i).get("MALBUM"),
						list.get(i).get("MTIME"),list.get(i).get("MSIZE")});
			}
		}
	}

	public void showTypeTable() {
		table.removeAll();
		//查classify表
		String sql = "select * from classify order by cid";
		List<ClassifyInfo> list = db.findAll(sql, null, ClassifyInfo.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).getCname()});
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
