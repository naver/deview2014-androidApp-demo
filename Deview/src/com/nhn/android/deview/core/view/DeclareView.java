package com.nhn.android.deview.core.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DeclareView {
	int id() default 0;
	String name() default "";
	String tag() default "";
	String click() default "";
}
