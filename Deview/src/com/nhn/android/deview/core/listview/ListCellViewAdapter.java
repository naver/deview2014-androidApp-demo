package com.nhn.android.deview.core.listview;

import java.lang.reflect.Constructor;
import java.util.Vector;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListCellViewAdapter<T> extends BaseAdapter {
	public Vector<T> mDataList = new Vector<T>();
	Class<?> mCls;
	
	public ListCellViewAdapter(Class<?> cls) {
		 mCls = cls;
	}
	public ListCellViewAdapter(Class<?> cls,Vector<T> dataList) {
		 mCls = cls;
		 mDataList = dataList;
	}

	public void setDataList(Vector<T> dataList) {
		mDataList = dataList;
		this.notifyDataSetChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ListCellView<T> cellView = (ListCellView<T>) view;
		if (cellView == null) { 
			try {
				Constructor<?> con = mCls.getDeclaredConstructor(Context.class);
				cellView = (ListCellView<T>) con.newInstance(viewGroup.getContext());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
		}
		cellView.mItemId = position;
		cellView.setData(position, mDataList.get(position));
		return cellView;
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}
	
	@Override
	public int getCount() {
		if (mDataList == null) {
			return 0;
		}
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataList.get(position);
	}

}
