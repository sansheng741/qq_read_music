package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.layout.FillLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import com.ck.dao.Helper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class LocationMusic extends Composite {
	//898 * 580
	private StackLayout stackLayout = new StackLayout();
	private Table table;
	private String filePath = null;
	private File[] fList;
	private MyUtils mu=new MyUtils();
	private Helper help=new Helper();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LocationMusic(Composite parent, int style,Shell shell) {
		super(parent, style);
		setLayout(stackLayout);
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 18, SWT.BOLD));
		lblNewLabel.setBounds(26, 20, 96, 31);
		lblNewLabel.setText("\u672C\u5730\u97F3\u4E50");
		
		Link link = new Link(composite, SWT.NONE);
		link.setBounds(171, 34, 53, 17);
		link.setText("<a>\u9009\u62E9\u76EE\u5F55</a>");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 17, SWT.BOLD));
		lblNewLabel_1.setText("\u8BF7\u6DFB\u52A0\u672C\u5730\u97F3\u4E50");
		lblNewLabel_1.setBounds(352, 127, 167, 38);
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);		
		lblNewLabel_2.setImage(SWTResourceManager.getImage(LocationMusic.class, "/images/\u9009\u62E9\u672C\u5730\u97F3\u4E50\u6587\u4EF6\u5939-1.png"));
		lblNewLabel_2.setBounds(321, 185, 239, 91);
		sashForm.setWeights(new int[] {138, 439});
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(57);
		tblclmnNewColumn.setText("\u7CFB\u5217");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(174);
		tblclmnNewColumn_1.setText("\u6B4C\u540D");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(130);
		tblclmnNewColumn_2.setText("\u6B4C\u624B");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(121);
		tblclmnNewColumn_3.setText("\u65F6\u957F");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(111);
		tblclmnNewColumn_4.setText("\u4E13\u8F91");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(85);
		tblclmnNewColumn_5.setText("\u5927\u5C0F");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("\u683C\u5F0F");
		
	
		
		
		stackLayout.topControl = sashForm;
		sashForm.setVisible(true);
		
		//表格的点击事件  8.22 李鑫
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
		 * 本地音乐
		 */
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				table.removeAll();
				int count=1;
				lblNewLabel_2.setImage(SWTResourceManager.getImage(LocationMusic.class, "/images/选择本地音乐文件夹-2.png"));				
				DirectoryDialog dd = new  DirectoryDialog(shell);
				//设置文件对话框的标题
				dd.setText("文件选择");
				//设置对话框提示文本信息
				dd.setMessage("请选择相应的文件夹");					
				filePath = dd.open();
				
				if(dd.getFilterPath()==null || "".equals(dd.getFilterPath())){
					sashForm.setVisible(true);
					composite_2.setVisible(false);
				}else{
					//判断选择的文件是否为单个的 mp3 文件
					filePath=dd.getFilterPath();
				
					File file=new File(filePath);
					fList=file.listFiles();

					if(fList !=null && !"".equals(fList) && fList.length > 0){
						for(int i = 0; i < fList.length; i++){
							if(fList[i].isFile()){							
								File f = new  File(fList[i].toString());	
								
								
								//判断文件格式
								String fileType = f.getName().substring(f.getName().lastIndexOf("."));
								String[] info = f.getName().split("-");
								if(info.length == 1){
									continue;
								}
								if(".mp3".equals(fileType)){
									String tfilepath =f.getAbsolutePath();
									
									info[1] = info[1].substring(0,info[1].lastIndexOf("."));						
									String SongLength = f.length()/1024.0/1024 + "";
									SongLength = trimSongLength(SongLength);
									//存音乐
									String filePath1 = filePath;
									filePath1 += "\\" + f.getName();
									String[] SongInfo = getSongInfo(fList[i].toString());
									TableItem tableItem = new TableItem(table, SWT.NONE);
									tableItem.setText(0, ""+count);
									count++;
									int j=0;
									for(;j<SongInfo.length;j++){
										tableItem.setText(j+1, SongInfo[j]);
										
									}
									tableItem.setText(++j,SongLength+"M");
									tableItem.setText(++j,"mp3");
									tableItem.setData("filepath", tfilepath);
								}							
							}						
						}
					}	
					sashForm.setVisible(false);
					composite_2.setVisible(true);
				}			
			}
			//Mouseup
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_2.setImage(SWTResourceManager.getImage(LocationMusic.class, "/images/选择本地音乐文件夹-1.png"));
			}
		});
	}
	
	
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected String trimSongLength(String songLength) {
		songLength = songLength.substring(0,songLength.indexOf(".")+2);
		return songLength;
	}
	/**
	 * 得到.mp3文件信息
	 * @param mp3Path  路径
	 * @return
	 */
	private String[] getSongInfo(String mp3Path){
		String[] info = new String[4];
		String singer = "未知歌曲名";
		String author = "未知歌手";
		String songName = "未知专辑";
		try {
			MP3File mp3File = new MP3File(mp3Path);
			AbstractID3v2Tag id3v2tag = mp3File.getID3v2Tag();
			
			 singer = id3v2tag.frameMap.get("TPE1").toString();
			 author = id3v2tag.frameMap.get("TALB").toString();
			 songName = id3v2tag.frameMap.get("TIT2").toString();
	
		} catch (Exception e) {
			
		} finally{
			try {
				info[0] = songName.substring(6, songName.length() - 3);
			} catch (Exception e) {
				info[0] = "未知歌曲名";
			}
			try {
				info[1] = singer.substring(6, singer.length() - 3);
			} catch (Exception e) {
				info[1] = "未知歌手";
			}
			try {
				info[2] = author.substring(6, author.length() - 3);
			} catch (Exception e) {
				info[2] = "未知专辑";
			}
			info[3] = getSongTime(mp3Path);   //时长
		}
		
		return info;
	}
	private String getSongTime(String path) {				
		int time = 0;
		int time1 = 0;
		String time3 = null;
        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(new File(path));
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();

            // 单位为秒
            time = audioHeader.getTrackLength();
            while(time >= 60){
            	time -=60;
            	time1++;
            }
            if(time1 < 10){
            	time3 = "0" + time1 + ":";
            }else{
            	time3 = time1 + ":";
            }		            
            if(time == 0){
            	time3 += "00";
            }else if(time < 10){
            	time3 += "0" + time;
            }else{
            	time3 += time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time3;
	}
}
