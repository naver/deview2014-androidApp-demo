package com.nhn.android.deview.webviewer;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.R;
import com.nhn.android.deview.core.lifecycle.CycleControllerActivity;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.dao.MyMemo;
import com.nhn.android.deview.dao.MyMemoManager;
import com.nhn.android.deview.dao.program.ProgramData;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class ProgramWebActivity extends CycleControllerActivity {
	
	@DeclareView(id = R.id.webPanel)
	FrameLayout webPanel;
	
	@DeclareView(id = R.id.gnbPanel)
	View mGnbPanel;
	
	MemoWebToolbar mToolbar;
	WebPageView mWebPageView ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programweb, true);
		Uri uri = getIntent().getData();
		
		mWebPageView = new WebPageView(this);
		webPanel.addView(mWebPageView);
	

		mToolbar = new MemoWebToolbar(mGnbPanel);
		mToolbar.mProgramData = AppContext.getValue(ProgramWebActivity.class, true);
		
		mWebPageView.loadUrl(uri.toString());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {
		super.onActivityResult(requestCode, resultCode, arg2);
		if (requestCode == 100 && resultCode == RESULT_OK) {
			MyMemo memo = mToolbar.mMyMemo;
			MyMemoManager.addMemo(memo);	
		}
		
	}
}
