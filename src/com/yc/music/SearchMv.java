package com.yc.music;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

public class SearchMv extends Composite {
	//897 * 464
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchMv(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 897, 464);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(41);
		
		TableColumn tblclmnMv = new TableColumn(table, SWT.CENTER);
		tblclmnMv.setWidth(425);
		tblclmnMv.setText("MV");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(425);
		tableColumn_2.setText("\u6B4C\u624B");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
