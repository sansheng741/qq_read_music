package com.ck.read;

import java.awt.MouseInfo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class MainUi {
	protected Shell shell;
	private Boolean dargFlag = false;
	private int x,y; //鼠标点击时的坐标
	private MyUtils mu = new MyUtils();
	private DBHelper db = new DBHelper();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainUi window = new MainUi();
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
		shell.setSize(665, 768);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setBackgroundImage(SWTResourceManager.getImage(MainUi.class, "/images/manager2.jpg"));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_1, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		
		Composite composite_3 = new Composite(sashForm_3, SWT.NONE);
		
		ToolBar toolBar = new ToolBar(composite_3, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(595, -4, 70, 26);
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setImage(SWTResourceManager.getImage(MainUi.class, "/images/btn_mini_normal.png"));
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setImage(SWTResourceManager.getImage(MainUi.class, "/images/btn_close_normal.png"));
		
		Label label_4 = new Label(composite_3, SWT.WRAP);
		label_4.setText("\u4E66\u7C4D\u662F\u4EBA\u7C7B\u8FDB\u6B65\u7684\u9636\u68AF      \r\n\t\t   ----\u9AD8\u5C14\u57FA");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_4.setFont(SWTResourceManager.getFont("楷体", 21, SWT.BOLD));
		label_4.setBounds(10, 10, 458, 65);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(sashForm_3, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);		
		
		Composite composite_2 = new Composite(sashForm_1, SWT.BORDER);
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setText("\u4E66\u57CE");
		label.setBounds(37, 37, 30, 17);
		
		ToolBar toolBar_1 = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBounds(31, 1, 39, 38);
		
		ToolItem tltmNewItem = new ToolItem(toolBar_1, SWT.NONE);	
		tltmNewItem.setImage(SWTResourceManager.getImage(MainUi.class, "/images/\u5BB6.png"));
		
		ToolBar toolBar_2 = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setBounds(208, 1, 39, 38);
		
		ToolItem toolItem_2 = new ToolItem(toolBar_2, SWT.NONE);		
		toolItem_2.setImage(SWTResourceManager.getImage(MainUi.class, "/images/\u7C7B\u76EE \u54C1\u7C7B \u5206\u7C7B \u7C7B\u522B-2-02.png"));
		
		ToolBar toolBar_3 = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar_3.setBounds(394, 1, 39, 38);
		
		ToolItem toolItem_3 = new ToolItem(toolBar_3, SWT.NONE);		
		toolItem_3.setImage(SWTResourceManager.getImage(MainUi.class, "/images/\u4E66.png"));
		
		ToolBar toolBar_4 = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar_4.setBounds(575, 1, 39, 38);
		
		ToolItem toolItem_4 = new ToolItem(toolBar_4, SWT.NONE);		
		toolItem_4.setImage(SWTResourceManager.getImage(MainUi.class, "/images/\u6211 \u7684.png"));
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setText("\u5206\u7C7B");
		label_1.setBounds(214, 37, 30, 17);
		
		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setText("\u6211\u7684\u4E66\u7C4D");
		label_2.setBounds(391, 37, 48, 17);
		
		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setText("\u6211\u7684");
		label_3.setBounds(583, 37, 30, 17);
		sashForm_1.setWeights(new int[] {703, 60});
		sashForm.setWeights(new int[] {685});
		
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
		 * 最小化 -按钮
		 */
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setMinimized(true);
			}
		});
		/**
		 * 关闭 - 按钮
		 */
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		//书城
		BookStore bs = new BookStore(scrolledComposite,SWT.NONE);
		scrolledComposite.setContent(bs);
		//分类
		Type type = new Type(scrolledComposite,SWT.NONE);
		//收藏
		MyBook mb = new MyBook(scrolledComposite,SWT.NONE);
		//我的
		MyInfo mi = new MyInfo(scrolledComposite,SWT.NONE);
		sashForm_3.setWeights(new int[] {74, 625});
		scrolledComposite.setMinSize(645,780);
		/**
		 * 书城
		 */
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				scrolledComposite.setMinSize(645,780);
				scrolledComposite.setLocation(0, 77);
				scrolledComposite.setContent(bs);
			}
		});
		/**
		 * 分类
		 */
		toolItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				scrolledComposite.setMinSize(645,780);
				scrolledComposite.setLocation(0, 77);
				scrolledComposite.setContent(type);
			}
		});
		/**
		 * 收藏
		 */
		toolItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setMinSize(645,780);	
				scrolledComposite.setLocation(0, 77);
				scrolledComposite.setContent(mb);
			}
		});
		/**
		 * 我的
		 */
		toolItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setMinSize(520, 570);
				scrolledComposite.setLocation(73, 100);
				scrolledComposite.setContent(mi);
			}
		});
	}
}
