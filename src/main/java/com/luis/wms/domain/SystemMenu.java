package com.luis.wms.domain;

import com.luis.wms.util.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class SystemMenu extends BaseDomain implements JsonObject{
    private String name;
    private String sn;
    private String url;
    private SystemMenu parent;

    @Override
    public String toString() {
        return "SystemMenu{" +
                "name='" + name  +
                ", sn='" + sn +
                ", url='" + url +
                ", parent=" + parent.id +
                '}';
    }

    @Override
    public Object toJson() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("pId",parent.getId());
        map.put("action",url);
        return map;
    }
}
