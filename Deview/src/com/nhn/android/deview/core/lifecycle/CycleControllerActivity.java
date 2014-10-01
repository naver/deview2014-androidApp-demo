package com.nhn.android.deview.core.lifecycle;


import com.nhn.android.deview.core.view.ViewMapper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class CycleControllerActivity extends FragmentActivity {
	protected CycleController mLFContoller = new CycleController();
	
	public CycleController getCycleController() {
		return mLFContoller;
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	public void setContentView(int layoutResID,boolean isViewMap) {
		if (isViewMap == true) {
			setContentView(layoutResID);			
			ViewMapper.mapLayout(this, getWindow().getDecorView());
		} else {
			setContentView(layoutResID);
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		mLFContoller.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mLFContoller.onPause();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		mLFContoller.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLFContoller.finish();
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		mLFContoller.onActivityResult();
	}
}
