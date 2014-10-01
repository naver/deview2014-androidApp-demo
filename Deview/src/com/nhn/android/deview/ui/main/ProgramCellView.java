package com.nhn.android.deview.ui.main;

import com.nhn.android.deview.R;
import com.nhn.android.deview.R.id;
import com.nhn.android.deview.R.layout;
import com.nhn.android.deview.core.listview.ListCellView;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.core.view.ViewMapper;
import com.nhn.android.deview.dao.program.ProgramData;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class ProgramCellView extends ListCellView<ProgramData> implements View.OnClickListener {
	@DeclareView(id = R.id.lecTitle)
	public TextView mTitle;
	
	@DeclareView(id = R.id.speaker,click="this")
	public TextView mSpeaker;
	
	@DeclareView(id = R.id.lecTime,click="this")
	public TextView mTime;
	
	public ProgramCellView(Context context) {
		super(context);
	}
	
	@Override
	public View onCreateView(Context context) {
		return inflateView(R.layout.program_listcell);
	}

	@Override
	public void setData(int position, ProgramData data) {
		super.setData(position, data);
		mTitle.setText(data.name);
		mSpeaker.setText("발표자 : " + data.speaker);	
		mTime.setText(String.format("시간 : %s ~ %s",data.programStrt,data.programEnd));	
		if (position % 2 == 1) {
			this.setBackgroundColor(0xFFE0E0E0);
		} else {
			this.setBackgroundColor(Color.WHITE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lecTitle:
			break;
		case R.id.lecTime:
			break;
		}
		
	}

}
