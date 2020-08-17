package com.oms.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.item.model.OrderItem;
import com.oms.item.model.OrderItemRequest;
import com.oms.item.service.orderItem.OrderItemService;

@RestController
@RequestMapping("/service")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	@PostMapping("/api/add-orderItem")
	public void addOrderItem(@RequestBody List<OrderItemRequest> orderItemRequest) throws IllegalStateException, Exception {
		orderItemService.saveOrderItemDetails(orderItemRequest);
	}
	
	@GetMapping("/api/orderItem/{orderId}")
	public List<OrderItem> getOrderItemByOrderId(@PathVariable (name = "orderId") String orderId) {
		System.out.println("orderId :: " + orderId);
		List<OrderItem> items = orderItemService.getOrderItemByOrderId(Long.parseLong(orderId));
		System.out.println(items.size());
		return items;
	}

}
