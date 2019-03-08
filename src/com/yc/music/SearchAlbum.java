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

public class SearchAlbum extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	//897 * 464
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchAlbum(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 897, 464);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(41);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(425);
		tableColumn_1.setText("\u4E13\u8F91");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(425);
		tblclmnNewColumn.setText("\u6B4C\u624B");
		
		showTable();
		
		
		//表格的点击事件  8.22 李鑫
				table.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						TableItem ti=(TableItem)e.item;
						a.label.setToolTipText("暂停");

						if(a.as!=null){
							AudioPlayer.player.stop(a.as);
						}
						try {
							
							a.fileInputStream=new FileInputStream(new File(ti.getData("filepath").toString()));
							
							try {
								a.as=new AudioStream(a.fileInputStream);
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
							AudioPlayer.player.start(a.as);
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						}
						//AudioPlayer.player.stop(as);
						a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
						a.tflag=true;
						
					}
				});
				
		
	}

	public void showTable() {
		table.removeAll();
		String sql = "select malbum,msinger from music where malbum like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + a.searchContent + "%");
		List<MusicInfo> list = db.findAll(sql, params, MusicInfo.class);
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				TableItem ti = new TableItem(table,SWT.NONE);
				ti.setText(new String[]{(i+1)+"",list.get(i).getMalbum(),list.get(i).getMsinger()});
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
