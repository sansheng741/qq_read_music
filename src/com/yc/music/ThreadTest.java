package com.yc.music;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;

import com.ck.utils.a;

public class ThreadTest extends Thread {
	public ReentrantLock r=new ReentrantLock();
	public Condition c=r.newCondition();
	private ProgressBar progressBar;
	public int i;
	private int time;
	public ThreadTest(int time){
		this.time=time;
		a.progressBar.setMaximum(time);
	}
	public ThreadTest(){
		
	}
	public void run(){
		r.lock();
		for(i=1;i<=time;){
			if(a.ff){
				break;
			}
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
				Display.getDefault().asyncExec(new Runnable(){
					
					public void run(){
						
						if(a.playingflag){
							a.progressBar.setSelection(i);
							i++;
						}
					}
				});	
		}
		r.unlock();
	}
}
