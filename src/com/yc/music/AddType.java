package com.yc.music;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.ClassifyInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AddType {

	protected Shell shell;
	private Text text;
	private MyUtils mu=new MyUtils();
	private DBHelper db = new DBHelper();
	private String picPath;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddType window = new AddType();
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setImage(SWTResourceManager.getImage(AddType.class, "/images/qq.png"));
		shell.setSize(398, 334);
		shell.setText("\u6DFB\u52A0\u6B4C\u66F2\u5206\u7C7B");
		
		mu.centerShell(shell);
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		text.setBounds(44, 25, 290, 38);
		
		Button btnNewButton = new Button(shell, SWT.NONE);		
		btnNewButton.setBounds(79, 252, 80, 27);
		btnNewButton.setText("\u53D6\u6D88");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);		
		btnNewButton_1.setBounds(240, 252, 80, 27);
		btnNewButton_1.setText("\u786E\u5B9A");
		
		Label lblNewLabel = new Label(shell, SWT.BORDER);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(97, 79, 190, 159);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);		
		lblNewLabel_1.setImage(SWTResourceManager.getImage(AddType.class, "/images/\u76F8\u673A(2).png"));
		lblNewLabel_1.setBounds(340, 26, 35, 34);
		/**
		 * 照片选择
		 */
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//打开一个文件选择框
				FileDialog fd = new FileDialog(shell);
				//设置选择类型
				fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
				fd.open();
				if(fd != null && !"".equals(fd.getFilterPath())){
					//文件路径
					picPath = fd.getFilterPath() + "\\" + fd.getFileName();
					//得到图片
					Image image = null;
					try {
						File f = new File(picPath);
						//得到IO流
						InputStream is = new FileInputStream( f );
						//得到ImageData
						ImageData id = new ImageData( is );
						//改变图片大小
						int width,height;
						width = lblNewLabel.getBounds().width;
						height = lblNewLabel.getBounds().height;
						//缩放
						id = id.scaledTo(width, height);
						image = new Image( null, id );	
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					//显示图片
					lblNewLabel.setImage(image);
				}
			}
		});
		/**
		 * 取消
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		/**
		 * 确定
		 */
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text.getText() != null && !"".equals(text.getText())){
					//查数据库有没有这个分类
					String sql = "select * from classify where cname = ?";
					List<Object> params = new ArrayList<Object>();
					params.add(text.getText());
					List<ClassifyInfo> list = db.findAll(sql, params, ClassifyInfo.class);
					
					if(list.size() > 0){
						mu.alert(shell, "提示信息", "该分组已存在");
					}else{
						byte[] bt = null;
						if(picPath != null && !"".equals(picPath)){						
							try {
								InputStream is = new FileInputStream( new File(picPath) );
								bt = new byte[is.available()];
								is.read(bt);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							sql = "insert into classify values(seq_cid.nextval,?,?)";
						}else{
							sql = "insert into classify values(seq_cid.nextval,?,null)";
						}	
						params.clear();
						
						params.add(text.getText());
						if(picPath != null && !"".equals(picPath)){
							//将字节数组存到数据库中					
							params.add(bt);
						}
						int result = db.doUpdate(sql, params);
						if(result > 0){
							mu.alert(shell, "提示信息", "分类添加成功");
							shell.dispose();
						}else{
							mu.alert(shell, "提示信息", "分类添加失败");
							shell.dispose();
						}
					}
				}			
			}
		});

	}
}
