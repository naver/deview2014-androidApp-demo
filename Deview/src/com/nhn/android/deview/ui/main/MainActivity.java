package com.nhn.android.deview.ui.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.R;
import com.nhn.android.deview.R.id;
import com.nhn.android.deview.R.layout;
import com.nhn.android.deview.AppTable;
import com.nhn.android.deview.core.connector.DataConnectorListener;
import com.nhn.android.deview.core.connector.DefaultDataConnector;
import com.nhn.android.deview.core.db.DbTable;
import com.nhn.android.deview.core.lifecycle.CycleControllerActivity;
import com.nhn.android.deview.core.listview.PageViewPagerAdapter;
import com.nhn.android.deview.core.view.DeclareView;
import com.nhn.android.deview.dao.DeviewUri;
import com.nhn.android.deview.dao.program.ProgramData;
import com.nhn.android.deview.dao.program.ProgramManager;
import com.nhn.android.deview.dao.program.ProgramTrack;
import com.nhn.android.deview.dao.program.TrackListConnector;
import com.nhn.android.deview.ui.note.MemoRecorder;
import com.nhn.android.deview.ui.note.MyScheduleActivity;
import com.nhn.android.deview.webviewer.ProgramWebActivity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.PopupMenu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends CycleControllerActivity implements View.OnClickListener {

	@DeclareView(id = R.id.mainButton,click = "this")
	Button mMenuButton;
	
	@DeclareView(id = R.id.pager)
    ViewPager mViewPager;

	@DeclareView(id = R.id.day1Button,click = "this")
	Button mDay1Button;

	@DeclareView(id = R.id.day2Button,click = "this")
	Button mDay2Button;

	@Override
	public void onClick(View  view) {
		switch (view.getId()) {
		case R.id.day1Button:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.day2Button:
			mViewPager.setCurrentItem(4);
			break;
		case R.id.mainButton:
	          PopupMenu popup = new PopupMenu(MainActivity.this, mMenuButton);  
	          popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());  
	          popup.setOnMenuItemClickListener(mOnMenuItemClickListener);  
	          popup.show();//showing popup menu  			
			break;
		}
	}
   
   private  PageViewPagerAdapter<ProgramTrack> adapter ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.activity_main,true); //setViewMap
  
        TrackListConnector connector = new TrackListConnector();
        connector.open(new DataConnectorListener() {
			@Override
			public void onResult(int code, DefaultDataConnector connector) {
				initUI();
			}
		});

     
    }
    
    
    void initUI() {
        mViewPager.setOffscreenPageLimit(1);        
        adapter = new PageViewPagerAdapter<ProgramTrack>(ProgramPageView.class);
        adapter.mTracks = ProgramManager.getInstance().mTracks;
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				ProgramPageView pageView = (ProgramPageView)adapter.getView(position);
				registerForContextMenu(pageView.mListView);
				if (position < 4) {
					 mDay1Button.setSelected(true);
					 mDay2Button.setSelected(false);
				} else {
					 mDay1Button.setSelected(false);
					 mDay2Button.setSelected(true);	
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});    	
        mDay1Button.setSelected(true);
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	Uri uri = intent.getData();
    	if (uri != null) {
    	//	DeviewUri deviewUri = new DeviewUri(uri);
    		//goTrack(deviewUri);
    	}
    	
    }
    
    public void goToPageTrack(DeviewUri deviewUri) {
    	//deviewUri.trackID;
    //	mViewPager.setCurrentItem(item);
    }

    String item = "내 프로그램추가";
    ProgramData mItemData;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	//super.onCreateContextMenu(menu, v, menuInfo);
    	menu.add(item);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	if (item.getTitle().equals(item) == true) {
    		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    		ProgramData mItemData = ((ProgramCellView)info.targetView).mData;
    		
    		if (ProgramManager.getInstance().addMySchedule(mItemData) == true) {
    			Toast.makeText(AppContext.getContext(), "내 프로그램에 등록되었습니다.", Toast.LENGTH_SHORT).show();
    		}
    		
    	}
    	
    	
    	return super.onContextItemSelected(item);
    }
    
    final PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {  
        public boolean onMenuItemClick(MenuItem item) {  
        	Intent intent = new Intent(Intent.ACTION_VIEW);
         switch (item.getItemId()) {
         case R.id.action_myschedule:
        	 intent.setClass(MainActivity.this, MyScheduleActivity.class);
        	 startActivity(intent);
        	 break;
//         case R.id.action_memo:
//        	 intent.setClass(MainActivity.this, MemoRecorder.class);
//        	 intent.putExtra("pathName","/sdcard/cap.mp4");
//        	 intent.putExtra("isAudioOnly",false);       	 
//        	 startActivity(intent);
//        	 break;
         }
         
         return true;  
        }  
      };
}
