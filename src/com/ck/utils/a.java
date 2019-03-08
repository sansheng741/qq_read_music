package com.ck.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.Librarybooks;
import com.ck.dao.DBHelper;
import com.ck.dao.Helper;
import com.yc.music.MusicMainUi;
import com.yc.music.ThreadTest;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class a {

	private static Helper help=new Helper();
	public  static Composite composite_4;
	
	public static String bid;
	public static String qid;
	public static String bname;
	public static Librarybooks lb;
	public static boolean flag;	
	public static String code;
	public static Shell shell;
	public static String songName;
	public static String songSinger;
	public static String searchContent;
	// 李鑫  8.22
	public static  AudioStream as;
	public static boolean tflag;  // 控制音乐播放的图标 和播发音乐的状态
	public static String filePath; // 双击得到的歌曲的路径
	public static FileInputStream fileInputStream;//转歌曲的
	public static List<TableItem>list;// 得到你双击tableitem 的table的所有信息
	public static TableItem ti;// 表示你点击的tableitem
	public static boolean playflag=false;
	public static ProgressBar progressBar;
	public static boolean playingflag=true;// 控制滚动条的进行
	public static int number=0;
	public static boolean ff=false;//控制线程的结束
	public static ThreadTest tt=null;// 线程滚动条的进行

	
	public static int searchCount;
	public static List<String> songPath = new ArrayList<String>();
	public static String DownLoadPath = "C:\\Users\\sansheng__\\Desktop\\QQMusic";
	public static Label label;
	
	public static String delSongSheetName;
	/*************ck 8.24*********************/
	private static DBHelper db = new DBHelper();
	public static String ListTableName;
	/**********************************/
	
	public static void playSong(){
		
		number ++;
		if(number>1){
			a.progressBar.setSelection(0);
			a.progressBar.dispose();
			a.tt.stop();
		}
			String sql="select * from music where mmp3= ?";
			List<Object> params = new ArrayList<Object>();
			params.add(a.ti.getData("filepath").toString());
			List<Map<String,String>> songlist =help.findAll(sql, params);
			
			String songTime[]=songlist.get(0).get("MTIME").split(":");
			int time=Integer.parseInt(songTime[0])*60+Integer.parseInt(songTime[1]);
			
			if(a.as!=null){
				AudioPlayer.player.stop(a.as);
			}				
			try {
				
				a.fileInputStream=new FileInputStream(new File(a.ti.getData("filepath").toString()));
				
				try {
					a.as=new AudioStream(a.fileInputStream);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				//AudioPlayer.player.start(a.as);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
			
			if(number==1){
				a.tt=new ThreadTest(time);
			}else{

				ProgressBar progressBar = new ProgressBar(a.composite_4, SWT.NONE);
				progressBar.setBounds(239, 33, 668, 4);
				a.progressBar=progressBar;
				a.tt=new ThreadTest(time);
			}
			a.tt.start();
			
			AudioPlayer.player.start(a.as);

			label.setImage(SWTResourceManager.getImage(MusicMainUi.class, "/images/zt.png"));
			//用路径查数据库得到音乐的名字和歌手
			/*************ck 8.24*********************/
			String sql1 = "select msong,msinger,mtime from music where mmp3 = ?";
			List<Object> params1 = new ArrayList<Object>();
			params1.add(a.ti.getData("filepath").toString());
			List<Map<String, String>> list = db.findAll(sql1, params1);
			if(list.size() > 0){
				MusicMainUi.lblNewLabel_9.setText(list.get(0).get("MSONG"));
				MusicMainUi.label_5.setText(list.get(0).get("MSINGER"));
				MusicMainUi.lblNewLabel_7.setText(list.get(0).get("MTIME"));
			}
			/**********************************/
	}
	
	//播放下首音乐
		public static  void playNewSong(){
			if(Integer.parseInt(a.ti.getText(0).toString())==(a.list.size())){
				if(a.as!=null){
					AudioPlayer.player.stop(a.as);
				}						
				try {
					a.fileInputStream=new FileInputStream
							(new File(a.list.get(0).getData("filepath").toString()));
					
					try {
						a.as=new AudioStream(a.fileInputStream);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					AudioPlayer.player.start(a.as);
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
				a.ti=a.list.get(0);
				//用路径查数据库得到音乐的名字和歌手
				/*************ck 8.24*********************/
				String sql = "select msong,msinger,mtime from music where mmp3 = ?";
				List<Object> params = new ArrayList<Object>();
				params.add(a.list.get(0).getData("filepath").toString());
				List<Map<String, String>> list = db.findAll(sql, params);
				if(list.size() > 0){
					MusicMainUi.lblNewLabel_9.setText(list.get(0).get("MSONG"));
					MusicMainUi.label_5.setText(list.get(0).get("MSINGER"));
					MusicMainUi.lblNewLabel_7.setText(list.get(0).get("MTIME"));
				}
				/**********************************/
			    	return;
			  }
			int index=Integer.parseInt(a.ti.getText(0).toString());
			a.ti=a.list.get(index);
			
			if(a.as!=null){
				AudioPlayer.player.stop(a.as);
			}				
		//	if(ti.getData("file"))				
			try {

				a.fileInputStream=new FileInputStream
						(new File(a.list.get(index).getData("filepath").toString()));
				try {
					a.as=new AudioStream(a.fileInputStream);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				AudioPlayer.player.start(a.as);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
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
	
}

