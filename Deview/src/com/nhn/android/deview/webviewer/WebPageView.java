package com.nhn.android.deview.webviewer;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nhn.android.deview.core.lifecycle.CycleBaseLayout;
import com.nhn.android.deview.core.lifecycle.CycleControllerActivity;
public class WebPageView extends CycleBaseLayout {
	WebView mWebView;
	public WebPageView(Context context) {
		super(context);
		((CycleControllerActivity)context).getCycleController().register(this, 0);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mWebView.resumeTimers();
		mWebView.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mWebView.pauseTimers();
		mWebView.onPause();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mWebView != null) {
			removeViewInLayout(mWebView);
			mWebView.destroyDrawingCache();
			mWebView.destroy();	
			mWebView = null;
		}
		((CycleControllerActivity)getContext()).getCycleController().unregister(this, false);
	}
	
	public void loadUrl(String uri) {
		mWebView.loadUrl(uri);
	}
	
	@Override
	public
	void onCreate() {
		mWebView = new WebView(getContext());
		mWebView.setWebViewClient(new WebViewClient() {});
		mWebView.setWebChromeClient(new WebChromeClient() {});
		this.addView(mWebView);
		initWebView();
	}	
	
	void initWebView() {
		WebSettings ws = mWebView.getSettings();
		ws.setJavaScriptEnabled(true);
	}
	
}
