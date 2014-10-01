package com.nhn.android.deview.core.listview;

import com.nhn.android.deview.core.view.ViewMapper;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class ListCellView<T> extends FrameLayout {
	public T mData;
	public long mItemId = 0;
	
	public ListCellView(Context context) {
		super(context);
		View view = onCreateView(context);
		if (view != null) {
			this.addView(view);
		}
	}
	
	public View onCreateView(Context context) {
		return null;
	}

	public View inflateView(int layout) {
		View view = ViewMapper.inflateLayout(getContext(), this, layout);
		return view;
	}
	
	public void setData(int position,T t) {
		mData = t;
		mItemId = position;
	}
}
