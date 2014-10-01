package com.nhn.android.deview.dao;

import com.nhn.android.deview.core.connector.DataField;
import com.nhn.android.deview.dao.program.ProgramData;

public class MyMemo {
	
	public MyMemo(ProgramData data) {
		id = data.id;
		title = data.name;
		text="";
		startTick = System.currentTimeMillis();
	}
	
	public void setEnd(String pathName) {
		recordingTime = System.currentTimeMillis() - startTick;
		mediaUri = pathName;
		
		
	}
	
	@DataField(attr = "PRIMARY KEY")
	public String id;

	@DataField
	public String title;
		
	@DataField
	public String date;
	
	@DataField
	public String text;
	
	@DataField
	public String mediaUri;	
	
	@DataField
	public long recordingTime;
	
	public long startTick;
}
