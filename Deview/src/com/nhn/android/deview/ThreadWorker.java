package com.nhn.android.deview;

public class ThreadWorker<T> implements Runnable {
	T objParam;
	Runnable runnable;
	public ThreadWorker(AppContext conext,T objparam,Runnable r) {
		this.objParam = objparam;	
	}
	
	@Override
	public void run() {
		runnable.run();
	}

}
