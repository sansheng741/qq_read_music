package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.ck.utils.a;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SrearchSongSheet extends Composite {
	//897 * 464
	private Table table;
	private DBHelper db = new DBHelper();
	private Helper help=new Helper();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SrearchSongSheet(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 897, 464);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(41);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(425);
		tableColumn_1.setText("\u7528\u6237\u540D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(425);
		tableColumn_2.setText("\u6B4C\u5355");
		
		showTable();
		
		//表格的点击事件  8.22 李鑫
				table.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						TableItem ti=(TableItem)e.item;
						a.label.setToolTipText("暂停");
						a.list=help.getTableItem(table);
						a.ti=ti;

						a.playSong();
						//AudioPlayer.player.stop(as);
						a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
						a.tflag=true;
						
					}
					
				});
				
	}

	public void showTable() {
		table.removeAll();
		String sql = "select distinct lname,qname from list l,qq q where l.qid = q.qid and lname like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + a.searchContent + "%");
		List<Map<String, String>> list = db.findAll(sql, params);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).get("QNAME"),
						list.get(i).get("LNAME")});
			}
		}
		a.searchCount = list.size();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
