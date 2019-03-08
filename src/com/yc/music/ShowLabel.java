package com.yc.music;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.ListInfo;
import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

public class ShowLabel {
	private int y = 0;
	private DBHelper db = new DBHelper();
	
	
	public void createLabel(Composite composite_11, ListInfo listInfo, ShowListTable slt, Table table, Search sh, LikeMusic lem, DownloadManager lgm, LocationMusic lm, FindMusic fm) {

		Label lblNewLabel = new Label(composite_11, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(50, 5, 95, 27);
		lblNewLabel.setText("\u54C8\u54C8");
		
		Label lblNewLabel_1 = new Label(composite_11, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(ShowLabel.class, "/images/\u6211\u7684\u6B4C\u5355_1.png"));
		lblNewLabel_1.setBounds(0, 0, 50, 34);
		//规律
		//x不变   y变
		lblNewLabel.setBounds(50, 0+35*y, 95, 27);
		lblNewLabel_1.setBounds(0, 0+34*y, 50, 34);
		y++;
		lblNewLabel.setText(listInfo.getLname());
		
		
		lblNewLabel.setData("lname",listInfo.getLname());
		/*
		 * 歌单点击事件
		 */
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				table.removeAll();
				a.delSongSheetName = lblNewLabel.getData("lname").toString();
				a.ListTableName = lblNewLabel.getData("lname").toString(); //8.24 ck				
				String sql = "select * from list l inner join music m on l.mid = m.mid where l.lname='"
						+ lblNewLabel.getData("lname") + "' and l.qid = " + a.qid;
				List<MusicInfo> list=db.findAll(sql, null,MusicInfo.class);
				if(list.size()>0){
					composite_11.setVisible(true);
					 for(int i=0;i<list.size();i++){
						 TableItem ti=new TableItem(table,SWT.NONE);
						 ti.setText(new String[]{(i+1)+"",list.get(i).getMsong(),
								 list.get(i).getMsinger(),list.get(i).getMalbum(),
								 list.get(i).getMtime()});
						 ti.setData("filepath", list.get(i).getMmp3());
					 }
				}
				fm.setVisible(false);
				lm.setVisible(false);
				lgm.setVisible(false);
				lem.setVisible(false);
				sh.setVisible(false);
				slt.setVisible(true);
			}
		});
	}

}
