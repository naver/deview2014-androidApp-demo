package com.nhn.android.deview.core.transfer;

import java.lang.reflect.Field;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;

public class BundleMapper {

	boolean toBundle(Object container,Bundle bundle,boolean isAll) {
		Field[] fields = container.getClass().getFields();
		String name;
		for (Field f:fields) {
			
			BundleField ref = f.getAnnotation(BundleField.class);
			String sname = f.getType().getSimpleName();
			if (ref != null) {
				name = ref.name();
			} else {
				name = f.getName();
			}
			
			 try {
				if (sname.equals("String") == true) {
					bundle.putString(name, (String) f.get(container));
				} else if (sname.equals("int") == true) {
					bundle.putInt(name, f.getInt(container));				
				} else if (sname.equals("long") == true) {
					bundle.putLong(name, f.getLong(container));
				} else if (sname.equals("float") == true) {
					bundle.putFloat(name, f.getFloat(container));
				} else if (sname.equals("double") == true) {
					bundle.putDouble(name, f.getDouble(container));	
				}
			} catch (Exception e) {
			}
		}
		return false;
	}
	
	public static boolean fromBundle(Object container,Bundle bundle) {
		if (bundle == null) {
			return false;
		}
		
		Set<String> keys =  bundle.keySet();
		Class<?> cls = container.getClass();
		for (String key:keys) {
			try {
				Field field = cls.getField(key);
				field.setAccessible(true);
				BundleField ref = field.getAnnotation(BundleField.class);
				if (ref != null) {
					if (cls.isPrimitive() == true) {
						String sname = field.getType().getSimpleName();
						if (sname.equals("int") == true) {
							field.setInt(container, bundle.getInt(key));
						} else if (sname.equals("float") == true) {
							field.setFloat(container, bundle.getFloat(key));
						} else {
							return false;
						}
						return true;
					} else {
						Object value = bundle.get(key);
						field.set(container, value);
					}
				}
			} catch (NoSuchFieldException e) {
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
		
	public static boolean fromIntent(Object container,Intent intent) {
		//Uri uri = getData();
		if (intent == null) {
			return false;
		}
		
		return fromBundle(container,intent.getExtras());
	}
}
