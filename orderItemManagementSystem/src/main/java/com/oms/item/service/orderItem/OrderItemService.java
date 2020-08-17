package com.oms.item.service.orderItem;

import java.util.List;

import com.oms.item.model.OrderItem;
import com.oms.item.model.OrderItemRequest;

public interface OrderItemService {
		public void saveOrderItemDetails(List<OrderItemRequest> orderItemRequest) throws IllegalStateException, Exception;
		public List<OrderItem> getOrderItemByOrderId(long orderId);
}
