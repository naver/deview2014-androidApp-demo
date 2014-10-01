package com.nhn.android.deview;


import com.nhn.android.deview.ui.main.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;

public class DeviewSchemeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		processUri(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}
	
	
	void processUri(Intent intent) {
		Uri uri = intent.getData();
		if (uri != null) {
			String host = uri.getHost();
			
			Intent intent0 = new Intent(Intent.ACTION_VIEW);
			intent0.setData(uri);
			
			if (host.equals("default") == true) {
				intent0.setClass(this, MainActivity.class);
				
			} else if (host.equals("myprogram") == true) {
				
			} else if (host.equals("note") == true) {
				
			}
			startActivity(intent0);
		}
	
		if (uri != null) {
			String ver = uri.getQueryParameter("version");
			String sessionCode = uri.getQueryParameter("sessionCode");
			String action= uri.getQueryParameter("action"); //VIEW, ADD, REMOVE
		/*	
			if (host.equals("default") == true) {
				intent0.setClass(this, MainActivity.class);
				
			} else if (host.equals("myprogram") == true) {
				
			} else if (host.equals("note") == true) {
				
			}*/
			
		}
		
		
	
		
	}
	
	//DatabaseUtils.
}
