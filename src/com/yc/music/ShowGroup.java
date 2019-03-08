package com.yc.music;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.ck.bean.ClassifyInfo;
import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ShowGroup {
	private int x = 0, y = 0;
	private Label lblNewLabel_1;
	private DBHelper db = new DBHelper();

	public Label lblNewLabel;

	// public Group group;
	/**
	 * @param sashForm 
	 * @wbp.parser.entryPoint
	 */
	public void createGroup(Composite composite_1, ClassifyInfo classifyInfo, Composite composite_2,Table table, SashForm sashForm) {

		Group group = new Group(composite_1, SWT.NONE);

		// 设置group 长 高
		// x: 0 1 2 3 0 1 2 3 0 1 2 3
		// x: 23 235 447 659 23 235 447 659
		// y: 0 0 0 0 1 1 1 1 2 2 2 2
		// y: 10 10 10 10 156 156 156 156
		group.setBounds(23 + 212 * x, 10 + 146 * y, 175, 140);
		// 找 x y规律
		if (x % 4 == 0) {
			x = 1;
		} else if (x % 4 == 1) {
			x = 2;
		} else if (x % 4 == 2) {
			x = 3;
		} else if (x % 4 == 3) {
			x = 0;
			y++;
		}
		lblNewLabel = new Label(group, SWT.NONE);

		lblNewLabel.setBounds(0, 10, 175, 110);
		// 插入图片
		Image image = null;
		if (classifyInfo.getCimage() != null) {
			int width = lblNewLabel.getBounds().width;
			int height = lblNewLabel.getBounds().height;
			InputStream is = new ByteArrayInputStream(classifyInfo.getCimage());
			ImageData id = new ImageData(is);
			id = id.scaledTo(width, height);
			image = new Image(group.getDisplay(), id);
			lblNewLabel.setImage(image);
		} else {
			lblNewLabel.setText("");
		}

		lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);

		lblNewLabel_1.setBounds(45, 121, 83, 17);
		
		// 插入歌名
		lblNewLabel_1.setText(classifyInfo.getCname());
		
		//设置标签，点击不同的group得到不同的歌单名
		group.setData("cname", classifyInfo.getCname());
		
		/*
		 * 图片点击事件
		 */
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				table.removeAll();
				String sql = "select * from classify c inner join music m on c.cid=m.cid where c.cname='"
						+ group.getData("cname") + "'";
				List<MusicInfo> list=db.findAll(sql, null,MusicInfo.class);
				if(list.size()>0){
					composite_1.setVisible(true);
					 for(int i=0;i<list.size();i++){
						 TableItem ti=new TableItem(table,SWT.NONE);
						 ti.setText(new String[]{(i+1)+"",list.get(i).getMsong(),
								 list.get(i).getMsinger(),list.get(i).getMalbum(),
								 list.get(i).getMtime(),list.get(i).getMsize()});
						 ti.setData("filepath",list.get(i).getMmp3());
					 }
				}
				sashForm.setVisible(false);
				composite_2.setVisible(true);
			}
		});
	}

}
