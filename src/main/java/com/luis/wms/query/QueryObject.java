package com.luis.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryObject {

	@Getter@Setter
	private Integer currentPage = 1;
	@Getter@Setter
	private Integer pageSize = 5;
	
	private List<String> conditions = new ArrayList<>();
	@Getter
	private List<Object> params = new ArrayList<>();
	
	public String getQuery(){
		StringBuilder sb = new StringBuilder(40);
		this.customizedQuery();
		if (conditions.size() > 0) {
			String join = StringUtils.join(conditions, " and ");
			return sb.append(" where ").append(join).toString();
		}
		return "";
	}
	
	protected void customizedQuery() {
	}

	public void addQuery(String condition,Object...args){
		conditions.add(condition);
		params.addAll(Arrays.asList(args));
	}
}
