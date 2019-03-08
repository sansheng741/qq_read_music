package com.yc.music;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;

import org.eclipse.swt.layout.FillLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddSong {

	protected Shell shell;
	private Table table;
	private MyUtils mu=new MyUtils();
	private String filePath = null;
	private DBHelper db = new DBHelper();
	private File[] fList;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddSong window = new AddSong();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(AddSong.class, "/images/QQ\u97F3\u4E501.png"));
		shell.setSize(882, 530);
		shell.setText("\u6DFB\u52A0\u6B4C\u66F2");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);	
		btnNewButton.setBounds(245, 29, 80, 27);
		btnNewButton.setText("\u9009\u62E9\u97F3\u4E50\u76EE\u5F55");
		
		Button btnmv = new Button(composite_1, SWT.NONE);				
		btnmv.setText("\u9009\u62E9Mv\u76EE\u5F55");
		btnmv.setBounds(504, 29, 80, 27);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(55);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(221);
		tblclmnNewColumn_1.setText("\u6B4C\u540D");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(141);
		tblclmnNewColumn_2.setText("\u6B4C\u624B");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("\u4E13\u8F91");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("\u65F6\u957F");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("\u5927\u5C0F");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(124);
		tblclmnNewColumn_6.setText("Mv");
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button button = new Button(composite_3, SWT.NONE);		
		button.setText("\u786E\u8BA4\u6DFB\u52A0");
		button.setBounds(388, 20, 80, 27);
		sashForm.setWeights(new int[] {78, 348, 59});
		/**
		 * ѡ������
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//ͳ�Ƹ�����Ŀ
				int count = 1;
				DirectoryDialog dd = new  DirectoryDialog(shell);
				//�����ļ��Ի���ı���
				dd.setText("�ļ�ѡ��");
				//���öԻ�����ʾ�ı���Ϣ
				dd.setMessage("��ѡ����Ӧ���ļ���");					
				filePath = dd.open();	
				
				//���ʸ�·���µ������ļ�
				if(filePath != null && !"".equals(filePath)){
					File file = new File(filePath);
					fList = file.listFiles();
					for(int i = 0; i < fList.length; i++){
						if(fList[i].isFile()){							
							File f = new  File(fList[i].toString());
							String[] info = f.getName().split("-");
							//�ж��ļ���ʽ
							String fileType = info[1].substring(info[1].indexOf("."));
							if(".mp3".equals(fileType)){								
								String[] SongInfo = getSongInfo(fList[i].toString());
								//�ļ���С
								String SongLength = f.length()/1024.0/1024 + "";
								SongLength = trimSongLength(SongLength);
								String sql = "select * from music where msong = ? and msinger = ?";
								List<Object> params = new ArrayList<Object>();
								params.add(SongInfo[0]);
								params.add(SongInfo[1]);
								List<Map<String, String>> list = db.findAll(sql, params);
								if(list.size() <= 0){
									TableItem ti = new TableItem(table,SWT.NONE);
									ti.setText(new String[]{count++ +"",SongInfo[0],SongInfo[1],
											SongInfo[2],SongInfo[3],SongLength+"M"});
									ti.setData("filepath",fList[i].toString() );
								}								
							}					
						}
					}					
				}
			}			
		});	
		/**
		 * ȷ�����
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(fList !=null && !"".equals(fList) && fList.length > 0){
					for(int i = 0; i < fList.length; i++){
						if(fList[i].isFile()){							
							File f = new  File(fList[i].toString());
							String[] info = f.getName().split("-");
							//�ж��ļ���ʽ
							String fileType = info[1].substring(info[1].indexOf("."));
							if(".mp3".equals(fileType)){
								info[1] = info[1].substring(0,info[1].indexOf("."));						
								String SongLength = f.length()/1024.0/1024 + "";
								SongLength = trimSongLength(SongLength);
								//������
								String filePath1 = filePath;
								filePath1 += "\\" + f.getName();
								String[] SongInfo = getSongInfo(fList[i].toString());
								String sql = "select * from music where msong = ? and msinger = ?";
								List<Object> params = new ArrayList<Object>();
								params.add(SongInfo[0]);
								params.add(SongInfo[1]);
								List<Map<String, String>> list = db.findAll(sql, params);
								if(list.size() <= 0){
									//������ݿ�
									sql = "insert into music values(seq_mid.nextval,?,?,?,?,?,'',?,'')";
									params.clear();
									params.add(SongInfo[0]);  				//����
									params.add(SongInfo[1]);				//����
									params.add(SongInfo[2]);				//ר��
									params.add(SongInfo[3]);				//ʱ��
									params.add(SongLength+"M");				//��С			
									params.add(filePath1);					//MP3
									int result = db.doUpdate(sql, params);
									if(result <= 0){
										mu.alert(shell, "��ʾ��Ϣ", "���ʧ��");
										break;
									}
								}	
							}							
						}						
					}
					mu.alert(shell, "��ʾ��Ϣ", "��ӳɹ�");
					shell.dispose();
				}else{
					mu.alert(shell, "��ʾ��Ϣ", "������Ӹ���");
				}							
			}
		});
	}

	protected String trimSongLength(String songLength) {
		songLength = songLength.substring(0,songLength.indexOf(".")+2);
		return songLength;
	}
	/**
	 * �õ�.mp3�ļ���Ϣ
	 * @param mp3Path  ·��
	 * @return
	 */
	private String[] getSongInfo(String mp3Path){
		String[] info = new String[4];
		String singer = "δ֪������";
		String author = "δ֪����";
		String songName = "δ֪ר��";
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
				info[0] = "δ֪������";
			}
			try {
				info[1] = singer.substring(6, singer.length() - 3);
			} catch (Exception e) {
				info[1] = "δ֪����";
			}
			try {
				info[2] = author.substring(6, author.length() - 3);
			} catch (Exception e) {
				info[2] = "δ֪ר��";
			}
			info[3] = getSongTime(mp3Path);   //ʱ��
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

            // ��λΪ��
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