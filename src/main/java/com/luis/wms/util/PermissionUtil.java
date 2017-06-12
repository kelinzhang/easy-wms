package com.luis.wms.util;

import java.lang.reflect.Method;

public class PermissionUtil {

	public static String buildExpression(Method method) {
		//得到方法所在的类
		String classname = method.getDeclaringClass().getName();
		return classname + ":" + method.getName();
	}
}
