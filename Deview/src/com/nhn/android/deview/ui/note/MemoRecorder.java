package com.nhn.android.deview.ui.note;

import java.io.IOException;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.R;
import com.nhn.android.deview.core.lifecycle.CycleControllerActivity;
import com.nhn.android.deview.core.transfer.BundleField;
import com.nhn.android.deview.core.transfer.BundleMapper;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.core.view.ViewMapper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.media.MediaRecorder.VideoEncoder;
import android.media.MediaRecorder.VideoSource;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MemoRecorder extends CycleControllerActivity implements SurfaceHolder.Callback {
	@BundleField
	public boolean isAuidoOnly;
	
	@BundleField
	public String pathName;
	
	@DeclareView(id = R.id.memoVideoPreview)
	SurfaceView mPreview;
		
	MediaRecorder mRecorder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BundleMapper.fromIntent(this,getIntent());	
		setContentView(R.layout.activity_memo_recorder, true);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		startRecord(isAuidoOnly);

	}
	
	//
	@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	void startRecord(boolean audioOnly) {
		try {
			mRecorder = new MediaRecorder();
			if (audioOnly == false) {
				mRecorder.setVideoSource(VideoSource.CAMERA);
			}
			
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			
			CamcorderProfile camcorderProfile_HQ = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
			mRecorder.setProfile(camcorderProfile_HQ);
			mPreview.getHolder().addCallback(this);
			mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			
			//mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			mRecorder.setOutputFile(pathName);
	
		  // Recording is now started
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void stopRecord() {

		 if (mRecorder != null) {
			 mRecorder.stop();
			 mRecorder.reset();   // You can reuse the object by going back to setAudioSource() step
			 mRecorder.release();	
			 mRecorder = null;
		 }
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
		try {
			//mRecorder.setOrientationHint(180);
			mRecorder.prepare();
			mRecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	
}
