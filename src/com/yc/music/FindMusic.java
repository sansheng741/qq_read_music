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
	private boolean flag = false; //�жϲ˵����Ƿ��Ѿ�����
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

		// �ö�
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
		
		// �������Ҽ��˵� ��table���		
		menu = new Menu(table);		
		/**
		 * �����ݿ�
		 * ��ǰ�û��ж��ٸ��赥
		 * ѭ��new �˵�
		 */
		ShowMenu();						
		
		/**
		 * ͼƬ����
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
					// ˯�ߵ÷����棬���ܷ�����
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
		
		//չʾ�������ֵĸ赥
		showSongSheet();
		
		/*
		 *  �˳�������
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
				a.label.setToolTipText("��ͣ");	
				a.list=db.getTableItem(table);
				a.ti=ti;
				a.playSong();
				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
			}
		});
		
			
	}

	/**
	 * չʾ�˵�
	 */
	public void ShowMenu() {
		menu.dispose();
		menu = new Menu(table);
		
		MenuItem itemdownLoad = new MenuItem(menu, SWT.PUSH);
		itemdownLoad.setText("����->");
		MenuItem itemiLove = new MenuItem(menu, SWT.PUSH);
		itemiLove.setText("��ϲ��������");	
		
		String sql = "select distinct lname from list where qid = '"+ a.qid +"' and lname != '��ϲ��������'";
		List<Map<String, String>> list = db.findAll(sql, null);
		if(list.size() > 0){
			for(SongSheeti = 0; SongSheeti < list.size(); SongSheeti++){
				MenuItem itemiLove2 = new MenuItem(menu, SWT.PUSH);			
				itemiLove2.setText(list.get(SongSheeti).get("LNAME"));
				//��ӵ��赥
				//���жϸø赥�Ƿ��Ѿ������׸�
				itemiLove2.addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(Event arg0) {
						if(table.getSelectionCount() > 0){
							TableItem ti = table.getSelection()[0];
							String msql = "select * from music where msong='" + ti.getText(1) + "'and msinger='" + ti.getText(2)
									+ "'";
							List<Map<String, String>> mlist = db.findAll(msql, null);
							if (mlist.size() > 0) {
								//������˵�����赥����û�����׸�
								String lsql = "select * from list where qid = ? and lname = ? and mid = ?";
								List<Object> params = new ArrayList<Object>();
								params.add(a.qid);
								params.add(itemiLove2.getText());
								params.add(mlist.get(0).get("MID"));
								List<Map<String, String>>  list = db.findAll(lsql, params);
								if(list.size() <= 0){
									//���û�в鵽����������
									//����赥
									lsql = "insert into list values(?,?,?)";
									params.clear();
									params.add(itemiLove2.getText());
									params.add(a.qid);
									params.add(mlist.get(0).get("MID"));
									int result = db.doUpdate(lsql, params);
									if (result > 0) {
										mu.alert(getShell(), "��ʾ��Ϣ", "���ղص��赥");
									}else{
										mu.alert(getShell(), "��ʾ��Ϣ", "�ղ�ʧ��");
									}
								}else{
									mu.alert(getShell(), "��ʾ��Ϣ", "�����Ѵ���");
								}									
							}
						}	
					}
				});				
			}
		}
		/*
		 * ������¼� ��ʾ�˵�
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
		 * ����
		 */
		itemdownLoad.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					//�����ݿ�õ�����·��
					String sql = "select mmp3 from music where msong = '"+ ti.getText(1) +"' and msinger = '"+ ti.getText(2) +"'";
					List<Map<String, String>> list = db.findAll(sql, null);
					if(list.size() > 0){
						//ȥĿ���ļ��в鿴�Ƿ��Ѿ������׸裬�����������
						String songName = list.get(0).get("MMP3").substring(
								list.get(0).get("MMP3").lastIndexOf("\\")+1);
						//���ʸ�·���µ������ļ�
						if(a.DownLoadPath != null && !"".equals(a.DownLoadPath )){
							File file = new File(a.DownLoadPath);
							File[] fList = file.listFiles();
							for(int i = 0; i < fList.length; i++){
								if(fList[i].isFile()){							
									File f = new  File(fList[i].toString());
									if( f.getName().equals(songName) ){
										mu.alert(getShell(), "��ʾ��Ϣ", "�ø��������ع���");
										return;
									}
								}
							}
						}
						//��·��
						a.songPath.add(list.get(0).get("MMP3"));
					}else{
						mu.alert(getShell(), "��ʾ��Ϣ", "���ش���");
					}
				}	
			}
		});
		/**
		 * ��ϲ�� �˵����� ��Ӵ˸�
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
						//�жϸ赥���Ƿ��Ѿ������׸�
						//������˵�����赥����û�����׸�
						String lsql = "select * from list where qid = ? and lname = '��ϲ��������' and mid = ?";
						List<Object> params = new ArrayList<Object>();
						params.add(a.qid);
						params.add(mlist.get(0).get("MID"));
						List<Map<String, String>>  list = db.findAll(lsql, params);
						if(list.size() <= 0){
							//���û�в鵽����������
							//����赥
							lsql = "insert into list values('��ϲ��������',?,?)";
							params.clear();
							params.add(a.qid);
							params.add(mlist.get(0).get("MID"));
							int result = db.doUpdate(lsql, params);
							if (result > 0) {
								mu.alert(getShell(), "��ʾ��Ϣ", "���ղص��赥");
							}else{
								mu.alert(getShell(), "��ʾ��Ϣ", "�ղ�ʧ��");
							}
						}else{
							mu.alert(getShell(), "��ʾ��Ϣ", "�����Ѵ���");
						}	
						
					}
				}	
			}
		});
	}


	/***************ck**********************/
	public void showSongSheet() {
		/*
		 * ��ʾ�������ֵĸ��� ͼƬ�Ӹ���
		 */
		String sql = "select * from classify order by cid";
		List<ClassifyInfo> list = db.findAll(sql, null, ClassifyInfo.class);
			
		 // �����µ����
		  Composite composite_1 = new Composite(scrolledComposite, SWT.NONE); // ��������
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
