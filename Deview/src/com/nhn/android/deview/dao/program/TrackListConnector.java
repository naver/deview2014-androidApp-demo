package com.nhn.android.deview.dao.program;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.nhn.android.deview.core.connector.DataConnectorListener;
import com.nhn.android.deview.core.connector.DefaultDataConnector;

public class TrackListConnector extends DefaultDataConnector {
	DataConnectorListener mListener;
	
	public void open(DataConnectorListener l) {
		mListener = l;
		open("http://deview.kr/2014/app/trackMap");
	}
	
	@Override
	public void onReceive(byte[] response) {
		String json;
		try {
			json = new String(response,"UTF-8");
			ProgramManager.getInstance().parseTrack(json);
			mHandler.obtainMessage(200).sendToTarget();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	

	final Handler mHandler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message arg0) {
			mListener.onResult(200, TrackListConnector.this);
			return false;
		}
	});
	
	
}
