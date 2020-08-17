package com.oms.item.reposistory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oms.item.model.OrderItem;

public interface OrderItemReposistory extends CrudRepository<OrderItem, Long>{
	public List<OrderItem> findByOrderId(long id);
}
