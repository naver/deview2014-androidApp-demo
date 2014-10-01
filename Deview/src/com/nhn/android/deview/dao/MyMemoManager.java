package com.nhn.android.deview.dao;

import java.util.Vector;

import android.content.ContentValues;
import android.database.Cursor;

import com.nhn.android.deview.AppContext;
import com.nhn.android.deview.AppTable;
import com.nhn.android.deview.core.db.CursorReader;

public class MyMemoManager {

	public static void addMemo(MyMemo memo) {	
		ContentValues value = null;
		AppContext.getDB().insert(AppTable.MYMEMO, null, value);
	}
	
	@SuppressWarnings("unchecked")
	public static Vector<MyMemo> getMemoList() {
		Cursor c = AppContext.getDB().query(AppTable.MYMEMO, null, null, null, null, null, null);	
		Vector<MyMemo> list = CursorReader.readAll(c, MyMemo.class);
		return list;
	}
}
