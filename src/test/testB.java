package test;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class testB extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public testB(Composite parent, int style) {
		super(parent, style);
		
		Label lblB = new Label(this, SWT.NONE);
		lblB.setBounds(130, 143, 61, 17);
		lblB.setText("b");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
