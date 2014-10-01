package com.nhn.android.deview.dao.program;

import java.lang.reflect.Field;
import java.util.Vector;

import org.json.JSONObject;

import android.content.pm.FeatureInfo;

import com.nhn.android.deview.core.connector.DataField;

public class ProgramTrack {
	
	@DataField
	public int seq;
	
	@DataField
	public int episodeSeq;
	
	@DataField
	public String name;

	@DataField
	public int order;
	
	
	public ProgramTrack(JSONObject track) {	
		try {
			Field[] fields = ProgramTrack.class.getFields();
			for(Field field: fields) 
			{
				String sname = field.getType().getSimpleName();
				String name = field.getName();
				if (sname.equals("String") == true) {
					String value = track.getString(name);
					field.set(this, value);
				} if (sname.equals("int") == true)  {
					field.setInt(this, track.getInt(name));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Vector<ProgramData> mTimeTable = new Vector<ProgramData>();
	public Vector<ProgramData> getTrack(int trackId) {
		return mTimeTable;
	}
	
	public boolean hasProgmList() {
		return mTimeTable.size() > 0;
	}
}
