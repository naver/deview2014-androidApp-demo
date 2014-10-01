package com.nhn.android.deview.ui.note;

import java.util.Vector;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.R;
import com.nhn.android.deview.core.db.CursorReader;
import com.nhn.android.deview.core.lifecycle.CycleControllerActivity;
import com.nhn.android.deview.core.listview.ListCellViewAdapter;
import com.nhn.android.deview.dao.DeviewUri;
import com.nhn.android.deview.dao.MyMemo;
import com.nhn.android.deview.dao.MyMemoManager;

public class MemoListActivity extends CycleControllerActivity implements OnItemClickListener {

	ListView mListView;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_memolist, true);

		Vector<MyMemo> list = MyMemoManager.getMemoList();
		if (list != null) {
			ListCellViewAdapter<MyMemo> adapter = 
					new ListCellViewAdapter<MyMemo>(MemoCellView.class,list);
			mListView.setAdapter(adapter);
		}
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long arg3) {
		MemoCellView cellView = (MemoCellView) view;
		if (cellView.mData.mediaUri != null) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.parse(cellView.mData.mediaUri), "video/mpeg");
			startActivity(intent);
		}
	}
	
}
