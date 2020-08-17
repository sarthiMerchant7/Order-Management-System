package com.oms.reposistory;

import org.springframework.data.repository.CrudRepository;

import com.oms.model.Order;

public interface OrderReposistory extends CrudRepository<Order, Long>{
}
