package com.oms.model;

import java.io.Serializable;


public class ResponseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String message;
	private Object entity;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
}
