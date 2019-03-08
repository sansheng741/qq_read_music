package test;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class test {

	protected Shell shell;
	private StackLayout stacklayout = new StackLayout();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			test window = new test();
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
		shell.setSize(1024, 619);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		Button btnA = new Button(composite, SWT.NONE);
		
		btnA.setBounds(27, 91, 80, 27);
		btnA.setText("a");
		
		Button btnB = new Button(composite, SWT.NONE);
		
		btnB.setBounds(27, 197, 80, 27);
		btnB.setText("b");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {154, 851});
		
		//…Ë÷√∂—’ª≤ºæ÷
		composite_1.setLayout(stacklayout);
		testA testa = new testA(composite_1, SWT.NONE);
		testB testb = new testB(composite_1, SWT.NONE);
		
		btnA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				testa.setVisible(true);
				testb.setVisible(false);
			}
		});
		
		btnB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				testa.setVisible(false);
				testb.setVisible(true);
			}
		});

	}
}
