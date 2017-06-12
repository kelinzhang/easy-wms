package com.luis.wms.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class Product extends BaseDomain{
    private String name;
    private String sn;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private String imagePath;
    private String intro;

    private Brand brand;

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("brandName",brand.getName());
        map.put("costPrice",costPrice);
        map.put("salePrice",salePrice);
        return JSON.toJSONString(map);
    }

    public String getSmallImage(){
        if (imagePath == null && "".equals(imagePath)) {
            return "";
        }
        int index = imagePath.lastIndexOf(".");
        return imagePath.substring(0,index)+"_small"+imagePath.substring(index);
    }
}
