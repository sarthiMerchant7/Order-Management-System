package com.oms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oms.exception.order.OrderNotFoundException;
import com.oms.exception.orderItem.OrderItemNotFoundException;
import com.oms.model.ResponseModel;

@ControllerAdvice
public class OrderExceptionController {
	
	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<Object> exception(OrderNotFoundException orderNotFoundException){
		ResponseModel responseModel = new ResponseModel();
		responseModel.setMessage("Order not found.");
		return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderItemNotFoundException.class)
	public ResponseEntity<Object> exception(OrderItemNotFoundException orderItemNotFoundException){
		ResponseModel responseModel = new ResponseModel();
		responseModel.setMessage("Order item not found.");
		return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
	}
	
}
