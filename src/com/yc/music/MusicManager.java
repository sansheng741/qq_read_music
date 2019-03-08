package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MusicManager extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private MyUtils mu=new MyUtils();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MusicManager(Composite parent, int style) {
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
		tblclmnNewColumn.setWidth(46);
		tblclmnNewColumn.setText("\u5E8F\u53F7");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(293);
		tblclmnNewColumn_1.setText("\u6B4C\u540D");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(205);
		tblclmnNewColumn_2.setText("\u6B4C\u624B");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(155);
		tblclmnNewColumn_3.setText("\u4E13\u8F91");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(114);
		tblclmnNewColumn_4.setText("\u65F6\u957F");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(98);
		tblclmnNewColumn_5.setText("\u5927\u5C0F");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);		
		btnNewButton.setBounds(268, 29, 80, 27);
		btnNewButton.setText("\u6DFB\u52A0\u6B4C\u66F2");
		
		Button button = new Button(composite_1, SWT.NONE);		
		button.setText("\u5220\u9664\u6B4C\u66F2");
		button.setBounds(594, 29, 80, 27);
		sashForm.setWeights(new int[] {445, 77});
		
		//一开始就展示
		showTable();
		/**
		 * 添加歌曲
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddSong as = new AddSong();
				as.open();
			}
		});
		
		/**
		 * 删除歌曲
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//得到用户点击的那一行
				if(table.getSelectionCount()> 0){
					TableItem ti = table.getSelection()[0]; 
					//数据库操作
					//先去list里面删除
					String sql = "delete from list where mid = (select mid from music where msong = ?  and  msinger = ?)";
					List<Object> params = new ArrayList<Object>();
					params.add(ti.getText(1));
					params.add(ti.getText(2));
					int result1 = db.doUpdate(sql, params);
					sql = "delete from music where msong = ?  and  msinger = ?";
					/*params.clear();
					params.add(ti.getText(1));
					params.add(ti.getText(2));*/
					int result = db.doUpdate(sql, params); 
					if(result > 0 && result1 >= 0){
						mu.alert(a.shell, "提示信息", "删除成功");
					}else{
						mu.alert(a.shell, "提示信息", "删除失败");
					}
					//刷新
					showTable();
				}
			}
		});

	}

	public void showTable() {
		table.removeAll();
		//查music表
		String sql = "select * from music order by mid";
		List<MusicInfo> list = db.findAll(sql, null ,MusicInfo.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).getMsong(),list.get(i).getMsinger()
						,list.get(i).getMalbum(),list.get(i).getMtime(),list.get(i).getMsize()});
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
