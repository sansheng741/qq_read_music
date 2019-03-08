package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ck.dao.Helper;

import org.eclipse.swt.widgets.Label;

public class OldXiaoXi {

	protected Shell shell;
	private ScrolledComposite sc;
	private Text txtSfsfsfsdfsdfsdfs;
	private Composite composite_1;
	//private Text text;
	private int y=0;
	private Text text_1;
	private String qid;
	private Helper help=new Helper();
	private String qname;
	private String beizu;

	/**
	 * Launch the application.
	 * @param args
	 */
	public OldXiaoXi(String qid,String qname,String beizu){
		this.qid=qid;
		this.qname=qname;
		this.beizu=beizu;
	}
	public OldXiaoXi(){
		
	}
	
	
	public static void main(String[] args) {
		try {
			OldXiaoXi window = new OldXiaoXi();
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
		shell.setSize(610, 396);
		shell.setText("\u804A\u5929\u8BB0\u5F55");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		sc=new ScrolledComposite(composite,SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		composite_1 = new Composite(sc, SWT.NONE);
		
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from qcontent where qid1 =? and qid2=?  or  qid1 =? and qid2=? order by qcid";
		params.add(qid);
		params.add(QQDL.qid);

		params.add(QQDL.qid);
		params.add(qid);

		List<Map<String, String>> list = help.findAll(sql, params);
		/**
		 * 
		 * for (; start < list.size(); start++) {
		
			
			if(list.get(start).get("QID1").equals(QQDL.qid)){// 代表你发给好友的
				String at="select q.qname from qq q where qid='"+QQDL.qid+"'";
				List<Map<String,String>>listqq=help.findAll(at, null);
				
				txtn.append(listqq.get(0).get("QNAME")+ " " + list.get(start).get("QDATE")+"\n");
				
			}else{
				if (beizu == null || "".equals(beizu)) {

					txtn.append( qname + " " + list.get(start).get("QDATE")+"\n");
				} else {

					txtn.append( beizu + " " + list.get(start).get("QDATE")+"\n");
				}
			}
			txtn.append( list.get(start).get("TEXT")+"\n");
		}
		 */
		
		for(int i=0;i<list.size();i++){
			Text text=new Text(composite_1, SWT.WRAP|SWT.MULTI);
			text.setBounds(0, 0+43*y++, 573, 43);
			text.setEnabled(false);
			if(list.get(i).get("QID1").equals(QQDL.qid)){// 代表你发给好友的
				String at="select q.qname from qq q where qid='"+QQDL.qid+"'";
				List<Map<String,String>>listqq=help.findAll(at, null);
				
				text.append(listqq.get(0).get("QNAME")+ " " + list.get(i).get("QDATE")+"\n");
			}else{
				if (beizu == null || "".equals(beizu)) {

					text.append( qname + " " + list.get(i).get("QDATE")+"\n");
				} else {

					text.append( beizu + " " + list.get(i).get("QDATE")+"\n");
				}
				
			}
			text.append( list.get(i).get("TEXT")+"\n");
		}
		sc.setContent(composite_1);
		sc.setMinSize(new Point(573,list.size()*43));
	
		
		

	}
}
