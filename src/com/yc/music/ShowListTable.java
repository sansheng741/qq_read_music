package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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

public class ShowListTable extends Composite {
	public Table table;
	private Helper help=new Helper();
	/*********ck 8.24***************/
	private MyUtils mu=new MyUtils();
	private Menu menu;
	private DBHelper db = new DBHelper();
	/*********************************/
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ShowListTable(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(null);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setBounds(0, 0, 849, 594);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(39);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(221);
		tableColumn_1.setText("\u97F3\u4E50\u6807\u9898");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(200);
		tableColumn_2.setText("\u6B4C\u624B");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(224);
		tableColumn_3.setText("\u4E13\u8F91");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(131);
		tableColumn_4.setText("\u65F6\u957F");
		
		Label lblNewLabel = new Label(this, SWT.NONE);		
		lblNewLabel.setToolTipText("\u5220\u9664\u6B4C\u5355");
		lblNewLabel.setImage(SWTResourceManager.getImage(ShowListTable.class, "/images/close-circle.png"));
		lblNewLabel.setBounds(857, 20, 30, 35);
		
		Label lblNewLabel_1 = new Label(this, SWT.WRAP | SWT.CENTER);
		lblNewLabel_1.setBounds(865, 57, 16, 74);
		lblNewLabel_1.setText("\u5220\u9664\u6B4C\u5355");
		
		/*********ck 8.24***************/
		menu = new Menu(table);
				
		MenuItem itemDel = new MenuItem(menu, SWT.PUSH);
		itemDel.setText("删除");
		MenuItem itemRefresh = new MenuItem(menu, SWT.PUSH);
		itemRefresh.setText("刷新");
		/**
		 * 鼠标 - 右键 - down
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//System.out.println(e.button);
				// 1-左键  2-滑轮 3-右键
				if(e.button == 3){
					//显示菜单
					table.setMenu(menu);
				}				
			}
		});
		/**
		 * 菜单 - 刷新
		 */
		itemRefresh.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				showTable();
			}
			
		});
		/**
		 * 菜单 - 删除
		 */
		itemDel.addListener(SWT.Selection,new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				//得到用户点击的那一行
				if(table.getSelectionCount() > 0){
					TableItem ti = table.getSelection()[0];
					//数据库操作
					String sql = "delete from list where lname = '"+ a.ListTableName +"' and qid = '"+a.qid+"' and "
							+ "mid = (select mid from music where msong = '"+ti.getText(1)+"' and msinger = '"+ti.getText(2)+"')";
					int result = db.doUpdate(sql, null);
					if(result > 0){
						mu.alert(getShell(), "提示信息", "删除成功");
						showTable();
					}else{
						mu.alert(getShell(), "提示信息", "删除失败");
					}
				}
			}
			
		});
		/*********************************/
		
		//歌单的点击事件
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem ti=(TableItem)e.item;
				a.label.setToolTipText("暂停");	
			
				a.list=help.getTableItem(table);
				a.ti=ti;
				a.playSong();
				a.label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
				a.tflag=true;
				
			}
		});
		
		
		/**
		 * 删除歌单
		 */
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				DelSongSheet dss = new DelSongSheet();
				dss.open();
			}
		});

	}
	private void showTable() {
		table.removeAll();
		String sql="select m.* from list l inner join music m on l.mid=m.mid where l.qid='"+a.qid+"'and lname = '"+ a.ListTableName +"'";
		List<MusicInfo> list = db.findAll(sql, null, MusicInfo.class);
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
