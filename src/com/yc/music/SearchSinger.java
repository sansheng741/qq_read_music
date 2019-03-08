package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchSinger extends Composite {
	//897 * 464
	private Table table;
	private DBHelper db = new DBHelper();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchSinger(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 897, 464);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(52);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(840);
		tableColumn_2.setText("\u6B4C\u624B");
		
		showTable();
		
		//8.22 ¿ÓˆŒ
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem ti=(TableItem)e.item;
				a.label.setToolTipText("‘›Õ£");

				a.playSong();
				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
				
				
			}
		});
	}

	public void showTable() {
		table.removeAll();
		String sql = "select distinct msinger from music where msinger like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + a.searchContent + "%");
		List<MusicInfo> list = db.findAll(sql, params, MusicInfo.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).getMsinger()});
				
				ti.setData("file",list.get(i).getMmp3());
			}
		}
		a.searchCount = list.size();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
