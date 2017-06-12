package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Depot extends BaseDomain{
    private String name;
    private String location;
}
