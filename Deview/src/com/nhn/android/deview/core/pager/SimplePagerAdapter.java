package com.nhn.android.deview.core.pager;

import java.util.AbstractCollection;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class SimplePagerAdapter<TYPE> extends PagerAdapter {
	Class<?> mCls;
	public SimplePagerAdapter(Class<?> pagecls) {
		//mCls = cls;
	}
	
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		
		return super.instantiateItem(container, position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

}
