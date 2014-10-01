package com.nhn.android.deview;

import com.nhn.android.deview.core.db.DbTable;
import com.nhn.android.deview.dao.MyMemo;
import com.nhn.android.deview.dao.MySchedule;
import com.nhn.android.deview.dao.program.ProgramData;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDB  extends SQLiteOpenHelper{
	private static final String DB_NAME = "deview10"; 	// 저장될 DB 이름
	private static final int 	DB_VER = 1;				// DB 버전
	
	public SQLiteDatabase mDB;
	public MainDB(Context context) {
		super(context, DB_NAME, null, DB_VER);
	}
	
	public void init() {
		mDB = getWritableDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		DbTable.createTable(db, AppTable.MYSCHEDULE, MySchedule.class);
		DbTable.createTable(db, AppTable.MYMEMO, MyMemo.class);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist myschdule_tbl");
		onCreate(db);
	}

}
