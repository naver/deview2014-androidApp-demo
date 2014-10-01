package com.nhn.android.deview;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.Preference;
import android.util.DisplayMetrics;

public class AppContext {
	public static final String PREF_KEY = "deview";
	
	public static Context getContext() {
		return DeViewApplication.mInstance;
	}
	
	public static SQLiteDatabase getDB() {
		return DeViewApplication.mInstance.mDB.mDB;
	}
	
	/* AppContext Storage: Data Transfer  */
	public static void putValue(Class<?> cls,Object value) {
		String key = cls.getName();
		 DeViewApplication.mInstance.saveObject(key, value);
	}
	
	public static<T> T getValue(Class<?> cls,boolean remove) {
		String key = cls.getName();
		T t = (T)DeViewApplication.mInstance.loadObject(key,remove);	 
		return t;
	}
	//TO store key-values
	public static void putValue(String key,Object value) {
		 DeViewApplication.mInstance.saveObject(key, value);
	}
	
	public static<T> T getValue(String key,boolean remove) {
		T t = (T)DeViewApplication.mInstance.loadObject(key,remove);	 
		return t;
	}
	
	//Shared Preference 
	public static SharedPreferences getAppPref() {
		SharedPreferences pref = getContext().getSharedPreferences(PREF_KEY, 0);
		return pref;
	}

	//To redirect  to UI Thread
	public static void post(Runnable r) {
		DeViewApplication.mInstance.mHandler.post(r);
	}
	
	public static void postDelayed(Runnable r,int millis) {
		DeViewApplication.mInstance.mHandler.postDelayed(r,millis);
	}
	
	// UI Conversion
	public static int dp2px(float dp) {
		return (int)(DeViewApplication.mInstance.mDesity * dp);
	}
	
	public static int getScreenWidth() {
		return DeViewApplication.mInstance.mDisplay.getWidth();
	}
}
