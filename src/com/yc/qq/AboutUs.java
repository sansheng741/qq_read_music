package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.utils.MyUtils;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AboutUs {

	protected Shell shlqq;
	private Text txtPortionsCopyright;
	private MyUtils mu = new MyUtils();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AboutUs window = new AboutUs();
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
		shlqq.open();
		shlqq.layout();
		while (!shlqq.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlqq = new Shell();
		shlqq.setImage(SWTResourceManager.getImage(AboutUs.class, "/images/qq.png"));
		shlqq.setSize(450, 300);
		shlqq.setText("\u5173\u4E8EQQ2018");
		shlqq.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shlqq);
		
		SashForm sashForm_1 = new SashForm(shlqq, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);
		
		SashForm sashForm = new SashForm(sashForm_1, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite, SWT.WRAP);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel.setText("QQ2018 1.10.20\r\n\u4E09\u751F\u516C\u53F8 \u7248\u6743\u6240\u6709\r\nCopyright\u00A9 2018-2020 Sansheng All Rights Reserved.\r\n");
		
		txtPortionsCopyright = new Text(sashForm, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		txtPortionsCopyright.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		txtPortionsCopyright.setText("Portions Copyright @ 2002-2018 Global IP Sound Inc. All rights\r\nreserved.\r\nGlobal IP Sound, GIPS, SoundWare, NetEQ, iPCM, iLBC, GIPS\r\nVoiceEngine, and associated design marks and logos are trademarksowned or used under license by Global IP Sound AB, and may beregistered in the United States and other countries.Patents and Patents Pending, Global IP Sound Inc.\r\nwww.globalipsound.com\r\n\r\n\r\n\u672C\u8F6F\u4EF6\u4E2D\u4F7F\u7528\u7684Purevoice SDK\u4E13\u5229\u6280\u672F\u53CA\u5176\u76F8\u5173\u5546\u6807\u4E3AQUALCOMMIncorporated\u6240\u6709.\r\nPatents and trademarks,QUAL COMM Incorporated.\r\n\u672C\u8F6F\u4EF6\u4E2D\u4F7F\u7528\u4E86SILK CODEC, SILK CODEC\u6240\u9075\u5FAA\u7684\u534F\u8BAE\u8BE6\u60C5\u89C1\u7528\u6237\u534F\r\n\u8BAE\u3002");
		sashForm.setWeights(new int[] {79, 179});
		
		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlqq.dispose();
			}
		});
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		btnNewButton.setBounds(341, 1, 80, 20);
		btnNewButton.setText("\u786E\u5B9A");
		sashForm_1.setWeights(new int[] {234, 24});

	}

}
