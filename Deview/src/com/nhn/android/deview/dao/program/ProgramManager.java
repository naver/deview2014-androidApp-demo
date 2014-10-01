package com.nhn.android.deview.dao.program;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.AppTable;
import com.nhn.android.deview.core.db.CursorReader;
import com.nhn.android.deview.core.db.DbTable;
import com.nhn.android.deview.dao.MySchedule;

public class ProgramManager {
	
	static ProgramManager mInstance = null;
	public static ProgramManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProgramManager();
		}
		return mInstance;
	}
	
	public Vector<ProgramTrack>  mTracks = new Vector<ProgramTrack>();	
	public HashMap<String,ProgramData> mProgramMap = new HashMap<String, ProgramData>();
	
	public void parseTrack(String json) {
		JSONObject root;
		try {
			root = new JSONObject(json);
			JSONObject trackMap = root.getJSONObject("trackMap");
			JSONArray trackArray = trackMap.getJSONArray("DAY 1");
			for (int i = 0; i< trackArray.length(); i++) {
				ProgramTrack trackData = new ProgramTrack((JSONObject)trackArray.get(i));
				mTracks.add(trackData);
			}
						
			trackArray = trackMap.getJSONArray("DAY 2");
			for (int i = 0; i< trackArray.length(); i++) {
				ProgramTrack trackData = new ProgramTrack((JSONObject)trackArray.get(i));
				mTracks.add(trackData);
			}
			/*
			for (ProgramTrack t: mTracks) {
				ProgramListConnector c= new ProgramListConnector();
				c.open(t, null);
			}*/
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		
	}

	public void setProgramList(ProgramTrack track,Vector<ProgramData> list) {
	{
				Collections.sort(list,new Comparator<ProgramData>() {
					@Override
					public int compare(ProgramData lhs, ProgramData rhs) {
						return lhs.programStrt.compareTo(rhs.programStrt);
					}
				});
				
				track.mTimeTable = list;
				for (ProgramData data:list) {
					mProgramMap.put(data.id, data);
				}
				
				return;
			}
		
	}

	public Vector<ProgramData> getProgramList(String trackName,int seq) {
		for(ProgramTrack t: mTracks) {
			if (t.seq == seq) {
				return t.mTimeTable;
			}
		}
		return null;
	}
	
	public ProgramData getProgram(String id) {
		return mProgramMap.get(id);
	}
	
	public Vector<ProgramData> getMyProgramList() {

		Vector<ProgramData> mMyProgram = new Vector<ProgramData>();
		
		Cursor c = AppContext.getDB().query(AppTable.MYSCHEDULE, null , null, null, null, null, null);
		
		Vector<MySchedule> listData = CursorReader.readAll(c, MySchedule.class);
		
		for (int i = 0; i < listData.size(); i++) {
			MySchedule schedule = listData.get(i);
			ProgramData item = ProgramManager.getInstance().mProgramMap.get(schedule.id);
			
			if (item != null) {
				item.mMySchedule = schedule;
				mMyProgram.add(item);
			}
		}
		
		Collections.sort(mMyProgram,new Comparator<ProgramData>() {
			@Override
			public int compare(ProgramData lhs, ProgramData rhs) {
				return lhs.programStrt.compareTo(rhs.programStrt);
			}
		});
		return mMyProgram;
	}
		
	
	public boolean addMySchedule(ProgramData data) {
 		ContentValues values = DbTable.toContentValues(new MySchedule(data), null);
		SQLiteDatabase db = AppContext.getDB();
		long result = db.insert(AppTable.MYSCHEDULE , null, values);
		return result >= 0;
	}
}
