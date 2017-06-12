package com.luis.generator;

import lombok.Getter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

//封装类相关的信息
@Getter
public class ClassInfo {
    private String basePkg;//类的基本的包
    private String className;//类的简单名称
    private String objectName;//对象的名称,类名首字母小写
    private List<String> props = new ArrayList<>();//类中属性的名称

    public ClassInfo(Class<?> clz) throws Exception {
        String pkgName = clz.getPackage().getName();
        this.basePkg = pkgName.substring(0, pkgName.lastIndexOf('.'));
        this.className = clz.getSimpleName();
        this.objectName = className.substring(0, 1).toLowerCase() + className.substring(1);

        BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (!"id".equals(pd.getName())) {
                props.add(pd.getName());
            }
        }
    }
}
