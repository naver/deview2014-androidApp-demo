package com.nhn.android.deview.ui.main;

import java.util.Vector;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.core.connector.DataConnectorListener;
import com.nhn.android.deview.core.connector.DefaultDataConnector;
import com.nhn.android.deview.core.lifecycle.CycleBaseLayout;
import com.nhn.android.deview.core.listview.ListCellViewAdapter;
import com.nhn.android.deview.core.listview.PageView;
import com.nhn.android.deview.dao.program.ProgramData;
import com.nhn.android.deview.dao.program.ProgramListConnector;
import com.nhn.android.deview.dao.program.ProgramListDoc;
import com.nhn.android.deview.dao.program.ProgramTrack;
import com.nhn.android.deview.webviewer.ProgramWebActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class ProgramPageView extends PageView<ProgramTrack> implements OnItemClickListener, 
	OnItemLongClickListener , DataConnectorListener {
	ProgramListConnector mConnector = null;
	public Vector<ProgramData> mProgramList;
	ListView mListView;
	
	public ProgramPageView(Context context) {
		super(context);
		this.addView(onCreateView());
	}
	
	public View onCreateView() { 
		mListView = new ListView(getContext());
		mListView.setOnItemClickListener(this);
		return mListView;
	}
	
	@Override
	public void setItemData(int position,ProgramTrack data) {
		if (data.hasProgmList() == true) {
			setAdapter(data.mTimeTable);
		} else {
			mConnector = new ProgramListConnector();
			mConnector.open(data, this);
		}
	}
	
	public void setAdapter(Vector<ProgramData> list) {
		ListCellViewAdapter adapter = new ListCellViewAdapter<ProgramData>(ProgramCellView.class);
		adapter.mDataList = list;
		mListView.setAdapter(adapter);	
	}
	
	public void onResume() {
		/*
		for (ProgramData d:mProgramList) {
			d.checkTime();
		}
		((BaseAdapter)mListView.getAdapter()).notifyDataSetChanged();
		*/
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		ProgramCellView cellView = (ProgramCellView)view;
		if (cellView.mData != null) {
			AppContext.putValue(ProgramWebActivity.class, cellView.mData);
			Uri uri = Uri.parse("http://deview.kr/2014/session?seq=" + cellView.mData.seq);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.setClass(getContext(), ProgramWebActivity.class);
			getContext().startActivity(intent);
		}
		
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//ContextMenu	menu = new ContextMenu();
		
		return false;
	}	

	@Override
	public void onResult(int code, DefaultDataConnector connector) {
		if (code == 200) {
			mProgramList = ((ProgramListConnector)connector).mTrack.mTimeTable;
			setAdapter(mProgramList);
		}		
	}
}
