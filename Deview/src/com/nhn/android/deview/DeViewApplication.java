package com.nhn.android.deview;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.nhn.android.deview.dao.MySchedule;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class DeViewApplication extends Application {
	public final String PROGRAM = "program";
	public final String MYSCHEDULE = "myschedule";
	
	HashMap<String, Object> mStorage = new HashMap<String, Object>();
	
	MySchedule mMySchedule = new MySchedule();
	MainDB mDB = null;
	public Handler mHandler = new Handler();
	
	public static DeViewApplication mInstance= null;
	
	public Display mDisplay;
	public float mDesity;
	
	@Override
	public void onCreate() {
		mInstance = this;		
		super.onCreate();
		mDB = new MainDB(this);
		mDB.init();
		
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		mDisplay = wm.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		mDisplay.getMetrics(outMetrics);
		mDesity = outMetrics.density;
	}
	
	@Override
	public Object getSystemService(String name) {
		if (name.equals(MYSCHEDULE) == true) {
			return  mMySchedule;
		}
		return super.getSystemService(name);
	}
	
	public void saveObject(String key,Object value) {
		mStorage.put(key, value);		
	}
	
	public Object loadObject(String key,boolean remove) {
		Object value = null;	
		if (remove == true) {
			value = mStorage.remove(key);
		} else { 
			value = mStorage.get(key);
		}
		return value;
	}

	
	final BroadcastReceiver mSystemReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
				
		}
		
	};
	
	
	
	public void onConfigurationChanged(android.content.res.Configuration newConfig) {
		
	};
	
}
