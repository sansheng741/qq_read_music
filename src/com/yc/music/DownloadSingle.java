package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DownloadSingle extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private int DownLoadCount = 1; //下载歌曲数目
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DownloadSingle(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(40);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(277);
		tableColumn_1.setText("\u97F3\u4E50\u6807\u9898");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(152);
		tableColumn_2.setText("\u6B4C\u624B");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(236);
		tableColumn_3.setText("\u4E13\u8F91");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(87);
		tableColumn_4.setText("\u65F6\u957F");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(79);
		tableColumn_5.setText("\u5927\u5C0F");
		
		showTable();
		//发现音乐的点击播发
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem ti=(TableItem)e.item;
				a.label.setToolTipText("暂停");	
				a.list=db.getTableItem(table);
				a.ti=ti;
				a.playSong();
				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
				
			}
		});
	}

	public void showTable() {
		table.removeAll();
		DownLoadCount = 1;
		//访问该路径下的所有文件
		if(a.DownLoadPath != null && !"".equals(a.DownLoadPath)){
			File file = new File(a.DownLoadPath);
			if(!file.exists()){
				file.mkdir();	
			}
			File[] fList = file.listFiles();
			if(fList != null && !"".equals(fList)){
				for(int i = 0; i < fList.length; i++){
					if(fList[i].isFile()){
						File f = new  File(fList[i].toString());
						//判断文件格式						
						String fileType = f.getName().substring(f.getName().lastIndexOf("."));
						String[] info = f.getName().split(" - ");
						if(info.length == 1 || info.length == 0){
							continue;
						}
						info[0] = info[0].replace(",", "/");
						info[1] = info[1].substring(0, info[1].lastIndexOf("."));
						if(".mp3".equals(fileType)){
							try {
								if(info[0] == null || "".equals(info[0])){
									info[0] = "?";
								}
								if(info[1] == null || "".equals(info[1])){
									info[1] = "?";
								}
								//查数据库，得到该歌曲的所有信息
								String sql = "select * from music where msong = ? and msinger = ?";
								List<Object> params = new ArrayList<Object>();
								params.add(info[1]);
								params.add(info[0]);
								List<MusicInfo> list = db.findAll(sql, params, MusicInfo.class);
								if(list.size() > 0){
									TableItem ti = new TableItem(table,SWT.NONE);
									ti.setText(new String[]{DownLoadCount++ + "",list.get(0).getMsong(),
											list.get(0).getMsinger(),list.get(0).getMalbum(),
											list.get(0).getMtime(),list.get(0).getMsize()});
									ti.setData("filepath", list.get(0).getMmp3());
								}
							} catch (Exception e) {
								e.getStackTrace();
							}
						}					
					}
				}			
			}					
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
