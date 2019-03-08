package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.utils.a;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Search extends Composite {
	private StackLayout stackLayout = new StackLayout();
	public Label lblNewLabel_1;
	public SearchSong searchSong;
	public Label lblNewLabel_2;
	//898 * 580
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Search(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel.setText("\u641C\u7D22");
		lblNewLabel.setBounds(36, 23, 36, 20);
		
		lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		lblNewLabel_1.setBounds(74, 26, 61, 17);

		lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(36, 57, 121, 20);
		lblNewLabel_2.setText("\u627E\u5230xx");
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);		
		lblNewLabel_3.setImage(SWTResourceManager.getImage(Search.class, "/images/1b.jpg"));
		lblNewLabel_3.setBounds(163, 74, 103, 44);
		
		Label label = new Label(composite, SWT.NONE);		
		label.setImage(SWTResourceManager.getImage(Search.class, "/images/2a.jpg"));
		label.setBounds(308, 72, 103, 44);
		
		Label label_1 = new Label(composite, SWT.NONE);		
		label_1.setImage(SWTResourceManager.getImage(Search.class, "/images/3a.jpg"));
		label_1.setBounds(438, 74, 103, 44);
		
		Label label_2 = new Label(composite, SWT.NONE);		
		label_2.setImage(SWTResourceManager.getImage(Search.class, "/images/4a.jpg"));
		label_2.setBounds(576, 72, 103, 44);
		
		Label label_3 = new Label(composite, SWT.NONE);		
		label_3.setImage(SWTResourceManager.getImage(Search.class, "/images/5a.jpg"));
		label_3.setBounds(726, 74, 103, 44);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {114, 463});
		composite_1.setLayout(stackLayout);
		
		//单曲
		searchSong = new SearchSong(composite_1,SWT.NONE);
		stackLayout.topControl = searchSong;
		//歌手
		SearchSinger searchSinger = new SearchSinger(composite_1,SWT.NONE);
		//专辑
		SearchAlbum searchAlbum = new SearchAlbum(composite_1,SWT.NONE);
		//视频
		SearchMv searchMv = new SearchMv(composite_1,SWT.NONE);
		//歌单
		SrearchSongSheet srearchSongSheet = new SrearchSongSheet(composite_1,SWT.NONE);

		/**
		 * 单曲
		 */
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_3.setImage(SWTResourceManager.getImage(Search.class, "/images/1b.jpg"));
				label.setImage(SWTResourceManager.getImage(Search.class, "/images/2a.jpg"));
				label_1.setImage(SWTResourceManager.getImage(Search.class, "/images/3a.jpg"));
				label_2.setImage(SWTResourceManager.getImage(Search.class, "/images/4a.jpg"));
				label_3.setImage(SWTResourceManager.getImage(Search.class, "/images/5a.jpg"));
				
				//更换面板
				searchSong.setVisible(true);
				searchSinger.setVisible(false);
				searchAlbum.setVisible(false);
				searchMv.setVisible(false);
				srearchSongSheet.setVisible(false);
				//显示数据				
				searchSong.showTable();
				lblNewLabel_2.setText("找到" + a.searchCount + "首单曲");
			}
		});
		/**
		 * 歌手
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_3.setImage(SWTResourceManager.getImage(Search.class, "/images/1a.jpg"));
				label.setImage(SWTResourceManager.getImage(Search.class, "/images/2b.jpg"));
				label_1.setImage(SWTResourceManager.getImage(Search.class, "/images/3a.jpg"));
				label_2.setImage(SWTResourceManager.getImage(Search.class, "/images/4a.jpg"));
				label_3.setImage(SWTResourceManager.getImage(Search.class, "/images/5a.jpg"));
				
				//更换面板
				searchSong.setVisible(false);
				searchSinger.setVisible(true);
				searchAlbum.setVisible(false);
				searchMv.setVisible(false);
				srearchSongSheet.setVisible(false);
				//显示数据		
				searchSinger.showTable();
				lblNewLabel_2.setText("找到" + a.searchCount + "位歌手");
				
			}
		});
		/**
		 * 专辑
		 */
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_3.setImage(SWTResourceManager.getImage(Search.class, "/images/1a.jpg"));
				label.setImage(SWTResourceManager.getImage(Search.class, "/images/2a.jpg"));
				label_1.setImage(SWTResourceManager.getImage(Search.class, "/images/3b.jpg"));
				label_2.setImage(SWTResourceManager.getImage(Search.class, "/images/4a.jpg"));
				label_3.setImage(SWTResourceManager.getImage(Search.class, "/images/5a.jpg"));
				
				//更换面板
				searchSong.setVisible(false);
				searchSinger.setVisible(false);
				searchAlbum.setVisible(true);
				searchMv.setVisible(false);
				srearchSongSheet.setVisible(false);
				
				//显示数据		
				searchAlbum.showTable();
				lblNewLabel_2.setText("找到" + a.searchCount + "张专辑");
			}
		});
		/**
		 * 视频
		 */
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_3.setImage(SWTResourceManager.getImage(Search.class, "/images/1a.jpg"));
				label.setImage(SWTResourceManager.getImage(Search.class, "/images/2a.jpg"));
				label_1.setImage(SWTResourceManager.getImage(Search.class, "/images/3a.jpg"));
				label_2.setImage(SWTResourceManager.getImage(Search.class, "/images/4b.jpg"));
				label_3.setImage(SWTResourceManager.getImage(Search.class, "/images/5a.jpg"));
				
				//更换面板
				searchSong.setVisible(false);
				searchSinger.setVisible(false);
				searchAlbum.setVisible(false);
				searchMv.setVisible(true);
				srearchSongSheet.setVisible(false);
				
				lblNewLabel_2.setText("找到" + 0 + "个视频");
			}
		});
		/**
		 * 歌单
		 */
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//更换图片
				lblNewLabel_3.setImage(SWTResourceManager.getImage(Search.class, "/images/1a.jpg"));
				label.setImage(SWTResourceManager.getImage(Search.class, "/images/2a.jpg"));
				label_1.setImage(SWTResourceManager.getImage(Search.class, "/images/3a.jpg"));
				label_2.setImage(SWTResourceManager.getImage(Search.class, "/images/4a.jpg"));
				label_3.setImage(SWTResourceManager.getImage(Search.class, "/images/5b.jpg"));
				
				//更换面板
				searchSong.setVisible(false);
				searchSinger.setVisible(false);
				searchAlbum.setVisible(false);
				searchMv.setVisible(false);
				srearchSongSheet.setVisible(true);
				
				//显示数据		
				srearchSongSheet.showTable();
				lblNewLabel_2.setText("找到" + a.searchCount + "个歌单");
			}
		});
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
