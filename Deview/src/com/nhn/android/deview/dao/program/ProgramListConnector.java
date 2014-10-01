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

public class ProgramListConnector extends DefaultDataConnector {
	public ProgramTrack mTrack;
	DataConnectorListener mListener;
	
	public void open(ProgramTrack track,DataConnectorListener l) {
		mTrack = track;
		mListener = l;
		open("http://deview.kr/2014/app/sessionList?seq=" + track.seq);
	}
	
	@Override
	public void onReceive(byte[] response) {
		String json;
		try {
			json = new String(response,"UTF-8");
			mTrack.mTimeTable = parseSession(json);
			ProgramManager.getInstance().setProgramList(mTrack, mTrack.mTimeTable);
			mHandler.obtainMessage(200).sendToTarget();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<ProgramData> parseSession(String json) {
		JSONObject root;
		Vector<ProgramData> list = new Vector<ProgramData>();
		try {
			root = new JSONObject(json);
			JSONArray trackArray = root.getJSONArray("sessionList");
			for (int i = 0; i< trackArray.length(); i++) {
				ProgramData trackData = new ProgramData((JSONObject)trackArray.get(i));
				list.add(trackData);
				//ProgramManager.getInstance().mProgramMap.put(trackData.id, trackData);
			}						
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	final Handler mHandler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message arg0) {
			mListener.onResult(200, ProgramListConnector.this);
			return false;
		}
	});
	
	
}
