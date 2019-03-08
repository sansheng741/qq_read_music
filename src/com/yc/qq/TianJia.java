package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.ck.bean.QQ;
import com.ck.dao.Helper;
import com.ck.utils.MyUtils;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wb.swt.SWTResourceManager;

public class TianJia {
	//添加成功了 添加的qq号 和 把这个人放在那个分组   如果那个人同意了 就 在吧这个信息插入到数据库  好友table中 
	static String qname1;
	static String groupname;
	
	private Helper help=new Helper();
	private MyUtils mu=new MyUtils();

	protected Shell shell;
	private Text text;
	private Text text_1;
	private boolean tflag=true;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TianJia window = new TianJia();
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
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setBackgroundImage(SWTResourceManager.getImage(TianJia.class, "/images/\u7ED8\u753B.png"));
		shell.setImage(SWTResourceManager.getImage(TianJia.class, "/images/qq.png"));
		shell.setSize(576, 407);
		shell.setText("\u597D\u53CB\u67E5\u627E");
		
		mu.centerShell(shell);
		
		text = new Text(shell, SWT.BORDER);
		
		text.setBounds(51, 41, 301, 26);
		
		Label lblNewLabel_2 = new Label(shell, SWT.WRAP);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_2.setBounds(436, 41, 116, 43);
		lblNewLabel_2.setText("");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setBounds(353, 41, 80, 26);
		btnNewButton.setText("\u67E5\u627E");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 107, 382, 261);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(62, 67, 152, 142);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(233, 66, 95, 23);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		
		btnNewButton_1.setBounds(292, 156, 80, 27);
		btnNewButton_1.setText("\u6DFB\u52A0");
		
		Label lblNewLabel_1 = new Label(composite, SWT.WRAP);
		lblNewLabel_1.setBounds(233, 96, 139, 54);
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_4.setBounds(251, 189, 121, 17);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(382, 107, 168, 261);
		
		Tree tree = new Tree(composite_1, SWT.BORDER);
		
		tree.setBounds(0, 0, 168, 251);
           text.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				if(text.getText()==null ||"".equals(text.getText())){
					tflag=true;
					tree.removeAll();
					lblNewLabel_2.setText("搜索账号不能为空");
				}
			}
	
			public void focusGained(FocusEvent e) {
				tree.removeAll();
				tflag=true;
				lblNewLabel_1.setText("");
				text_1.setText("");
				lblNewLabel.setImage(null);
				lblNewLabel_2.setText("");
				lblNewLabel_4.setText("");
			}
			});
           btnNewButton.addSelectionListener(new SelectionAdapter() {
   			
   			public void widgetSelected(SelectionEvent e) {
   				qname1=text.getText();
   				String sql="select * from qq where qid='"+qname1+"'";
   				List<QQ>list=help.findAllQQ(sql, null, 1);
   				if(list.size()==0){
   					lblNewLabel_2.setText("账号不存在");
   					tflag=true;
   				}else{
   					
   					int width,height;
					width=lblNewLabel.getBounds().width;
					height=lblNewLabel.getBounds().height;
					InputStream is=new ByteArrayInputStream(list.get(0).qimage);
					ImageData id=new ImageData(is);
					id=id.scaledTo(width, height);
					Image image=new Image(null,id);
					lblNewLabel.setImage(image);
					text_1.setText(list.get(0).qname);
					lblNewLabel_1.setText(list.get(0).motto);
					tflag=false;
   				}
   				
   			}
   		});
           btnNewButton_1.addSelectionListener(new SelectionAdapter() {
      			@Override
      			//添加好友    将存在的好友分组显示出来  放在composite_1的tree 中
      			public void widgetSelected(SelectionEvent e) {
      				
      				
      				
      				tree.removeAll();
      				String sqlt="select * from qfriend where qid1=? and qid2=?";
      				List<Object> params=new ArrayList<Object>();
      				params.add(QQDL.qid);
      				params.add(text.getText());
      				List<Map<String,String>> listt=help.findAll(sqlt, params);
      				if(listt.size()>0){
      					
      					lblNewLabel_4.setText("已经在好友列表");
      					tflag=true;
      					return ;
      				}
      				
      				
      				
      				if(tflag)
      					return ;
      				

      				String sql="select * from qfriend where qid1='"+QQDL.qid+"'";
      				
      				List<Map<String,String>> list=help.findAll(sql, null);
      				boolean flag;
      					ArrayList<String>arr=new ArrayList<String>();//保存列名 
      					for(int i=0;i<list.size();i++){
      						flag=true;
      						for(int j=0;j<arr.size();j++){
      							if(list.get(i).get("GROUPNAME").equals(arr.get(j))){
      								flag=false;
      								break;
      							}
      						}
      						if(flag){
      							arr.add(list.get(i).get("GROUPNAME"));
      						}
      						
      					}
      						for(int i=0;i<arr.size();i++){
      							TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
      							trtmNewTreeitem_2.setText(arr.get(i));
      				}
      			}
      		});
           
           tree.addSelectionListener(new SelectionAdapter() {
   			//树根的点击事件
   			public void widgetSelected(SelectionEvent e) {
   				/**
   				 *    qid int ,
					  groupname varchar2(16),
					  qname  varchar2(11),--qq好友二
					  beizu varchar2(16),
					  uname varchar2(20),
					  liyou varchar2(36),
					  flag int
   				 */
   				TreeItem ti= (TreeItem) e.item;
   				
   				String qid=text.getText();// qq好
   			    groupname=ti.getText();//获取点击的组名
   			    TiShi sh=new TiShi();
   			    sh.open();
   			    String liyou=sh.atext;
   			    if(liyou==null || "".equals(liyou)){
   			    	return ;
   			    }
   			 
   			    String sql="insert into getfriend values(?,?,?,?)";
   			    List<Object> params=new ArrayList<Object>();
   			    params.add(QQDL.qid);
   			    params.add(qid);
   			    params.add(groupname);
   			    params.add(liyou);
   			    
   			    help.doUpdate(sql, params);
   			   // params.add()
   				/*mu.alert(shell, "提示信息", "添加成功");*/
   				shell.dispose();
   			}
   		});

	}
}
