package test;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class testA extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public testA(Composite parent, int style) {
		super(parent, style);
		
		Label lblA = new Label(this, SWT.NONE);
		lblA.setBounds(239, 139, 61, 17);
		lblA.setText("a");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
