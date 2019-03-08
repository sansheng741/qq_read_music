package com.yc.qq;

import java.awt.MouseInfo;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.bean.QQ;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class DelFriend {

	protected Shell shell;
	private Helper help=new Helper();
	private String qid;
	private MyUtils mu=new MyUtils();
	private Boolean dargFlag = false;
	private int x,y; //鼠标点击时的坐标
	public DelFriend(String qid){
		this.qid=qid;
	}
	public DelFriend(){
		
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DelFriend window = new DelFriend();
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
		shell.setSize(338, 186);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shell);
		
		String sql="select * from qq where qid = ?";
		List<Object>params=new ArrayList<Object>();
		params.add(qid);
		List<QQ>list=help.findAllQQ(sql, params, 0);
		
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackground(SWTResourceManager.getColor(0, 191, 255));
		
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setBounds(0, -1, 32, 29);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(DelFriend.class, "/images/qqlog2.png"));
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setBounds(32, 7, 53, 17);
		lblNewLabel_4.setText("\u5220\u9664\u597D\u53CB");
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);				
		lblNewLabel_5.setImage(SWTResourceManager.getImage(DelFriend.class, "/images/\u53C9 (1).png"));
		lblNewLabel_5.setBounds(304, -1, 32, 29);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_2, SWT.WRAP);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 10, 316, 32);
		lblNewLabel.setText("\u5220\u9664\u540E\u4F60\u5C06\u4ECE\u5BF9\u65B9\u8054\u7CFB\u4EBA\u5217\u8868\u4E2D\u6D88\u5931\uFF0C\u4E14\u4EE5\u540E\u4E0D\u518D\u63A5\u53D7\u6B64\u4EBA\u7684\u4F1A\u8BDD\u6D88\u606F\u3002");
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setBounds(20, 48, 70, 60);
		int height=lblNewLabel_1.getBounds().height;
		int width=lblNewLabel_1.getBounds().width;
		
		InputStream is= new ByteArrayInputStream(list.get(0).qimage);
		ImageData id=new ImageData(is);
		id=id.scaledTo(width, height);
		Image image=new Image(null,id);
		lblNewLabel_1.setImage(image);
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(107, 59, 168, 32);
		lblNewLabel_2.setText(list.get(0).qname+"  ("+list.get(0).qid + ")");
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button button = new Button(composite_3, SWT.NONE);
		
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setBounds(177, 3, 70, 25);
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(composite_3, SWT.NONE);
		
		button_1.setText("\u53D6\u6D88");
		button_1.setBounds(253, 3, 70, 25);
		sashForm.setWeights(new int[] {29, 117, 32});
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
		 * 关闭
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		/**
		 * 关闭 - 鼠标放上去变色
		 */
		lblNewLabel_5.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				lblNewLabel_5.setImage(SWTResourceManager.getImage(DelFriend.class, "/images/叉 (2) .png"));				
			}
			@Override
			public void mouseExit(MouseEvent e) {
				lblNewLabel_5.setImage(SWTResourceManager.getImage(DelFriend.class, "/images/\u53C9 (1).png"));
			}
		});
		/**
		 * 关闭窗口
		 */
		
		//点击确定删除
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sql3="delete qfriend where qid1=? and qid2=?";
				List<Object> params3=new ArrayList<Object>();
				params3.add(QQDL.qid);
				params3.add(qid);
				help.doUpdate(sql3, params3);
				
				String sql4="delete qfriend where qid1=? and qid2=?";
				params3.clear();
				params3.add(qid);
				params3.add(QQDL.qid);
				help.doUpdate(sql4, params3);
				
				shell.dispose();
			}
		});
		
		
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
	}
}
