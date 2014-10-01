package com.nhn.android.deview.dao;

import android.net.Uri;

import com.nhn.android.deview.core.transfer.UriObject;

public class DeviewUri extends UriObject{
	public DeviewUri(Uri uri) {
		super(uri);
	}
	public int version;
	public String host;
	public String sessionCode; 
	public String action; //VIEW, ADD, REMOVE
	
}
/*
class DeviewNoteUri extends UriObject{
	public DeviewNoteUri(Uri uri) {
		super(uri);
	}
	//public int version;
	//public String host;
	public String sessionCode; 
	public String action; 
	public String title;
	public String attachment;
}*/

//	public String day; //1,2
//deview://myprogram?
//deview://list?
//deview://myprogram?