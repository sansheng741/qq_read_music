package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LikeMusic extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private Helper help=new Helper();
	/*********ck 8.24***************/
	private MyUtils mu=new MyUtils();
	private Menu menu;
	/*********************************/
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LikeMusic(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(287);
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
		tableColumn_5.setWidth(79);
		tableColumn_5.setText("\u5927\u5C0F");
		
		showTable();
		//����˫���¼�  8.22 ����
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem ti=(TableItem)e.item;
				a.label.setToolTipText("��ͣ");
				a.list=help.getTableItem(table);
				a.ti=ti;
				a.playSong();

				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
				
			}
		});
		/*********ck 8.24***************/
		menu = new Menu(table);
		
		MenuItem itemDel = new MenuItem(menu, SWT.PUSH);
		itemDel.setText("ɾ��");
		MenuItem itemRefresh = new MenuItem(menu, SWT.PUSH);
		itemRefresh.setText("ˢ��");
		/**
		 * ��� - �Ҽ� - down
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//System.out.println(e.button);
				// 1-���  2-���� 3-�Ҽ�
				if(e.button == 3){
					//��ʾ�˵�
					table.setMenu(menu);
				}				
			}
		});
		/**
		 * �˵� - ˢ��
		 */
		itemRefresh.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				showTable();
			}
			
		});
		/**
		 * �˵� - ɾ��
		 */
		itemDel.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//�õ��û��������һ��
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					//���ݿ����
					String sql = "delete from list where lname = '��ϲ��������' and qid = '"+a.qid+"' and "
							+ "mid = (select mid from music where msong = '"+ti.getText(1)+"' and msinger = '"+ti.getText(2)+"')";
					int result = db.doUpdate(sql, null);
					if(result > 0){
						mu.alert(getShell(), "��ʾ��Ϣ", "ɾ���ɹ�");
						showTable();
					}else{
						mu.alert(getShell(), "��ʾ��Ϣ", "ɾ��ʧ��");
					}
				}
			}
			
		});
		/*********************************/
		
	}

	public void showTable() {
		table.removeAll();
		String sql="select m.* from list l inner join music m on l.mid=m.mid where l.qid='"+a.qid+"'and lname ='��ϲ��������'";
		List<MusicInfo> list=db.findAll(sql, null, MusicInfo.class);
	    if(list.size()>0){	    	
	    	for(int i=0;i<list.size();i++){
	    		TableItem ti=new TableItem(table,SWT.NONE);
	    		ti.setText(new String[]{(i+1)+"",list.get(i).getMsong(),list.get(i).getMsinger(),
	    				list.get(i).getMalbum(),list.get(i).getMtime(),list.get(i).getMsize()});		
	    		ti.setData("filepath", list.get(i).getMmp3());
	    	}
	    }
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
