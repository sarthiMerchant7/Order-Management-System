package com.oms.service.order.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.exception.order.OrderNotFoundException;
import com.oms.item.feign.remote.service.OrderItemRemoteService;
import com.oms.model.Order;
import com.oms.model.OrderItemRequest;
import com.oms.model.OrderRequest;
import com.oms.reposistory.OrderReposistory;
import com.oms.service.order.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderReposistory orderReposistory;
	
	@Autowired
	private OrderItemRemoteService orderItemRemoteService;
	
	@Override
	@Transactional
	public void saveOrderDetail(Order order, List<OrderItemRequest> orderItems) {
		Order orderDetails = orderReposistory.save(order);
		List<OrderItemRequest> itemUpdatedData = new ArrayList<>();
		for(OrderItemRequest itemRequest : orderItems) {
			itemRequest.setOrderId(orderDetails.getOrderId());
			itemUpdatedData.add(itemRequest);
		}
		
		orderItemRemoteService.createOrderItem(itemUpdatedData);
		
	}

	@Override
	public List<OrderRequest> getAllOrders() {
		List<Order> orders = (List<Order>) orderReposistory.findAll();
		List<OrderRequest> orderRequest = new ArrayList<OrderRequest>();
		for(Order order : orders) {
			List<OrderItemRequest> orderItem = orderItemRemoteService.getOrderItemByOrderId(order.getOrderId());
			OrderRequest orderDataRequest = new OrderRequest();
			orderDataRequest.setCustomerName(order.getCustomerName());
			orderDataRequest.setOrderDate(order.getOrderDate());
			orderDataRequest.setOrderId(order.getOrderId());
			orderDataRequest.setOrderItemRequests(orderItem);
			orderDataRequest.setShippingAddress(order.getShippingAddress());
			orderDataRequest.setTotal(order.getTotal());
			orderRequest.add(orderDataRequest);
		}
		
		return orderRequest;
	}

	@Override
	public List<OrderRequest> getOrders(long orderId) throws OrderNotFoundException, IllegalArgumentException{
		Optional<Order> orders = orderReposistory.findById(orderId);
		List<OrderRequest> orderRequest = new ArrayList<OrderRequest>();
		
		if(orders.isPresent()) {
			Order order = orders.get();
			List<OrderItemRequest> orderItem = orderItemRemoteService.getOrderItemByOrderId(order.getOrderId());
			OrderRequest orderDataRequest = new OrderRequest();
			  orderDataRequest.setCustomerName(order.getCustomerName());
			  orderDataRequest.setOrderDate(order.getOrderDate());
			  orderDataRequest.setOrderId(order.getOrderId());
			  orderDataRequest.setOrderItemRequests(orderItem);
			  orderDataRequest.setShippingAddress(order.getShippingAddress());
			  orderDataRequest.setTotal(order.getTotal());
			  orderRequest.add(orderDataRequest);
		}
		return orderRequest;
	}

}
