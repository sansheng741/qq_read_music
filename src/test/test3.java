package test;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Label;

public class test3 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			test3 window = new test3();
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
		shell.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\sansheng__\\Desktop\\TIM\u622A\u56FE20190228230144.png"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		RGB r = new RGB(0, 0, 0);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		shell.setSize(561, 385);
		shell.setText("SWT Application");
		
		Label lblAa = new Label(shell, SWT.NONE);
		lblAa.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAa.setBounds(129, 121, 61, 17);
		lblAa.setText("aa");

	}
}
