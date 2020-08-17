package com.oms.model;

import java.util.Date;
import java.util.List;

public class OrderRequest {

	private long orderId;
	private String customerName;
	private Date orderDate;
	private String shippingAddress;
	private double total;
	private List<OrderItemRequest> orderItemRequests;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<OrderItemRequest> getOrderItemRequests() {
		return orderItemRequests;
	}

	public void setOrderItemRequests(List<OrderItemRequest> orderItemRequests) {
		this.orderItemRequests = orderItemRequests;
	}

}
