package com.nhn.android.deview.core.listview;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Vector;

import com.nhn.android.deview.dao.program.ProgramTrack;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class PageViewPagerAdapter<TYPE> extends PagerAdapter {
	public Vector<ProgramTrack> mTracks = null;
	public HashMap<Integer, Object> mViews = new HashMap();
	
	Class<?> mPageCls;
	public PageViewPagerAdapter(Class<?> pagecls) {
		mPageCls = pagecls;
	}
	
	@Override
	public int getCount() {
		if (mTracks != null) {
			return mTracks.size();
		}
		return 0;
	}

	
	@Override
	public CharSequence getPageTitle(int position) {
		return mTracks.get(position).name;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	public Object getView(int position) {
		return mViews.get(position);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		PageView<TYPE> pageView = null;
		try {
			pageView = (PageView<TYPE>)mPageCls.getConstructor(Context.class).
					newInstance(container.getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageView.setItemData(position,(TYPE) mTracks.get(position));
		container.addView(pageView);
		
		mViews.put(position, pageView);
		return pageView;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
		mViews.remove(position);
	}

}
