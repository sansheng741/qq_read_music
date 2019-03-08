package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.ck.utils.a;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchSong extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private Helper help=new Helper();
	//897 * 464
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchSong(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(52);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(252);
		tblclmnNewColumn_1.setText("\u97F3\u4E50\u6807\u9898");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(215);
		tblclmnNewColumn_2.setText("\u6B4C\u624B");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(234);
		tblclmnNewColumn_3.setText("\u4E13\u8F91");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(137);
		tblclmnNewColumn_4.setText("\u65F6\u957F");
		
		showTable();
		
		//表格的点击事件
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem ti=(TableItem)e.item;
				a.label.setToolTipText("暂停");
				a.list=help.getTableItem(table);
				a.ti=ti;

				a.playSong();
				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
			}
		});

	}

	public void showTable() {
		table.removeAll();
		String sql = "select * from music where msong like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + a.searchContent + "%");
		List<MusicInfo> list = db.findAll(sql, params, MusicInfo.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).getMsong(),
						list.get(i).getMsinger(),list.get(i).getMalbum(),
						list.get(i).getMtime()});
				
				ti.setData("filepath",list.get(i).getMmp3());
			}
		}
		a.searchCount = list.size();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
