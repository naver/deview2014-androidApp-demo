package com.nhn.android.deview.dao.program;

import java.lang.reflect.Field;

import org.json.JSONObject;

import com.nhn.android.deview.core.connector.DataField;
import com.nhn.android.deview.dao.MySchedule;

public class ProgramData {
	public ProgramData(JSONObject json) {
		try {
			Field[] fields = ProgramData.class.getFields();
			for(Field field: fields) 
			{
				String sname = field.getType().getSimpleName();
				String name = field.getName();
				try {
					if (sname.equals("String") == true) {
						String value = json.getString(name);
						field.set(this, value);
					} if (sname.equals("int") == true)  {
						field.setInt(this, json.getInt(name));
					}
				} catch(org.json.JSONException e1) {
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	@DataField(attr = "PRIMARY KEY")
	public String id;
	
	@DataField(name = "name")
	public String name;
	
	@DataField
	public String speaker;
	
	@DataField
	public int seq;
	
	@DataField(name = "programStrt")
	public String programStrt;

	@DataField(name = "programEnd")
	public String programEnd;
	
	@DataField
	public String trackName; //D
	
	@DataField
	public String programName; //Session 1
	
	@DataField
	public String episodeName; //Day 2 
	
	@DataField
	public String url;
	
	
	@DataField
	public boolean isbooked; //	
	public boolean isPassed; //TimePassed
	
	public void checkTime() {
		
	}
	
	public MySchedule mMySchedule = null;
}
