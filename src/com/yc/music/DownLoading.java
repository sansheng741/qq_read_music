package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ck.bean.MusicInfo;
import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.layout.FillLayout;

public class DownLoading extends Composite {
	private Table table;
	private DBHelper db = new DBHelper();
	private MyUtils mu=new MyUtils();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DownLoading(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(636);
		tableColumn_1.setText("\u97F3\u4E50\u6807\u9898");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(204);
		tableColumn_5.setText("\u5927\u5C0F");
		
		/**
		 * 下载音乐
		 */
		loadMusic();
	}

	public void showTable() {
		table.removeAll();
		if(a.songPath.size() > 0){
			for(int i = 0; i < a.songPath.size(); i++){
				String song = a.songPath.get(i).substring(a.songPath.get(i).lastIndexOf("\\")+1);
				String[] info = song.split(" - ");		
				info[1] = info[1].substring(0, info[1].indexOf("."));
				info[0] = info[0].replace(",", "/");
				//查数据库得到这首歌的所有信息
				String sql = "select * from music where msong = '"+ info[1] +"' and msinger = '"+ info[0] +"'";
				List<MusicInfo> list = db.findAll(sql, null, MusicInfo.class);
				if(list.size() > 0){
					TableItem ti = new TableItem(table,SWT.NONE);
					ti.setText(new String[]{(i+1)+"",list.get(0).getMsong(),list.get(0).getMsize()});
					ti.setData("filepath", list.get(0).getMmp3());
				}
			}
		}
	}

	public void loadMusic() {
		InputStream bis = null;
		OutputStream bos = null;
		if(a.songPath.size() > 0){
			for(int i = 0; i < a.songPath.size(); i++){
				try {
					File file = new File(a.songPath.get(i));    //音乐路径
					String AimPath = a.DownLoadPath + a.songPath.get(i).substring(a.songPath.get(i).lastIndexOf("\\"));
					File file2 = new File(AimPath);	  //目标文件
					bis = new BufferedInputStream(new FileInputStream(file)); //将FileInputStream作为参数传入，建立数据通道
					bos = new BufferedOutputStream(new FileOutputStream(file2));
					byte[] bs = new byte[1024];
					int length = 0;
					while((length = bis.read(bs, 0, bs.length)) != -1){
						bos.write(bs, 0, bs.length);
						bos.flush();   //清空缓冲区，迫使缓冲区的数据全部写出
					}					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(bis != null)
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					if(bos != null)
						try {
							bos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
			//清空下载区
			a.songPath.clear();
			mu.alert(a.shell, "提示信息", "下载完成");
			table.removeAll();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
