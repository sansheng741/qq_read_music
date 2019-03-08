package com.yc.qq;

import java.awt.MouseInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SendDynamic {

	protected Shell shell;
	private Text text;
	private MyUtils mu=new MyUtils();
	private Boolean dargFlag = false;
	private int x,y; //鼠标点击时的坐标
	private String filePath;
	private DBHelper db = new DBHelper();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SendDynamic window = new SendDynamic();
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
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/qq.png"));
		shell.setSize(546, 349);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);
		
		Composite composite_3 = new Composite(sashForm_2, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_3.setBackground(SWTResourceManager.getColor(0, 191, 255));
		
		Label lblNewLabel_2 = new Label(composite_3, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("楷体", 21, SWT.BOLD));
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(211, 5, 121, 29);
		lblNewLabel_2.setText("\u53D1\u8868\u52A8\u6001");
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/qq\u7A7A\u95F4.png"));
		lblNewLabel_3.setBounds(176, 0, 38, 34);
		
		Label lblNewLabel_4 = new Label(composite_3, SWT.NONE);		
		lblNewLabel_4.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/\u53C9 (1).png"));
		lblNewLabel_4.setBounds(509, 4, 38, 29);
		
		SashForm sashForm_1 = new SashForm(sashForm_2, SWT.NONE);
		
		text = new Text(sashForm_1, SWT.WRAP | SWT.V_SCROLL);		
		text.setText("\u8BF4\u70B9\u513F\u4EC0\u4E48\u5427");
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);		
		lblNewLabel_1.setToolTipText("\u9009\u62E9\u7167\u7247");
		lblNewLabel_1.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/\u76F8\u673A.png"));
		lblNewLabel_1.setBounds(10, 10, 67, 61);
		sashForm_1.setWeights(new int[] {623, 119});
		sashForm_2.setWeights(new int[] {39, 82});
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label lblNewLabel = new Label(composite_1, SWT.BORDER);
		lblNewLabel.setBounds(109, 10, 292, 200);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);		
		btnNewButton.setBounds(454, 183, 80, 27);
		btnNewButton.setText("\u53D1\u9001");
		sashForm.setWeights(new int[] {125, 219});
		
		//面板拖拽
		composite_3.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if( dargFlag ){
										//MouseInfo.getPointerInfo().getLocation().x 距桌面左侧距离
										//x  距面板左侧距离
										//相减后得到面板距桌面左侧距离
					shell.setLocation(MouseInfo.getPointerInfo().getLocation().x - x,
							MouseInfo.getPointerInfo().getLocation().y - y	);
										//与鼠标移动先坐标相减，的到移动距离
				}
			}
		});
		composite_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//鼠标按下，可以移动
				dargFlag = true;
				x = e.x;
				y = e.y;
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				//鼠标松开，不能移动
				dargFlag = false;
			}
		});
		/**
		 * 关闭
		 */
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblNewLabel_4.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/叉 (2) .png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_4.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/\u53C9 (1).png"));
				shell.dispose();
			}
		});
		/**
		 * 相机
		 */
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblNewLabel_1.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/相机 (1).png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_1.setImage(SWTResourceManager.getImage(SendDynamic.class, "/images/\u76F8\u673A.png"));
				//打开一个文件选择框
				FileDialog fd = new FileDialog(shell);
				//设置选择类型
				fd.setFilterExtensions(new String[]{"*.jpg","*.png","*.gif"});
				fd.open();				
				if(fd != null && !"".equals(fd.getFilterPath())){
					//文件路径
					filePath = fd.getFilterPath() + "\\" + fd.getFileName();
					//得到图片
					Image image = null;
					try {
						File f = new File(filePath);
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
		 * 发送
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//时间
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//System.out.println(sdf.format(date));
				
				
				//往说说表中添加数据
				//得到说说内容
				String content = text.getText();
				String sql = null;
				//得到图片
				//用byte[],存放图片
				byte[] bt = null;
				if(filePath != null && !"".equals(filePath)){						
					try {
						InputStream is = new FileInputStream( new File(filePath) );
						bt = new byte[is.available()];
						is.read(bt);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					sql = "insert into shuoshuo values(?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'))";
				}else{
					sql = "insert into shuoshuo values(?,?,null,to_date(?,'yyyy-MM-dd hh24:mi:ss'))";
				}
				//数据库操作				
				List<Object> params = new ArrayList<Object>();
				params.add(QQDL.qid);		//账号
				params.add(content);	//发表内容
				if(filePath != null && !"".equals(filePath)){
					//将字节数组存到数据库中					
					params.add(bt);		//图片
				}	

				//添加时间  ,to_date('2018-08-28','yyyy-mm-dd')
				params.add(sdf.format(date));			//时间
				int result = db.doUpdate(sql, params);
				if(result > 0){
					mu.alert(shell, "提示信息", "动态发送成功^-^");
					shell.dispose();
				}else{
					mu.alert(shell, "提示信息", "动态发送失败-_-");
					shell.dispose();
				}
				
			}
		});
		/**
		 * 文本框 - 鼠标点下时情况默认内容
		 */
		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if( "说点儿什么吧".equals(text.getText())){
					text.setText("");
				}
			}
		});
	}

}
