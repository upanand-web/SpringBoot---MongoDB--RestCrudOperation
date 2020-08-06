package com.mongdb.javaspringBootmongodb.entity;

import java.util.Objects;

public class OrderLines {
	
	private String name;
	private double price;
	private String description;
	
	public OrderLines() {
		
	}
	
	public String getName() {
		return name;
	}
	public OrderLines setName(String name) {
		this.name = name;
		return this;
	}
	public double getPrice() {
		return price;
	}
	public OrderLines setPrice(double price) {
		this.price = price;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public OrderLines setDescription(String description) {
		this.description = description;
		return this;
	}
	@Override
	public String toString() {
		return "OrderLines{name=" + name + ", price=" + price + ", description=" + description + "}";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name , price , description);
	}

}
