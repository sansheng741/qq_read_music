package com.yc.music;

import java.awt.MouseInfo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.utils.MyUtils;
import com.ck.utils.a;

public class ManagerUi {

	protected Shell shell;
	private MyUtils mu=new MyUtils();
	private Boolean dargFlag = false;
	private int x,y; //鼠标点击时的坐标
	private StackLayout stacklayout = new StackLayout();
	//标记当前选择的是哪个界面
	private int flag = 0;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ManagerUi window = new ManagerUi();
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
		shell.setImage(SWTResourceManager.getImage(ManagerUi.class, "/images/QQ\u97F3\u4E501.png"));
		shell.setSize(1117, 631);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(ManagerUi.class, "/images/\u56FE\u4E66.jpg"));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(ManagerUi.class, "/images/QQ\u97F3\u4E50.png"));
		lblNewLabel_1.setBounds(28, 5, 70, 70);
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("楷体", 24, SWT.NORMAL));
		lblNewLabel_2.setBounds(112, 23, 402, 41);
		lblNewLabel_2.setText("QQ\u97F3\u4E50\u64AD\u653E\u5668\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);		
		lblNewLabel_3.setImage(SWTResourceManager.getImage(ManagerUi.class, "/images/\u4E3B\u9875.png"));
		lblNewLabel_3.setBounds(747, 5, 56, 57);
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setAlignment(SWT.CENTER);
		lblNewLabel_4.setBounds(753, 64, 40, 17);
		lblNewLabel_4.setText("\u9996\u9875");
		
		Label label = new Label(composite_1, SWT.NONE);				
		label.setImage(SWTResourceManager.getImage(ManagerUi.class, "/images/\u5237\u65B0.png"));
		label.setBounds(852, 5, 56, 57);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("\u5237\u65B0");
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(858, 64, 40, 17);
		
		Label label_2 = new Label(composite_1, SWT.NONE);		
		label_2.setImage(SWTResourceManager.getImage(ManagerUi.class, "/images/\u9000\u51FA.png"));
		label_2.setBounds(956, 5, 56, 57);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("\u9000\u51FA");
		label_3.setAlignment(SWT.CENTER);
		label_3.setBounds(962, 64, 40, 17);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		
		Label lblNewLabel_5 = new Label(composite_3, SWT.NONE);		
		lblNewLabel_5.setAlignment(SWT.CENTER);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 25, SWT.NORMAL));
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(0, 70, 165, 44);
		lblNewLabel_5.setText("\u6B4C\u66F2\u7BA1\u7406");
		
		Label label_4 = new Label(composite_3, SWT.NONE);		
		label_4.setText("\u6B4C\u66F2\u5206\u7C7B");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 25, SWT.NORMAL));
		label_4.setAlignment(SWT.CENTER);
		label_4.setBounds(0, 152, 165, 44);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_4, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);
		
		Label lblNewLabel = new Label(sashForm_2, SWT.NONE);
		lblNewLabel.setText("您当前的位置：首页");
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		//设置堆栈布局
		composite_5.setLayout(stacklayout);
		
		sashForm_2.setWeights(new int[] {19, 559});
		sashForm_1.setWeights(new int[] {165, 900});
		sashForm.setWeights(new int[] {88, 573});
		
		//面板拖拽
		composite_1.addMouseMoveListener(new MouseMoveListener() {
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
		composite_1.addMouseListener(new MouseAdapter() {
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
		
		//首页
		ManagerHome mh = new ManagerHome(composite_5,SWT.NONE);
		//设置顶层
		stacklayout.topControl = mh;
		//音乐管理
		MusicManager mm = new MusicManager(composite_5,SWT.NONE);
		//音乐分类
		MusicType mt = new MusicType(composite_5,SWT.NONE);
		
		/**
		 * 首页
		 */
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mh.setVisible(true);
				mm.setVisible(false);
				mt.setVisible(false);
				lblNewLabel.setText("您当前的位置：首页");
			}
		});
		/**
		 * 音乐管理
		 */
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				a.shell = shell;
				flag = 1;
				mh.setVisible(false);
				mm.setVisible(true);
				mt.setVisible(false);
				lblNewLabel.setText("您当前的位置：音乐管理");
			}
		});
		/**
		 * 音乐分类
		 */
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				a.shell = shell;
				flag = 2;
				mh.setVisible(false);
				mm.setVisible(false);
				mt.setVisible(true);
				lblNewLabel.setText("您当前的位置：音乐分类");
			}
		});
		/**
		 * 刷新
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(flag == 1){
					mm.showTable();
				}else if(flag == 2){
					mt.showTypeTable();
				}
				
			}
		});
		/**
		 * 关闭
		 */
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
	}
}
