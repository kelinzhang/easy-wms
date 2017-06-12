package com.luis.wms.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//可以保存在JVM中
@Target(ElementType.METHOD)//只能贴在方法上
public @interface RequiredPermission {

	String value();//权限的中文名称
}
