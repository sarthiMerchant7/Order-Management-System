package com.oms.item.feign.remote.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oms.model.OrderItem;
import com.oms.model.OrderItemRequest;

@FeignClient(name = "order-items", url = "http://localhost:8090/service")
public interface OrderItemRemoteService {

	@RequestMapping(method = RequestMethod.GET, value = "/api/orderItem/{orderItemId}")
	public OrderItem getOrderItemById(@PathVariable(value = "orderItemId") long orderItemId);

	@RequestMapping(method = RequestMethod.GET, value = "/api/orderItem/{orderId}")
	public List<OrderItemRequest> getOrderItemByOrderId(@PathVariable(value = "orderId") long orderId);

	@RequestMapping(method = RequestMethod.POST, value = "/api/add-orderItem")
	public void createOrderItem(@RequestBody List<OrderItemRequest> orderItems);
}
