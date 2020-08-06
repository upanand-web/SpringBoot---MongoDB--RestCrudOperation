package com.mongdb.javaspringBootmongodb.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.mongdb.javaspringBootmongodb.entity.Order;

@Repository
@Configuration
public interface OrderRepository {
	
	Order save(Order order);
	
	 List<Order> saveAll(List<Order> allorder);
	
	 Order findById(String id);
	
	 Iterable<Order> finaAll();

	Order updateOrderById(Order order);
	
	Order deleteByIdReturnOrder(String id);
	
	long deleteById(String id);

	
	
	
	

}
