package com.nhn.android.deview.ui.note;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nhn.android.deview.R;
import com.nhn.android.deview.core.listview.ListCellView;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.dao.MyMemo;
import com.nhn.android.deview.dao.program.ProgramData;

public class MemoCellView extends ListCellView<MyMemo> implements View.OnClickListener {

	@DeclareView(id = R.id.titleText,click = "this")
	public TextView mTitle;

	//@DeclareView(id = R.id.)
	//public TextView mTitle;
	
	
	public MemoCellView(Context context) {
		super(context);
	}

	@Override
	public View onCreateView(Context context) {
		return inflateView(R.layout.memo_listcell);
	}
	
	@Override
	public void setData(int position, MyMemo t) {
		super.setData(position, t);
		mTitle.setText(t.title);
		//Bitmap bMap = ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);

	}
	
	@Override
	public void onClick(View arg0) {
		
	}

}
