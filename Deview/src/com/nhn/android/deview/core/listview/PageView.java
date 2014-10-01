package com.nhn.android.deview.core.listview;

import android.content.Context;

import com.nhn.android.deview.core.lifecycle.CycleBaseLayout;

public class PageView<TYPE> extends CycleBaseLayout {
	public TYPE mData;
	
	public PageView(Context context) {
		super(context);

	}
	
	public void setItemData(int position,TYPE data) {
		mData = data;
	}

}
