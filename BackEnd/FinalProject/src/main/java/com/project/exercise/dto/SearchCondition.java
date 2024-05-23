package com.project.exercise.dto;

public class SearchCondition {
	private String searchItem;
	private String searchValue;
	private String orderSt;
	private String orderByDir;
	private String category;
	
	public SearchCondition() {

	}
	
	public SearchCondition(String searchItem, String searchValue, String orderSt, String orderByDir, String category) {
		this.searchItem = searchItem;
		this.searchValue = searchValue;
		this.orderSt = orderSt;
		this.orderByDir = orderByDir;
		this.category = category;
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getOrderSt() {
		return orderSt;
	}

	public void setOrderSt(String orderSt) {
		this.orderSt = orderSt;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SearchCondition [searchItem=" + searchItem + ", searchValue=" + searchValue + ", orderSt=" + orderSt
				+ ", orderByDir=" + orderByDir + "]";
	}

	
}
