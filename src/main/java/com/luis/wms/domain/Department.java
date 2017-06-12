package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Department extends BaseDomain{

	private String name;
	private String sn;
	
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", sn=" + sn + "]";
	}
}
