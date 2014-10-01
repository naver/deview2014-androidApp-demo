package com.nhn.android.deview.core.connector;

import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

public class DefaultDataConnector {

	AsyncHttpClient mHttpClient;
	
	Class<?> mRoot;
	public boolean open(InputStream in,Class<?> cls) {
		String json = "";
		try {
			JSONObject jobject = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return true;
	}
	
	public boolean open(String url) {
		mHttpClient = new AsyncHttpClient();
		mHttpClient.get(url, mResponseHandler);
		return false;
		
	}
	
	public DataDoc getResultDoc() {
		return null;
	}
	
	public void onReceive(byte[] response) {
		
	}

	AsyncHttpResponseHandler mResponseHandler = new AsyncHttpResponseHandler() {
		    @Override
		    public void onStart() {
		    }

		    @Override
		    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
		        // called when response HTTP status is "200 OK"
		    	onReceive(response);
		    	
		    }

		    @Override
		    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
		        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
		    }

		    @Override
		    public void onRetry(int retryNo) {
			}
		};
}
