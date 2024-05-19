package com.project.exercise.dto;

public class OrderCondition {
	private String orderSt;
	private String orderByDir;
	
	public OrderCondition() {

	}
	
	public OrderCondition(String orderSt, String orderByDir) {
		this.orderSt = orderSt;
		this.orderByDir = orderByDir;
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

	@Override
	public String toString() {
		return "SearchCondition [orderSt=" + orderSt + ", orderByDir=" + orderByDir + "]";
	}
}
