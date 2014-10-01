package com.nhn.android.deview.core.transfer;

import java.lang.reflect.Field;

import android.net.Uri;

public class UriObject {
	public UriObject(Uri uri) {
		Field[] fields = getClass().getFields();
		for (Field field : fields) {
			String value = uri.getQueryParameter(field.getName());
			if (value != null) {
				setStrToFieldValue(field,this, value);
			}
		}		
	}
	
	boolean setStrToFieldValue(Field field,Object object,String value)  {
		try {
			String tn = field.getType().getSimpleName();
			if (field.getType().isPrimitive() == true) {
				if (tn.equals("int")) {
					field.setInt(object, Integer.parseInt(value));	
				} else if (tn.equals("float")) {
					field.setFloat(object, Float.parseFloat(value));
				} else {
					return false;
				}
			} else {
				if (tn.equals("String") == true || tn.equals("Integer") || tn.equals("Float")) {
					field.set(object, value);
				} 
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	
}
