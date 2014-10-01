package com.nhn.android.deview.webviewer;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.R;
import com.nhn.android.deview.R.id;
import com.nhn.android.deview.core.lifecycle.CycleBaseLayout;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.core.view.ViewMapper;
import com.nhn.android.deview.dao.MyMemo;
import com.nhn.android.deview.dao.program.ProgramData;
import com.nhn.android.deview.dao.program.ProgramManager;
import com.nhn.android.deview.ui.main.MainActivity;
import com.nhn.android.deview.ui.main.ProgramCellView;
import com.nhn.android.deview.ui.note.MemoListActivity;
import com.nhn.android.deview.ui.note.MemoRecorder;
import com.nhn.android.deview.ui.note.MyScheduleActivity;

public class MemoWebToolbar  implements View.OnClickListener {

	@DeclareView(id = R.id.addMySchedule,click="this")
	Button mAddMySchedule;

	@DeclareView(id = R.id.videoMemo, click="this")
	Button mVideoMemo;	
	
	@DeclareView(id = R.id.textMemo, click="this")
	Button mTextMemo;	
	
	@DeclareView(id = R.id.viewMemo, click="this")
	Button mViewMemo;	
	
	public ProgramData mProgramData;
	public MyMemo	mMyMemo;
	
	Activity mContext;
	public MemoWebToolbar(View body) {
		ViewMapper.mapLayout(this, body);
		mContext = (Activity)body.getContext();
		
	}

	@Override
	public void onClick(View v) {
    	Intent intent = new Intent(Intent.ACTION_VIEW);
	
		switch (v.getId()) {
		case R.id.addMySchedule:
    		if (ProgramManager.getInstance().addMySchedule(mProgramData) == true) {
    			Toast.makeText(AppContext.getContext(), "내 프로그램에 등록되었습니다.", Toast.LENGTH_SHORT).show();
    		}
			break;
		case R.id.videoMemo:
			mMyMemo = new MyMemo(mProgramData);
			
			File dir = AppContext.getContext().getCacheDir();
			String pathName = String.format("%s/memo_%s_%d",dir.getAbsolutePath(),mMyMemo.id,System.currentTimeMillis());
			mMyMemo.mediaUri =  pathName;
			intent.setClass(mContext, MemoRecorder.class);
        	intent.putExtra("pathName",pathName);
        	intent.putExtra("isAudioOnly",false);    
        	mContext.startActivityForResult(intent, 100);	
		case R.id.textMemo:
			break;
		case R.id.viewMemo:
			intent.setClass(mContext, MemoListActivity.class);
			mContext.startActivityForResult(intent, mContext.hashCode() & 0xFFFF);	
			break;
		}
	}
	
}
