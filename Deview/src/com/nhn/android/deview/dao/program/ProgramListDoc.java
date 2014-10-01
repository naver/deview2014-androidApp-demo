package com.nhn.android.deview.dao.program;

import java.util.Vector;

import com.nhn.android.deview.core.connector.DataField;
import com.nhn.android.deview.core.connector.DataSet;

public class ProgramListDoc {

	@DataField(name = "",path = "/")
	public String mResultMsg;
	
	@DataSet(path="/schedule/scheduleMap",cls = ProgramData.class)
	public Vector<ProgramData> mTracks;
}
