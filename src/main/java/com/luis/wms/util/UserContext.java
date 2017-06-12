package com.luis.wms.util;

import com.luis.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

//用户上线文,用来存取session中的数据
public class UserContext {

    private UserContext() {
    }

    private static final String USER_IN_SESSION = "USER_IN_SESSION";
    private static final String PERMISSIONSET_IN_SESSION = "PERMISSIONSET_IN_SESSION";

    //把当前登录对象存储到session中
    public static void setCurrentUser(Employee current) {
        if (current != null) {
            ActionContext.getContext().getSession().put(USER_IN_SESSION, current);
        } else {
            //用于注销用户
            ActionContext.getContext().getSession().remove(USER_IN_SESSION);
        }
    }

    //从session中获取当前登录用户
    public static Employee getCurrentUser(){
        return (Employee) ActionContext.getContext().getSession().get(USER_IN_SESSION);
    }

    //把当前登录用户所有权限表达式保存到session中
    public static void setPermExpression(Set<String> permissionSet) {
        ActionContext.getContext().getSession().put(PERMISSIONSET_IN_SESSION, permissionSet);
    }

    //从当前session中获取当前用户的所有权限
    public static Set<String> getPermExpression(){
        return (Set<String>) ActionContext.getContext().getSession().get(PERMISSIONSET_IN_SESSION);
    }
}
