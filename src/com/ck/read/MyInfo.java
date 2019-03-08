package com.ck.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.QqInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.a;

public class MyInfo extends Composite {
	private DBHelper db = new DBHelper();
	//520*570 
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyInfo(Composite parent, int style) {
		super(parent, style);
		Label lblNewLabel = new Label(this, SWT.BORDER);
		lblNewLabel.setBounds(44, 10, 440, 260);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("楷体", 34, SWT.NORMAL));
		lblNewLabel_1.setBounds(150, 276, 214, 43);
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("楷体", 24, SWT.NORMAL));
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(44, 335, 440, 36);
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel_3.setBounds(133, 388, 58, 33);
		lblNewLabel_3.setText("\u5E74\u9F84\uFF1A");
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_4.setBounds(242, 385, 109, 30);
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel_5.setBounds(133, 427, 58, 33);
		lblNewLabel_5.setText("\u6027\u522B\uFF1A");
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		label.setBounds(242, 424, 109, 30);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_1.setText("\u751F\u65E5\uFF1A");
		label_1.setBounds(133, 466, 58, 33);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		label_2.setBounds(242, 460, 109, 30);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_3.setText("\u5730\u5740\uFF1A");
		label_3.setBounds(133, 505, 58, 33);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		label_4.setBounds(242, 502, 109, 30);
		
		//查qq表
		String sql = "select * from qq where qid = " + a.qid;
		List<QqInfo> list = db.findAll(sql, null, QqInfo.class);
		if(list.size() > 0){
			InputStream is = new ByteArrayInputStream(list.get(0).getQimage());
			ImageData id = new ImageData(is);
			id = id.scaledTo(500, 260);
			Image image = new Image(null,id);
			lblNewLabel.setImage(image);
			lblNewLabel_1.setText(list.get(0).getQname());
			lblNewLabel_2.setText(list.get(0).getMotto());
			lblNewLabel_4.setText(list.get(0).getAge()+"");
			label.setText(list.get(0).getSex());
			label_2.setText(list.get(0).getBirthday());
			label_4.setText(list.get(0).getAdrr());
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
