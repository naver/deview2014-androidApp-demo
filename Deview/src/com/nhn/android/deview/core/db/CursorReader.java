package com.nhn.android.deview.core.db;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import android.database.Cursor;
import android.view.HapticFeedbackConstants;

public class CursorReader {
	
	public static Vector readAll(Cursor c,Class<?> cls) {
		Field field = null;
		Object instance = null;

		Field[] fields = cls.getDeclaredFields();
		Vector list = new Vector<Object>();

		c.moveToFirst();
		
		while (c.isAfterLast() == false) {
			try {
				 instance = cls.newInstance();
			} catch (Exception e) {
				continue;
			} 
			
			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				int index = c.getColumnIndex(field.getName());
				try {
					if (index != -1) {
						Class<?> type = field.getType();
						String tn = type.getSimpleName();
						if (tn.equals("int") == true) {
							field.setInt(instance, c.getInt(index));
						} else if (tn.equals("float") == true) {
							field.setFloat(instance, c.getInt(index));
						} else if (tn.equals("String") == true) {
							field.set(instance, c.getString(index));
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			list.add(instance);
			c.moveToNext();
		}
		return list;
	}
	
	public static Object read(Cursor c,Class<?> cls) {
		Field field = null;
		c.moveToFirst();
		String names[] = c.getColumnNames();
		
			for (String name:names) {
				try {
					field = cls.getField(name);
					Class<?> type = field.getType();
					String tn = type.getSimpleName();
					
					if (tn.equals("Integer") == true) {
						//c.getInt(columnIndex);
						//field.setInt(object, value);
					}
				} catch (NoSuchFieldException e) {
				}
		}
		return null;
	}
	//public static boolean setFieldValue(Field field,Object target,Va)
}
