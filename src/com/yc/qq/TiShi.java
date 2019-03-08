package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class TiShi {
    static String atext;
	protected Shell shell;
	private Text text;
	private Button btnNewButton;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TiShi window = new TiShi();
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
		shell.setImage(SWTResourceManager.getImage(TiShi.class, "/images/qq.png"));
		shell.setSize(305, 174);
		shell.setText(" \u63D0\u793A");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x/2, Display.getCurrent().getClientArea().height / 2 - shell.getSize().y/2);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		
		text = new Text(composite, SWT.NONE);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text.setBounds(20, 52, 247, 27);
		atext="";
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(20, 17, 136, 29);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u76F8\u5E94\u7684\u5185\u5BB9");
		
		btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atext=text.getText();
				shell.dispose();
			}
		});
		btnNewButton.setBounds(199, 98, 80, 27);
		btnNewButton.setText("\u786E\u8BA4");

	}
}
