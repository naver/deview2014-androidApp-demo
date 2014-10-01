package com.nhn.android.deview.ui.note;

import java.util.Vector;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.R;
import com.nhn.android.deview.R.id;
import com.nhn.android.deview.R.layout;
import com.nhn.android.deview.core.db.DbTable;
import com.nhn.android.deview.core.db.CursorReader;
import com.nhn.android.deview.core.lifecycle.CycleControllerActivity;
import com.nhn.android.deview.core.listview.ListCellViewAdapter;
import com.nhn.android.deview.core.transfer.BundleField;
import com.nhn.android.deview.core.transfer.BundleMapper;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.dao.MySchedule;
import com.nhn.android.deview.dao.program.ProgramData;
import com.nhn.android.deview.dao.program.ProgramManager;
import com.nhn.android.deview.ui.main.ProgramCellView;
import com.nhn.android.deview.webviewer.ProgramWebActivity;

public class MyScheduleActivity extends CycleControllerActivity implements OnItemClickListener {
	public String SCHEDULE_TABLE = "memo_tbl";
	
	@DeclareView(id = R.id.scheduleList)
	public ListView mListView;	
	
	@BundleField //
	int seq; 
	
	@BundleField 
	String name;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_myschedule, true);
		BundleMapper.fromIntent(this, getIntent());
		
		Vector<ProgramData> mMyProgram = ProgramManager.getInstance().getMyProgramList();		
		mListView.setAdapter(new ListCellViewAdapter<ProgramData>(ProgramCellView.class,mMyProgram));
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		ProgramCellView cellView = (ProgramCellView)view;
		if (cellView.mData != null) {
			Uri uri = Uri.parse("http://deview.kr/2014/session?seq=" + cellView.mData.seq);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.setClass(this, ProgramWebActivity.class);
			startActivity(intent);
		}	
	}


}
