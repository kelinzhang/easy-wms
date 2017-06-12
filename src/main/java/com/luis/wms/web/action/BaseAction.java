package com.luis.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

public class BaseAction extends ActionSupport implements Preparable {
    private static final long serialVersionUID = 1L;

    @Override
    public void prepare() throws Exception {

    }

    protected void putJson(Object obj) {
        try {
            String jsonString = JSON.toJSONString(obj);
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putContext(String name, Object value) {
        ActionContext.getContext().put(name, value);
    }
}
