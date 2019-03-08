package com.yc.music;

import java.awt.MouseInfo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ck.bean.ListInfo;
import com.ck.bean.QqInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;
import com.yc.qq.QQDL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class MusicMainUi {
	//1100 * 720
	protected Shell shell;
	private MyUtils mu=new MyUtils();
	private Boolean dargFlag = false;
	private int x,y; //鼠标点击时的坐标
	private Text text;
	private StackLayout stacklayout = new StackLayout();
	private boolean flag;
	private DBHelper db = new DBHelper();
	private ScrolledComposite scrolledComposite;
	private ShowListTable slt;
	private Search sh;
	private LikeMusic lem;
	private DownloadManager lgm;
	private LocationMusic lm;
	private FindMusic fm;
	/******************ck 8.24**********************/
	public static Label lblNewLabel_9;
	public static Label label_5;
	public static Label lblNewLabel_7;
	/****************************************/
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			MusicMainUi window = new MusicMainUi();
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
		
		//李鑫  8.22
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				if(a.tt!=null)
				a.tt.stop();
				a.playflag=true;
				if(a.as!=null){
					AudioPlayer.player.stop(a.as);
				}
			}
		});
		
		shell.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/QQ\u97F3\u4E50.png"));
		shell.setSize(1100, 720);
		shell.setText("SWT Application");
		
		mu.centerShell(shell);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/500434791_banner.jpg"));
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		
		lblNewLabel.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/QQ\u97F3\u4E501.png"));
		lblNewLabel.setBounds(31, 4, 48, 47);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("楷体", 19, SWT.NORMAL));
		lblNewLabel_1.setBounds(88, 15, 78, 30);
		lblNewLabel_1.setText("QQ\u97F3\u4E50");
		
		ToolBar toolBar = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(1031, -6, 70, 26);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/btn_mini_normal.png"));
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/btn_close_normal.png"));
		
		text = new Text(composite_1, SWT.NONE);		
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(300, 13, 286, 30);
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.SHADOW_NONE);				
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/ss-2.png"));
		lblNewLabel_2.setBounds(586, 13, 27, 30);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_3, SWT.NONE);
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_5, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		
		Composite composite_7 = new Composite(sashForm_3, SWT.BORDER);
		composite_7.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_3 = new Label(composite_7, SWT.BORDER);		
		lblNewLabel_3.setBounds(47, 26, 95, 84);
		
		Label lblNewLabel_4 = new Label(composite_7, SWT.NONE);
		lblNewLabel_4.setAlignment(SWT.CENTER);
		lblNewLabel_4.setBounds(65, 128, 61, 17);		
				
		Composite composite_8 = new Composite(sashForm_3, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_4 = new SashForm(composite_8, SWT.NONE);
		sashForm_4.setOrientation(SWT.VERTICAL);
		
		Composite composite_9 = new Composite(sashForm_4, SWT.BORDER);
		composite_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_9.setLayout(null);
		
		Label lblNewLabel_6 = new Label(composite_9, SWT.NONE);			
		lblNewLabel_6.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/发现音乐-2.jpg"));
		lblNewLabel_6.setBounds(0, 0, 192, 38);
		
		Label label_2 = new Label(composite_9, SWT.SHADOW_NONE);		
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/\u672C\u5730\u97F3\u4E50-1.jpg"));
		label_2.setBounds(0, 38, 192, 38);
		
		Label label_3 = new Label(composite_9, SWT.NONE);		
		label_3.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/\u4E0B\u8F7D\u7BA1\u7406-1.jpg"));
		label_3.setBounds(0, 76, 192, 38);
		
		Label lblNewLabel_10 = new Label(composite_9, SWT.NONE);		
		lblNewLabel_10.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/\u6DFB\u52A0\u6B4C\u5355+.png"));
		lblNewLabel_10.setBounds(155, 126, 16, 16);
		
		Label label_4 = new Label(composite_9, SWT.NONE);		
		label_4.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/\u6211\u559C\u6B22\u7684\u97F3\u4E50-1.jpg"));
		label_4.setBounds(0, 114, 192, 38);
		
		Composite composite_10 = new Composite(sashForm_4, SWT.NONE);
		composite_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_5 = new SashForm(composite_10, SWT.NONE);
		sashForm_5.setOrientation(SWT.VERTICAL);
		
		scrolledComposite = new ScrolledComposite(sashForm_5, SWT.BORDER | 		SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite_12 = new Composite(sashForm_5, SWT.BORDER);
		
		Label lblNewLabel_8 = new Label(composite_12, SWT.NONE);
		lblNewLabel_8.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/music.png"));
		lblNewLabel_8.setBounds(10, 4, 61, 64);
		
		lblNewLabel_9 = new Label(composite_12, SWT.WRAP);
		lblNewLabel_9.setBounds(85, 7, 97, 30);
		lblNewLabel_9.setText("\u97F3\u4E50\u540D");
		
		label_5 = new Label(composite_12, SWT.WRAP);
		label_5.setText("\u6B4C\u624B");
		label_5.setBounds(85, 41, 107, 30);
		sashForm_5.setWeights(new int[] {168, 78});
		sashForm_4.setWeights(new int[] {154, 249});
		sashForm_3.setWeights(new int[] {171, 406});
		
		Composite composite_6 = new Composite(sashForm_2, SWT.BORDER);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm_2.setWeights(new int[] {196, 899});
		//设置堆栈布局
		composite_6.setLayout(stacklayout);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setBackgroundImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/500434791_banner.jpg"));
		composite_4.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_5 = new Label(composite_4, SWT.NONE);		
		lblNewLabel_5.setToolTipText("\u4E0A\u4E00\u9996");
		lblNewLabel_5.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/sys.png"));
		lblNewLabel_5.setBounds(26, 10, 51, 52);
		
		Label label = new Label(composite_4, SWT.NONE);		
		label.setToolTipText("\u6682\u505C");
		label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/bf.png"));
		//label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
		label.setBounds(99, 10, 51, 52);
		a.label=label;
		
		Label label_1 = new Label(composite_4, SWT.NONE);		
		label_1.setToolTipText("\u4E0B\u4E00\u9996");
		label_1.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/xys.png"));
		label_1.setBounds(171, 10, 51, 52);
		
		lblNewLabel_7 = new Label(composite_4, SWT.NONE);
		lblNewLabel_7.setBounds(916, 27, 61, 17);
		lblNewLabel_7.setText("\u65F6\u957F");
		a.composite_4=composite_4;
		
		ProgressBar progressBar = new ProgressBar(composite_4, SWT.NONE);
		progressBar.setBounds(239, 33, 668, 4);
		a.progressBar=progressBar;
		
		sashForm_1.setWeights(new int[] {581, 71});
		sashForm.setWeights(new int[] {60, 655});
		
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
	    /**
		 * 最小化 -按钮
		 */
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setMinimized(true);
			}
		});
		/**
		 * 关闭 - 按钮
		 */
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		/**************ck***********************/
		/**
		 * 显示用户头像和网名  
		 */
		//查数据库
		String sqls = "select * from qq where qid = " + a.qid;
		List<QqInfo> lists = db.findAll(sqls, null, QqInfo.class);
		if(lists.size() > 0){
			if(lists.get(0).getQimage() != null && !"".equals(lists.get(0).getQimage())){
				Image image = null;
				if(lists.size() > 0){
					//将数据库中的字节数组转换为图片
					InputStream is = new ByteArrayInputStream(lists.get(0).getQimage());
					ImageData id = new ImageData( is );
					id = id.scaledTo(lblNewLabel_3.getBounds().width, lblNewLabel_3.getBounds().height);
					image = new Image(shell.getDisplay(),id);
				}	
				lblNewLabel_3.setImage(image);
			}
			lblNewLabel_4.setText(lists.get(0).getQname());
		}
		/*************************************/		
		//发现音乐
		fm = new FindMusic(composite_6,SWT.NONE);
		stacklayout.topControl = fm;
		//本地音乐
		lm = new LocationMusic(composite_6,SWT.NONE, shell);
		//下载管理
		lgm = new DownloadManager(composite_6,SWT.NONE);
		//我喜欢的音乐
		lem = new LikeMusic(composite_6,SWT.NONE);
		//搜索
		sh = new Search(composite_6,SWT.NONE);		
		//我的歌单
		slt = new ShowListTable(composite_6,SWT.NONE);
		/*
		 * 显示歌单列表  
		 */
		showList();
		/**
		 * 搜索
		 */
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296){
					/***********陈凯*************/
					if(text.getText() != null && !"".equals(text.getText())){
						a.searchContent = text.getText();
						sh.lblNewLabel_1.setText(a.searchContent);	
						sh.searchSong.showTable();
						sh.lblNewLabel_2.setText("找到" + a.searchCount + "首单曲");
						fm.setVisible(false);
						lm.setVisible(false);
						lgm.setVisible(false);
						lem.setVisible(false);
						sh.setVisible(true);
						slt.setVisible(false);
						lblNewLabel_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/ss-2.png"));
					}	
					/***************************/	
				}
			}
		});
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				/***********陈凯*************/
				if(text.getText() != null && !"".equals(text.getText())){
					a.searchContent = text.getText();
					sh.lblNewLabel_1.setText(a.searchContent);	
					sh.searchSong.showTable();
					sh.lblNewLabel_2.setText("找到" + a.searchCount + "首单曲");
					fm.setVisible(false);
					lm.setVisible(false);
					lgm.setVisible(false);
					lem.setVisible(false);
					sh.setVisible(true);
					slt.setVisible(false);
					lblNewLabel_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/ss-2.png"));
				}	
				/***************************/	
			}
		});
		lblNewLabel_2.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				lblNewLabel_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/ss-1.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				lblNewLabel_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/ss-2.png"));
			}
		});
		/**
		 * 发现音乐
		 */
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				fm.setVisible(true);
				lm.setVisible(false);
				lgm.setVisible(false);
				lem.setVisible(false);
				sh.setVisible(false);
				slt.setVisible(false);
				lblNewLabel_6.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/发现音乐-2.jpg"));
				label_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/本地音乐-1.jpg"));
				label_3.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/下载管理-1.jpg"));
				label_4.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/我喜欢的音乐-1.jpg"));
				/********ck***************************/
				a.shell = shell;				
				//fm.showSongSheet();
				fm.ShowMenu();
				/***********************************/
			}
		});
		/**
		 * 本地音乐
		 */
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				fm.setVisible(false);
				lm.setVisible(true);
				lgm.setVisible(false);
				lem.setVisible(false);
				sh.setVisible(false);
				slt.setVisible(false);
				lm.getChildren()[0].setVisible(true);
				lm.getChildren()[1].setVisible(false);
				
				label_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/本地音乐-2.jpg"));
				lblNewLabel_6.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/发现音乐-1.jpg"));
				label_3.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/下载管理-1.jpg"));
				label_4.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/我喜欢的音乐-1.jpg"));
			}
		});
		
		
		/**
		 * 下载管理
		 */
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				a.shell = shell;
				fm.setVisible(false);
				lm.setVisible(false);
				lgm.setVisible(true);
				lem.setVisible(false);
				sh.setVisible(false);
				slt.setVisible(false);
				label_3.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/下载管理-2.jpg"));
				lblNewLabel_6.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/发现音乐-1.jpg"));
				label_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/本地音乐-1.jpg"));
				label_4.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/我喜欢的音乐-1.jpg"));
			}
		});
		/**
		 * 我喜欢的音乐
		 */
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				fm.setVisible(false);
				lm.setVisible(false);
				lgm.setVisible(false);
				lem.setVisible(true);
				sh.setVisible(false);
				slt.setVisible(false);
				label_4.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/我喜欢的音乐-2.jpg"));
				lblNewLabel_6.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/发现音乐-1.jpg"));
				label_3.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/下载管理-1.jpg"));
				label_2.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/本地音乐-1.jpg"));
				
				lem.showTable();
				fm.ShowMenu();
				showList();			
			}
		});
		/*********陈凯***************************************/
		/**
		 * 头像
		 */
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				LoginManager loginManager = new LoginManager();
				loginManager.open();
				fm.showSongSheet();
			}
		});		
		/**
		 * 添加歌单
		 */
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblNewLabel_10.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/\u6DFB\u52A0\u6B4C\u5355+.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_10.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/\u6DFB\u52A0\u6B4C\u5355++.png"));
				AddSongSheet ass = new AddSongSheet();
				ass.open();
				/**********ck*********/
				fm.ShowMenu();
				showList();
				/*********************/
			}
		});
		/*************************************************************/

		a.tflag = false;
		/**
		 * 李鑫  8.22
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if(a.tflag){
					label.setToolTipText("暂停");
					if(a.as!=null)
					AudioPlayer.player.stop(a.as);
					a.playingflag=false;
					a.tflag = false;
					label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/bf.png"));
					
				}else{
					label.setToolTipText("播放");
					if(a.as!=null)
					AudioPlayer.player.start(a.as);
					a.playingflag=true;
					a.tflag = true;
					label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
					
				}				
			}
		});
		/**
		 * 上一首
		 */
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblNewLabel_5.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/sys-2.png"));
			    //表示你点击的歌曲是 table里面的的第一首 不可以上一首
			}
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_5.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/sys.png"));
				 if(a.as==null)
					 return;
				
				if(Integer.parseInt(a.ti.getText(0).toString())==1){
				    	
						a.ti=a.list.get(a.list.size()-1);
						a.playingflag=true;
						a.playSong();
						//用路径查数据库得到音乐的名字和歌手
						/*************ck 8.24*********************/
						String sql = "select msong,msinger,mtime from music where mmp3 = ?";
						List<Object> params = new ArrayList<Object>();
						params.add(a.list.get(a.list.size()-1).getData("filepath").toString());
						List<Map<String, String>> list = db.findAll(sql, params);
						if(list.size() > 0){
							MusicMainUi.lblNewLabel_9.setText(list.get(0).get("MSONG"));
							MusicMainUi.label_5.setText(list.get(0).get("MSINGER"));
							MusicMainUi.lblNewLabel_7.setText(list.get(0).get("MTIME"));
						}
						/**********************************/
				    	return ;
				    }
				    //  19  18   在list  18 17 
				    int index=Integer.parseInt(a.ti.getText(0).toString())-2;
				    a.ti=a.list.get(index);
				    a.playingflag=true;
				    a.playSong();
				  //用路径查数据库得到音乐的名字和歌手
					/*************ck 8.24*********************/
					String sql = "select msong,msinger,mtime from music where mmp3 = ?";
					List<Object> params = new ArrayList<Object>();
					params.add(a.list.get(index).getData("filepath").toString());
					List<Map<String, String>> list = db.findAll(sql, params);
					if(list.size() > 0){
						MusicMainUi.lblNewLabel_9.setText(list.get(0).get("MSONG"));
						MusicMainUi.label_5.setText(list.get(0).get("MSINGER"));
						MusicMainUi.lblNewLabel_7.setText(list.get(0).get("MTIME"));
					}
					/**********************************/
			}
		});
		/**
		 * 下一首
		 */
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_1.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/xys-2.png"));
				
			}
			@Override
			public void mouseUp(MouseEvent e) {
				if(Integer.parseInt(a.ti.getText(0).toString())==(a.list.size())){
					
					a.ti=a.list.get(0);
					a.playingflag=true;
					a.playSong();
				    return;
				  }
				int index=Integer.parseInt(a.ti.getText(0).toString());
				a.ti=a.list.get(index);
				a.playingflag=true;
				a.playSong();
				label_1.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/xys.png"));
				
			}
		});
	}

	private void showList() {
		String mysql="select distinct lname from list where lname!='我喜欢的音乐' and qid = " + a.qid;
		List<ListInfo> mylist = db.findAll(mysql, null, ListInfo.class);
		if( mylist.size()>=0 ){			
			Composite composite_11 = new Composite(scrolledComposite, SWT.NONE);
			scrolledComposite.setContent(composite_11);
			scrolledComposite.setMinSize(170,(mylist.size()+1)*30);
			ShowLabel sl = new ShowLabel();
			for(int k = 0;k < mylist.size(); k++){
				sl.createLabel(composite_11,mylist.get(k),slt,slt.table,sh,lem,lgm,lm,fm);
			}
		}
	}
}
