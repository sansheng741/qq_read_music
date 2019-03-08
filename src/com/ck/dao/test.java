package com.ck.dao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ProgressBar;

import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class test {

	protected Shell shell;
	private boolean flag = true;  //判断进度条的 状态
	private boolean closeFlag = true;  //关闭线程用
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
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				closeFlag = false;
			}
		});
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(100, 101, 234, 17);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setBounds(50, 189, 80, 27);
		btnNewButton.setText("\u64AD\u653E");
		
		Button button = new Button(shell, SWT.NONE);
		
		button.setText("\u6682\u505C"); 
		button.setBounds(274, 189, 80, 27);
		
		
		
		new Thread(new Runnable(){
			int i;
			@Override
			public void run() {
				for(i = 1; i <= 100 && closeFlag;){
					Display.getDefault().asyncExec(new Runnable(){
						@Override
						public void run() {
							if(flag){
								progressBar.setSelection(i);
								i++;
							}	
						}
					});	
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		/**
		 * 暂停
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flag = false;
			}
		});
		/**
		 * 播放
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flag = true;
			}
		});
	}
}
