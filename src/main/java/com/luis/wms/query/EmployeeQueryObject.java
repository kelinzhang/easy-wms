package com.luis.wms.query;

import com.luis.wms.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EmployeeQueryObject extends QueryObject{

	private String keyword;
	private Long deptId = -1L;
	
	public void customizedQuery(){
		if (StringUtil.hasLength(keyword)) {
			String word = "%" + keyword + "%";
			super.addQuery(" (obj.name like ? or obj.email like ?) ", word,word);
		}
		if (deptId > 0) {
			System.out.println(deptId);
			super.addQuery(" obj.dept.id = ?", deptId);
		}
	}
}
