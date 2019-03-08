package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.utils.a;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DownloadManager extends Composite {
	//898 * 580
	private StackLayout stackLayout = new StackLayout();	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DownloadManager(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);		
		lblNewLabel_2.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/\u5DF2\u4E0B\u8F7D\u5355\u66F2-2.png"));
		lblNewLabel_2.setBounds(238, 20, 135, 42);
		
		Label label = new Label(composite, SWT.NONE);		
		label.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/\u5DF2\u4E0B\u8F7DMV-1.png"));
		label.setBounds(374, 20, 135, 42);
		
		Label label_1 = new Label(composite, SWT.NONE);		
		label_1.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/\u6B63\u5728\u4E0B\u8F7D-1.png"));
		label_1.setBounds(508, 20, 135, 42);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewLabel.setBounds(37, 33, 75, 20);
		lblNewLabel.setText("\u4E0B\u8F7D\u8DEF\u5F84\uFF1A");
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.WRAP);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(112, 33, 160, 68);
		//默认路径
		lblNewLabel_1.setText(a.DownLoadPath);

		Label lblNewLabel_3 = new Label(composite_2, SWT.NONE);
		Link link = new Link(composite_2, SWT.NONE);		
		link.setBounds(323, 36, 53, 17);
		link.setText("<a>\u66F4\u6539\u8DEF\u5F84</a>");		
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		//设置堆栈布局
		composite_3.setLayout(stackLayout);
		sashForm_1.setWeights(new int[] {83, 246});
		sashForm.setWeights(new int[] {82, 495});
				
		
		//已下载单曲
		DownloadSingle Downloadsingle = new DownloadSingle(composite_3,SWT.NONE);
		stackLayout.topControl = Downloadsingle;
		//已下载Mv
		DownloadMv Downloadmv = new DownloadMv(composite_3,SWT.NONE);
		//正在下载
		DownLoading downLoading = new DownLoading(composite_3,SWT.NONE);
		/**
		 * 已下载单曲
		 */
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_2.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/已下载单曲-2.png"));
				label.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/已下载MV-1.png"));
				label_1.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/正在下载-1.png"));
				lblNewLabel_3.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/白.png"));
				//更换面板
				Downloadsingle.setVisible(true);
				Downloadmv.setVisible(false);
				downLoading.setVisible(false);
			}
		});
		/**
		 * 已下载MV
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_2.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/已下载单曲-1.png"));
				label.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/已下载MV-2.png"));
				label_1.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/正在下载-1.png"));				
				lblNewLabel_3.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/白.png"));

				//更换面板
				Downloadsingle.setVisible(false);
				Downloadmv.setVisible(true);
				downLoading.setVisible(false);
			}
		});
		/**
		 * 正在下载
		 */
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_2.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/已下载单曲-1.png"));
				label.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/已下载MV-1.png"));
				label_1.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/正在下载-2.png"));
			
				//更换面板
				Downloadsingle.setVisible(false);
				Downloadmv.setVisible(false);
				downLoading.setVisible(true);
				
				lblNewLabel_3.setToolTipText("\u5168\u90E8\u5F00\u59CB");
				lblNewLabel_3.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/\u97F3\u4E50_\u5168\u90E8\u5F00\u59CB.png"));
				lblNewLabel_3.setBounds(800, 36, 32, 35);				
				//展示要下载的音乐
				downLoading.showTable();
			}
		});
		/**
		 * 下载按钮
		 */
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblNewLabel_3.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/音乐_全部开始 (1).png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_3.setImage(SWTResourceManager.getImage(DownloadManager.class, "/images/\u97F3\u4E50_\u5168\u90E8\u5F00\u59CB.png"));
				downLoading.loadMusic();
				Downloadsingle.showTable();
			}
		});
		/**
		 * 更换路径
		 */
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new  DirectoryDialog(a.shell);
				//设置文件对话框的标题
				dd.setText("文件选择");
				//设置对话框提示文本信息
				dd.setMessage("选择下载的存储目录");					
				a.DownLoadPath = dd.open();
				lblNewLabel_1.setText(a.DownLoadPath);
				Downloadsingle.showTable();
			}
		});
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
