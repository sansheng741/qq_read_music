package com.ck.read;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.Librarybooks;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BookInfo {

	protected Shell shell;
	private MyUtils mu = new MyUtils();
	private DBHelper db = new DBHelper();
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_6;
	private Text text_7;
	private Combo combo;
	private Label label_10;
	private String filePath;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BookInfo window = new BookInfo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(703, 724);
		shell.setText("\u8BE6\u60C5\u4FE1\u606F");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("\u4E66\u7C4D\u8BE6\u60C5\u4FE1\u606F");
		label.setFont(SWTResourceManager.getFont("楷体", 31, SWT.NORMAL));
		label.setBounds(207, 10, 262, 38);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u7F16\u53F7\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_1.setBounds(71, 80, 47, 29);
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		text.setBounds(172, 79, 244, 29);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("\u4E66\u540D\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_2.setBounds(71, 142, 47, 29);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		text_1.setBounds(172, 141, 244, 29);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("\u4F5C\u8005\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_3.setBounds(71, 206, 47, 29);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		text_2.setBounds(172, 205, 244, 29);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("\u4EF7\u683C\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_4.setBounds(71, 268, 47, 29);
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		text_3.setBounds(172, 267, 244, 29);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("\u7C7B\u522B\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_5.setBounds(71, 333, 47, 29);
		
		combo = new Combo(composite, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		combo.setBounds(172, 333, 244, 33);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("\u51FA\u7248\u793E\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_8.setBounds(50, 405, 68, 29);
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		
		text_6.setBounds(172, 402, 244, 29);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("\u51FA\u7248\u65E5\u671F\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_9.setBounds(33, 467, 85, 29);
		
		text_7 = new Text(composite, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		
		text_7.setBounds(172, 464, 244, 29);
		
		label_10 = new Label(composite, SWT.BORDER);
		label_10.setBounds(456, 303, 209, 242);
		
		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setAlignment(SWT.CENTER);
		label_11.setText("\u5C01\u9762");
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_11.setBounds(516, 268, 85, 29);
		//全部设为不可动
		text.setEnabled(false);
		text_1.setEnabled(false);
		text_2.setEnabled(false);
		text_3.setEnabled(false);
		text_6.setEnabled(false);
		text_7.setEnabled(false);
		combo.setEnabled(false);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
	
		btnNewButton_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		btnNewButton_1.setBounds(122, 605, 91, 38);
		btnNewButton_1.setText("\u6536\u85CF");
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("\u53D6\u6D88");
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		button.setBounds(427, 605, 91, 38);
		
		//展示信息
		//根据bid，查数据库
		String sql = "select * from librarybooks where bname = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(a.bname);
		List<Librarybooks> list = db.findAll(sql, params, Librarybooks.class);
		if(list.size() > 0){
			text.setText(list.get(0).getBid());
			text_1.setText(list.get(0).getBname());
			text_2.setText(list.get(0).getAuthor());
			text_3.setText(list.get(0).getBprice()+"");
			combo.setText(list.get(0).getTypename());		
			text_6.setText(list.get(0).getPub());
			text_7.setText(list.get(0).getBdate().toString());
			Image image = null;
			if(list.get(0).getPicture() != null && !"".equals(list.get(0).getPicture())){
				if(list.size() > 0){
					//将数据库中的字节数组转换为图片
					InputStream is = new ByteArrayInputStream(list.get(0).getPicture());
					ImageData id = new ImageData( is );
					id = id.scaledTo(label_10.getBounds().width, label_10.getBounds().height);
					image = new Image(shell.getDisplay(),id);
				}	
				label_10.setImage(image);
			}			
		}
		/**
		 * 收藏
		 */
		if(a.flag){
			btnNewButton_1.setEnabled(false);
		}else{
			btnNewButton_1.setEnabled(true);
		}
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {							
				String bid = text.getText();
				String qid = a.qid; //------------------------------------
				String typename = combo.getText();
				//先去收藏表查是否已经收藏	
				String sql = "select * from blend where bid = ? and qid = ?";
				List<Object> params = new ArrayList<Object>();								
				//清空集合
				params.clear();
				//注入参数
				params.add(bid);
				params.add(qid);
				List<Map<String, String>> list = db.findAll(sql, params);
				if(list.size() <= 0){				
					sql = "insert into blend values(?,?)";
					//清空集合
					params.clear();
					//注入参数
					params.add(bid);
					params.add(qid);
					int result2 = db.doUpdate(sql, params);				
					if(result2 > 0){
						mu.alert(shell, "提示信息", "收藏成功");
						shell.dispose();
					}else{
						mu.alert(shell, "提示信息", "收藏失败");
						shell.dispose();
					}
				}else{
					mu.alert(shell, "提示信息", "您已收藏该书籍");
				}
				
			}
		});
		/**
		 * 取消
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

}
