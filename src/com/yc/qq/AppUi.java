package com.yc.qq;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ck.read.MainUi;
import com.ck.utils.MyUtils;
import com.ck.utils.a;
import com.yc.music.MusicMainUi;

import org.eclipse.swt.widgets.Composite;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AppUi {

	protected Shell shlQq;
	private MyUtils mu=new MyUtils();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppUi window = new AppUi();
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
		shlQq.open();
		shlQq.layout();
		while (!shlQq.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlQq = new Shell();
		shlQq.setImage(SWTResourceManager.getImage(AppUi.class, "/images/qq.png"));
		shlQq.setSize(482, 353);
		shlQq.setText("QQ\u5E94\u7528");
		shlQq.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		mu.centerShell(shlQq);
		
		Composite composite = new Composite(shlQq, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		composite.setLayout(null);
		
		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(24, 32, 71, 70);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);		
		tltmNewItem.setImage(SWTResourceManager.getImage(AppUi.class, "/images/\u8D2A\u5403\u86C7.png"));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(29, 106, 61, 17);
		lblNewLabel.setText("\u8D2A\u5403\u86C7");
		
		ToolBar toolBar_1 = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBounds(185, 32, 71, 70);
		
		ToolItem toolItem = new ToolItem(toolBar_1, SWT.NONE);		
		toolItem.setImage(SWTResourceManager.getImage(AppUi.class, "/images/QQ\u9605\u8BFB.png"));
		
		Label lblQq = new Label(composite, SWT.NONE);
		lblQq.setText("QQ\u9605\u8BFB");
		lblQq.setAlignment(SWT.CENTER);
		lblQq.setBounds(190, 106, 61, 17);
		
		ToolBar toolBar_2 = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setBounds(333, 32, 71, 70);
		
		ToolItem toolItem_1 = new ToolItem(toolBar_2, SWT.NONE);	
		toolItem_1.setImage(SWTResourceManager.getImage(AppUi.class, "/images/QQ\u97F3\u4E50.png"));
		
		Label lblQq_1 = new Label(composite, SWT.NONE);
		lblQq_1.setText("QQ\u97F3\u4E50");
		lblQq_1.setAlignment(SWT.CENTER);
		lblQq_1.setBounds(338, 106, 61, 17);
		
		/**
		 * Ã∞≥‘…ﬂ
		 */
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File f = new File("E:/C++code/snake_online/bin/Debug/snake_online.exe");
				if(f.isFile()){
					try {
						Desktop.getDesktop().open(f);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else{
					mu.alert(shlQq, "Ã· æ–≈œ¢", "”Œœ∑º”‘ÿ¥ÌŒÛ");
				}
			}
		});
		
		/**
		 * QQ‘ƒ∂¡
		 */
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainUi read = new MainUi();
				read.open();
			}
		});
		
		/**
		 * QQ“Ù¿÷
		 */
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MusicMainUi mmu = new MusicMainUi();
				mmu.open();
			}
		});
	}
}
