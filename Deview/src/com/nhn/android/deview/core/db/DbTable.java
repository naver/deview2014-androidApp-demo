package com.nhn.android.deview.core.db;

import java.lang.reflect.Field;
import java.util.HashMap;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nhn.android.deview.core.connector.DataField;

public class DbTable {
	static HashMap<String,String> TYPEMAP = new HashMap<String, String>(); 
	static {
		TYPEMAP.put("byte", "INTEGER");
		TYPEMAP.put("short", "INTEGER");
		TYPEMAP.put("int", "INTEGER");
		TYPEMAP.put("long", "INTEGER");
		TYPEMAP.put("float", "REAL");
		TYPEMAP.put("double", "REAL");
		TYPEMAP.put("String", "TEXT");
		TYPEMAP.put("byte[]", "BLOB");
		
	}
	public static  boolean createTable(SQLiteDatabase db,String name,Class<?> cls) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS ");
		sb.append(name);
		sb.append(" (");

		Field[] fields = cls.getFields();
		
		for(int i = 0; i < fields.length; i++) {			
			DataField ref = fields[i].getAnnotation(DataField.class);			
			if (ref != null) {
				String type = fields[i].getType().getSimpleName();
				String colType = TYPEMAP.get(type);
				if (colType == null) {
					return false;
				}
				
				String attr = ref.attr();
				
				if (ref.name().length() > 0) {
					sb.append(ref.name());
				} else {
					sb.append(fields[i].getName());
				}
				sb.append(" ");
				sb.append(type);
				sb.append(" ");
				sb.append(attr);
				if ( fields.length-1 > i ) {
					sb.append(",");
				}
			}
		}
		sb.append(");");
		try {
			db.execSQL(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public static ContentValues toContentValues(Object object,String[] names) {
		ContentValues values = new ContentValues();
		
		if (names == null) {
		  Field[] fields = object.getClass().getFields();
		  names = new String[fields.length];
		  
		  for(int i = 0; i < fields.length; i++) {
			names[i] = fields[i].getName();  
		  }
		}
		
		for(String name:names) {
			try {
				Field field = object.getClass().getField(name);
				String stype = field.getType().getSimpleName();
				if (stype.equals("int") == true) {
					values.put(name,field.getInt(object));
				} else if (stype.equals("float") == true) {
					values.put(name,field.getFloat(object));
				} else if (stype.equals("String") == true) {
					values.put(name, (String)field.get(object));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return values;
	}
}
