package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.layout.FillLayout;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.ClassifyInfo;
import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;
import com.yc.qq.QQDL;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FindMusic extends Composite {

	// 898 * 580
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	private DBHelper db = new DBHelper();
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Label lblPicture;
	private StackLayout stackLayout2 = new StackLayout();
	private MyUtils mu = new MyUtils();
	
	private int iss = 1;
	private Label lblNewLabel_6;
	private Table table;
	/***************ck**********************/
	private Menu menu;
	private int SongSheeti;
	private ScrolledComposite scrolledComposite;
	private SashForm sashForm;
	private Composite composite_2;
	private boolean flag = false; //判断菜单中是否已经存在
	/*************************************/
	public FindMusic(Composite parent, int style) {
		super(parent, style);
		setLayout(stackLayout2);

		sashForm = new SashForm(this, SWT.NONE);
		sashForm.setBounds(0, 0, 830, 629);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(8, 5, 472, 241);


		lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(495, 5, 392, 110);


		lblPicture = new Label(composite, SWT.NONE);
		lblPicture.setBounds(495, 135, 392, 110);

		// 置顶
		stackLayout2.topControl = sashForm;
		sashForm.setVisible(true);
		
		scrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		sashForm.setWeights(new int[] {252, 367});

		composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBounds(0, 0, 830, 629);

		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 863, 577);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(213);
		tableColumn_1.setText("\u97F3\u4E50\u6807\u9898");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(152);
		tableColumn_2.setText("\u6B4C\u624B");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(236);
		tableColumn_3.setText("\u4E13\u8F91");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(87);
		tableColumn_4.setText("\u65F6\u957F");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(121);
		tableColumn_5.setText("\u5927\u5C0F");

		
		
		ToolBar toolBar = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(866, -5, 35, 28);
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
	
		toolItem.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/btn_mini_normal.png"));
		
		// 添加鼠标右键菜单 给table添加		
		menu = new Menu(table);		
		/**
		 * 查数据库
		 * 当前用户有多少个歌单
		 * 循环new 菜单
		 */
		ShowMenu();						
		
		/**
		 * 图片滚动
		 */
		new Thread(new Runnable() {
			public void run() {
				while (!a.playflag) {					
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {

							lblNewLabel.setImage(SWTResourceManager.getImage(FindMusic.class,
									"/images/\u672A\u6807\u9898-" + iss + ".jpg"));
							lblNewLabel_1.setImage(SWTResourceManager.getImage(FindMusic.class,
									"/images/\u672A\u6807\u9898-" + (iss+4) + ".jpg"));
							lblPicture.setImage(SWTResourceManager.getImage(FindMusic.class,
									"/images/\u672A\u6807\u9898-" + (iss+5) + ".jpg"));
						}
					});
					// 睡眠得放下面，不能放上面
					try {
						Thread.sleep(3000);	
						iss++;
						if(iss == 4){
							iss = 1;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
		
		//展示发现音乐的歌单
		showSongSheet();
		
		/*
		 *  退出这块面板
		 */
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout2.topControl = sashForm;
				composite_2.setVisible(false);
				sashForm.setVisible(true);
			}
		});
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem ti=(TableItem)e.item;
				a.label.setToolTipText("暂停");	
				a.list=db.getTableItem(table);
				a.ti=ti;
				a.playSong();
				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
			}
		});
		
			
	}

	/**
	 * 展示菜单
	 */
	public void ShowMenu() {
		menu.dispose();
		menu = new Menu(table);
		
		MenuItem itemdownLoad = new MenuItem(menu, SWT.PUSH);
		itemdownLoad.setText("下载->");
		MenuItem itemiLove = new MenuItem(menu, SWT.PUSH);
		itemiLove.setText("我喜欢的音乐");	
		
		String sql = "select distinct lname from list where qid = '"+ a.qid +"' and lname != '我喜欢的音乐'";
		List<Map<String, String>> list = db.findAll(sql, null);
		if(list.size() > 0){
			for(SongSheeti = 0; SongSheeti < list.size(); SongSheeti++){
				MenuItem itemiLove2 = new MenuItem(menu, SWT.PUSH);			
				itemiLove2.setText(list.get(SongSheeti).get("LNAME"));
				//添加到歌单
				//先判断该歌单是否已经有这首歌
				itemiLove2.addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(Event arg0) {
						if(table.getSelectionCount() > 0){
							TableItem ti = table.getSelection()[0];
							String msql = "select * from music where msong='" + ti.getText(1) + "'and msinger='" + ti.getText(2)
									+ "'";
							List<Map<String, String>> mlist = db.findAll(msql, null);
							if (mlist.size() > 0) {
								//查这个人的这个歌单里有没有这首歌
								String lsql = "select * from list where qid = ? and lname = ? and mid = ?";
								List<Object> params = new ArrayList<Object>();
								params.add(a.qid);
								params.add(itemiLove2.getText());
								params.add(mlist.get(0).get("MID"));
								List<Map<String, String>>  list = db.findAll(lsql, params);
								if(list.size() <= 0){
									//如果没有查到，则可以添加
									//插入歌单
									lsql = "insert into list values(?,?,?)";
									params.clear();
									params.add(itemiLove2.getText());
									params.add(a.qid);
									params.add(mlist.get(0).get("MID"));
									int result = db.doUpdate(lsql, params);
									if (result > 0) {
										mu.alert(getShell(), "提示信息", "已收藏到歌单");
									}else{
										mu.alert(getShell(), "提示信息", "收藏失败");
									}
								}else{
									mu.alert(getShell(), "提示信息", "歌曲已存在");
								}									
							}
						}	
					}
				});				
			}
		}
		/*
		 * 表格按下事件 显示菜单
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {
					table.setMenu(menu);
				}
			}
		});
		/**
		 * 下载
		 */
		itemdownLoad.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					//查数据库得到歌曲路径
					String sql = "select mmp3 from music where msong = '"+ ti.getText(1) +"' and msinger = '"+ ti.getText(2) +"'";
					List<Map<String, String>> list = db.findAll(sql, null);
					if(list.size() > 0){
						//去目标文件夹查看是否已经有这首歌，如果有则不下载
						String songName = list.get(0).get("MMP3").substring(
								list.get(0).get("MMP3").lastIndexOf("\\")+1);
						//访问该路径下的所有文件
						if(a.DownLoadPath != null && !"".equals(a.DownLoadPath )){
							File file = new File(a.DownLoadPath);
							File[] fList = file.listFiles();
							for(int i = 0; i < fList.length; i++){
								if(fList[i].isFile()){							
									File f = new  File(fList[i].toString());
									if( f.getName().equals(songName) ){
										mu.alert(getShell(), "提示信息", "该歌曲已下载过了");
										return;
									}
								}
							}
						}
						//传路径
						a.songPath.add(list.get(0).get("MMP3"));
					}else{
						mu.alert(getShell(), "提示信息", "下载错误");
					}
				}	
			}
		});
		/**
		 * 我喜欢 菜单功能 添加此歌
		 */
		itemiLove.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					String msql = "select * from music where msong='" + ti.getText(1) + "'and msinger='" + ti.getText(2)
							+ "'";
					List<Map<String, String>> mlist = db.findAll(msql, null);
					if (mlist.size() > 0) {
						//判断歌单里是否已经有这首歌
						//查这个人的这个歌单里有没有这首歌
						String lsql = "select * from list where qid = ? and lname = '我喜欢的音乐' and mid = ?";
						List<Object> params = new ArrayList<Object>();
						params.add(a.qid);
						params.add(mlist.get(0).get("MID"));
						List<Map<String, String>>  list = db.findAll(lsql, params);
						if(list.size() <= 0){
							//如果没有查到，则可以添加
							//插入歌单
							lsql = "insert into list values('我喜欢的音乐',?,?)";
							params.clear();
							params.add(a.qid);
							params.add(mlist.get(0).get("MID"));
							int result = db.doUpdate(lsql, params);
							if (result > 0) {
								mu.alert(getShell(), "提示信息", "已收藏到歌单");
							}else{
								mu.alert(getShell(), "提示信息", "收藏失败");
							}
						}else{
							mu.alert(getShell(), "提示信息", "歌曲已存在");
						}	
						
					}
				}	
			}
		});
	}


	/***************ck**********************/
	public void showSongSheet() {
		/*
		 * 显示发现音乐的歌曲 图片加歌名
		 */
		String sql = "select * from classify order by cid";
		List<ClassifyInfo> list = db.findAll(sql, null, ClassifyInfo.class);
			
		 // 创建新的面板
		  Composite composite_1 = new Composite(scrolledComposite, SWT.NONE); // 设置内容
		  scrolledComposite.setContent(composite_1);
		  sashForm.setWeights(new int[] {268, 344});
		  scrolledComposite.setMinSize(860, (list.size() / 4 + 1) * 166);
		  ShowGroup sg = new ShowGroup();
		  
		  for (int i = 0; i < list.size();i++) { 				  
			  sg.createGroup(composite_1, list.get(i),composite_2,table,sashForm);				  
		  }
	}
	/*************************************/


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
