package com.luis.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class PageResult {

	private Integer pageSize;
	private Integer currentPage;
	private Integer prevPage;
	private Integer nextPage;
	private Integer totalCount;
	private Integer totalPage;
	private List<?> listData;
	public PageResult(Integer pageSize, Integer currentPage, Integer totalCount, List<?> listData) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.listData = listData;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.prevPage = currentPage - 1 >= 1 ? currentPage - 1 : 1; 
		this.nextPage = currentPage + 1 <= totalPage ? currentPage + 1 : totalPage;
	}
	@Override
	public String toString() {
		return "PageResult [pageSize=" + pageSize + ", currentPage=" + currentPage + ", prevPage=" + prevPage
				+ ", nextPage=" + nextPage + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", listData="
				+ listData + "]";
	}


}
