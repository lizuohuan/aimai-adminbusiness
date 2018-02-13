package com.magic.aimai.business.entity;

import java.io.Serializable;

public class PageArgs  implements Serializable {

	//页面大小
	private static int PAGE_SIZE = 10;

	//当前页面(从0开始)
	private int page;

	//页面大小
	private Integer pageSize;

	//排序列名称
	private String column;

	//排列(asc或者desc)
	private String order;

	public PageArgs() {

	}

	public PageArgs(Integer page,Integer pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	/**
	 * 获取其实索引.
	 * @return
	 */
	public int getPageStart() {
		if (pageSize == null || pageSize.intValue() <= 0) {
			pageSize = PAGE_SIZE;
		}

		return page * pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize.intValue() <= 0) {
			pageSize = PAGE_SIZE;
		}

		return pageSize;
	}

	public void setPageSize(Integer pageSize) {

		this.pageSize = pageSize;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

