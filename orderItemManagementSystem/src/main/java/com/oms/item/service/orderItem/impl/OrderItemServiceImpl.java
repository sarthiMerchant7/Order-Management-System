package com.oms.item.service.orderItem.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.item.model.OrderItem;
import com.oms.item.model.OrderItemRequest;
import com.oms.item.reposistory.OrderItemReposistory;
import com.oms.item.service.orderItem.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemReposistory orderItemReposistory;

	@Override
	public void saveOrderItemDetails(List<OrderItemRequest> orderItemRequest) throws IllegalStateException, Exception{

		for(OrderItemRequest itemRequest : orderItemRequest) {
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(itemRequest, orderItem);
			orderItemReposistory.save(orderItem);
		}
		
	}

	@Override
	public List<OrderItem> getOrderItemByOrderId(long orderId) {
		return orderItemReposistory.findByOrderId(orderId);
	}
}
