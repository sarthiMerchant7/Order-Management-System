package com.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.exception.order.OrderNotFoundException;
import com.oms.model.Order;
import com.oms.model.OrderItemRequest;
import com.oms.model.OrderRequest;
import com.oms.model.ResponseModel;
import com.oms.service.order.OrderService;
import com.oms.validator.OrderValidator;

@RestController
@RequestMapping("/home")
public class OrderController {

	@Autowired(required = true)
	private OrderService orderService;

	@PostMapping("/api/addOrder")
	public ResponseEntity<ResponseModel> addOrder(@RequestBody OrderRequest orderRequest) {
		ResponseModel responseModel = new ResponseModel();
		try {
			Order order = new Order();
			BeanUtils.copyProperties(orderRequest, order);
			List<String> message = OrderValidator.validate(orderRequest);
			if(message.isEmpty()) {
				List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItemRequests();
				orderService.saveOrderDetail(order, orderItemRequests);
				responseModel.setMessage("Order details saves successfully.");
			}else {
				StringBuilder errors = new StringBuilder();
				for(int i = 0; i < message.size(); i++) {
					if(i == 0) {
						errors.append(message.get(i));
					}else {
						errors.append(", ").append(message.get(i));
					}
					
				}
				responseModel.setMessage(errors.toString());
			}
		} catch (Exception ex) {
			responseModel.setMessage("Order details not saved.");
			ex.printStackTrace();
		}

		return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	}

	@GetMapping("/api/getOrders")
	public ResponseEntity<ResponseModel> getOrders() {
		ResponseModel responseModel = new ResponseModel();
		HttpStatus httpStatus = null;
		try {
			List<OrderRequest> orders = orderService.getAllOrders();
			responseModel.setEntity(orders);
			responseModel.setMessage("Order details find successfully.");
			httpStatus = HttpStatus.OK;
		} catch (OrderNotFoundException ex) {
			responseModel.setEntity(new ArrayList<Order>());
			responseModel.setMessage("Order details not available.");
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			responseModel.setEntity(new ArrayList<Order>());
			responseModel.setMessage("Order details not available.");
			httpStatus = HttpStatus.OK;
		}

		return new ResponseEntity<ResponseModel>(responseModel, httpStatus);
	}

	@GetMapping("/api/getOrder/{orderId}")
	public ResponseEntity<ResponseModel> getOrder(@PathVariable(name = "orderId") String orderId) {
		ResponseModel responseModel = new ResponseModel();
		HttpStatus httpStatus = null;
		try {
			if (orderId != null && !"".equals(orderId)) {
				List<OrderRequest> orders = orderService.getOrders(Long.parseLong(orderId));
				
				if (!orders.isEmpty()) {
					responseModel.setEntity(orders);
					responseModel.setMessage("Order detail find successfully.");
					httpStatus = HttpStatus.OK;
				} else {
					responseModel.setMessage("Order detail not available with id : " + orderId);
					httpStatus = HttpStatus.OK;
					throw new OrderNotFoundException();
				}
			} else {
				responseModel.setMessage("Please provide Order id.");
				httpStatus = HttpStatus.OK;
			}
		} catch (OrderNotFoundException ex) {
			responseModel.setMessage("Order details not available for Order id " + orderId);
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			responseModel.setMessage("Order details not available for Order id " + orderId);
			httpStatus = HttpStatus.OK;
		}

		return new ResponseEntity<>(responseModel, httpStatus);
	}

}
