package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class ManagerHome extends Composite {
	//942*525
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ManagerHome(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(ManagerHome.class, "/images/QQ\u97F3\u4E50\u80CC\u666F.png"));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
