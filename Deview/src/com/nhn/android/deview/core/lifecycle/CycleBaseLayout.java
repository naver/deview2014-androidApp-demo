package com.nhn.android.deview.core.lifecycle;

import android.content.Context;
import android.widget.FrameLayout;


public class CycleBaseLayout extends FrameLayout implements CycleControllable {

	public CycleBaseLayout(Context context) {
		super(context);
		onCreate();
	}

	public void onCreate() {
		
	}
	
	@Override
	public void onStart() {
		
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onPause() {
		
	}

	@Override
	public void onStop() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void onActivityResult() {
	}

}
