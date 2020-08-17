package com.oms.validator;

import java.util.ArrayList;
import java.util.List;

import com.oms.model.OrderRequest;

public class OrderValidator {
	
	public static List<String> validate(OrderRequest orderRequest){
		List<String> msg = new ArrayList<String>(); 
		if(orderRequest != null) {
			if(orderRequest.getCustomerName() == null || orderRequest.getCustomerName().equals("")) {
				msg.add("Please provide valid customer name.");
			}else if(orderRequest.getOrderDate() == null) {
				msg.add("Please provide valid order date.");
			}else if(orderRequest.getShippingAddress() == null || orderRequest.getShippingAddress().equals("")){
				msg.add("Please provide valid shipping address.");
			}else if(orderRequest.getTotal() == 0) {
				msg.add("Please provide valid total amount.");
			}else if(orderRequest.getOrderItemRequests().isEmpty()) {
				msg.add("Please provide order item details.");
			}
		}else {
			msg.add("Please provide Order data.");
		}
		
		return msg;
	}
	
}
