package com.oms.service.order;

import java.util.List;

import com.oms.exception.order.OrderNotFoundException;
import com.oms.model.Order;
import com.oms.model.OrderItemRequest;
import com.oms.model.OrderRequest;


public interface OrderService{
	public void saveOrderDetail(Order order, List<OrderItemRequest> orderItems);
	public List<OrderRequest> getAllOrders();
	public List<OrderRequest> getOrders(long orderId) throws OrderNotFoundException, IllegalArgumentException;
}
