package com.yc.qq;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.ck.bean.QqInfo;
import com.ck.bean.Shuoshuo;
import com.ck.dao.DBHelper;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AotoGroup {
	private Label lblNewLabel;
	private Label lblNewLabel_4;
	private DBHelper db = new DBHelper();
	
	private Image image = null;
	static Image image2 = null;
	
	int /* x=0, */ y = 0;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createGroup(Composite composite_4, Shuoshuo shuoshuo) {
		
		// 查询发表说说的人的信息
		String sql = "select * from qq where qid='" + shuoshuo.getQid() + "'";
		List<QqInfo> list = db.findAll(sql, null, QqInfo.class);
		QqInfo qi = list.get(0);
		
		Group group = new Group(composite_4, SWT.NONE);
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setBounds(0, 0 + 300 * y, 280, 300);
		y++;
		
		lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(22, 20, 69, 57);
		

		Label lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setBounds(114, 20, 148, 20);
		lblNewLabel_1.setText(qi.getQname());

		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBounds(114, 57, 314, 20);
		//设置时间
		lblNewLabel_2.setText(""+shuoshuo.getQtime());
		Label lblNewLabel_3 = new Label(group, SWT.NONE);
		
		lblNewLabel_3.setBounds(22, 97, 406, 57);
		lblNewLabel_3.setText(shuoshuo.getMsg());
		// TODO Auto-generated method stub
		lblNewLabel_4 = new Label(group, SWT.NONE);
		
		lblNewLabel_4.setBounds(22, 160, 137, 130);

		

		// 头像图片
		if (qi.getQimage()!=null) {
			int width = lblNewLabel.getBounds().width;
			int height = lblNewLabel.getBounds().height;
			InputStream is = new ByteArrayInputStream(qi.getQimage());
			ImageData id = new ImageData(is);
			id = id.scaledTo(width, height);
			image = new Image(group.getDisplay(), id);
			lblNewLabel.setImage(image);
		}else{
			lblNewLabel.setText("");
		}
		
		
		// 说说图片
		if(shuoshuo.getQimage()!=null){
			int width2 = lblNewLabel_4.getBounds().width;
			int height2 = lblNewLabel_4.getBounds().height;
			InputStream is2 = new ByteArrayInputStream(shuoshuo.getQimage());
			ImageData id2 = new ImageData(is2);
			id2 = id2.scaledTo(width2, height2);
			image2 = new Image(group.getDisplay(), id2);
			lblNewLabel_4.setImage(image2);
		}else{
			lblNewLabel_4.setText("");
		}
	
	}

}
