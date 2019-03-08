package com.yc.music;

import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.dao.DBHelper;
import com.ck.utils.MyUtils;
import com.ck.utils.a;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddSongSheet {

	protected Shell shell;
	private Text text;
	private MyUtils mu=new MyUtils();
	private Boolean dargFlag = false;
	private int x,y; //�����ʱ������
	private DBHelper db = new DBHelper();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddSongSheet window = new AddSongSheet();
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
		shell.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/QQ\u97F3\u4E501.png"));
		shell.setSize(280, 244);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		//mu.centerShell(shell);
		mu.centerShell(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/\u4E3B\u754C\u9762\u6DFB\u52A0\u5206\u7C7B\u80CC\u666F.jpg"));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);		
		lblNewLabel.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/\u5173\u95ED-1.png"));
		lblNewLabel.setBounds(248, 15, 20, 20);
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text.setBounds(50, 98, 188, 34);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);		
		lblNewLabel_1.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/\u786E\u8BA41.png"));
		lblNewLabel_1.setBounds(46, 209, 82, 30);
		
		Label label = new Label(composite, SWT.NONE);
		
		label.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/\u53D6\u6D881.png"));
		label.setBounds(170, 209, 82, 30);
		
		//�����ק
		composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if( dargFlag ){
										//MouseInfo.getPointerInfo().getLocation().x ������������
										//x  �����������
										//�����õ���������������
					shell.setLocation(MouseInfo.getPointerInfo().getLocation().x - x,
							MouseInfo.getPointerInfo().getLocation().y - y	);
										//������ƶ�������������ĵ��ƶ�����
				}
			}
		});
		composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//��갴�£������ƶ�
				dargFlag = true;
				x = e.x;
				y = e.y;
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				//����ɿ��������ƶ�
				dargFlag = false;
			}
		});
		
		/**
		 * �ر�
		 */
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		/**
		 * ȡ��
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/ȡ��2.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/ȡ��1.png"));
				shell.dispose();
			}
		});
		/**
		 * ȷ��
		 */
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblNewLabel_1.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/ȷ��2.png"));			
			}
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_1.setImage(SWTResourceManager.getImage(AddSongSheet.class, "/images/ȷ��1.png"));
				String lname= text.getText();
				//�Ȳ����ݿ��ж��������û������赥
				String lsql = "select * from list where lname = ? and qid = ?";
				List<Object> params = new ArrayList<Object>();
				params.add(lname);
				params.add(a.qid);
				List<Map<String, String>> list = db.findAll(lsql, params);
				if(list.size() <= 0){
					lsql = "insert into list values(?,?,null)";
					int result = db.doUpdate(lsql, params);
					if(result>0){
						mu.alert(shell, "��ʾ��Ϣ", "��Ӹ赥�ɹ�");
						shell.dispose();
					}else{
						mu.alert(shell, "��ʾ��Ϣ", "��Ӹ赥ʧ��");
						shell.dispose();
					}
				}else{
					mu.alert(shell, "��ʾ��Ϣ", "�赥�Ѵ���");
				}				
			}
		});
	}
}
