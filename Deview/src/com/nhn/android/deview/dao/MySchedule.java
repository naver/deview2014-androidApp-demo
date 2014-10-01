package com.nhn.android.deview.dao;

import com.nhn.android.deview.core.connector.DataField;
import com.nhn.android.deview.dao.program.ProgramData;

public class MySchedule {
	public MySchedule() {
		
	}

	public MySchedule(ProgramData data) {
		id = data.id;
		seq = data.seq;
		alarmType = 0;
		memo = 0;
	}	
	
	@DataField(attr = "PRIMARY KEY")
	public String id;

	@DataField
	public int seq;
	
	@DataField
	public int alarmType;  //0:NO 1: prev 10
	
	@DataField
	public int memo;  //0: NO 1:YES
}
