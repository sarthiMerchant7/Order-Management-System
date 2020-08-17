package com.oms.item.exception.orderItem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class OrderExceptionController {

	@ExceptionHandler(value = OrderItemNotFoundException.class)
	public ResponseEntity<Object> exception(OrderItemNotFoundException orderItemNotFoundException) {
		return new ResponseEntity<>("Order item not found.", HttpStatus.NOT_FOUND);
	}

}
