package com.mongdb.javaspringBootmongodb.entity;

import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonInclude(Include.NON_NULL)
public class Order {
	
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId orderNo;
	private String orderDescription;
	private List<OrderLines> orderLine;
	
	public ObjectId getOrderNo() {
		return orderNo;
	}
	public Order setOrderNo(ObjectId orderNo) {
		this.orderNo = orderNo;
		return this;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public Order setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
		return this;
	}
	public List<OrderLines> getOrderLine() {
		return orderLine;
	}
	public Order setOrderLine(List<OrderLines> orderLine) {
		this.orderLine = orderLine;
		return this;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(orderNo,orderDescription,orderLine);
	}
	@Override
	public String toString() {
		return "Order{orderNo=" + orderNo + ", orderDescription=" + orderDescription+", orderLine="
				+ orderLine
				+ "}";
	}
	
	
	
	
	
	
	
	
	

}
