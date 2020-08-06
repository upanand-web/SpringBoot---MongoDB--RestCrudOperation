package com.mongdb.javaspringBootmongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongdb.javaspringBootmongodb.entity.Order;
import com.mongdb.javaspringBootmongodb.repository.OrderRepository;



@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	 
	 
	 @PostMapping("createall")
	 @ResponseStatus(HttpStatus.CREATED)
	 List<Order> createAll(@RequestBody List<Order> order){
		 return orderRepository.saveAll(order);
	 }
	 
	
	@PostMapping("createorder")
	@ResponseStatus(HttpStatus.CREATED)
	Order createOrder(@RequestBody Order request) {
		return orderRepository.save(request);
		
	}
	  @GetMapping("getOrder")
	    public List<Order>  getOrders() {
	        Iterable<Order> iterable =  orderRepository.finaAll();
	        List<Order> ls = new ArrayList<>();
	        for(Order d: iterable) {
	        	ls.add(d);
	        }
	        	return ls;
	    }

	    @GetMapping("getOrder/{id}")
	    public ResponseEntity<Order> getOrder(@PathVariable String id) {
	        //Person person = personRepository.findOne(id);
	    	Order order = orderRepository.findById(id);
	        if (order == null)
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        return ResponseEntity.ok(order);
	    }
	    
	    @PutMapping("update")
	    public ResponseEntity<Order> updateOrder(@RequestBody Order updatedOrder ){
	    	Order order  = orderRepository.updateOrderById(updatedOrder );
	    	if(order == null)
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    return	ResponseEntity.ok(order);
	    }
	    
	    @DeleteMapping("delete/{id}")
	    public ResponseEntity<Long> deleteId(@PathVariable String id){
	    	long count = orderRepository.deleteById(id);
	    	if(count == 0) {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	}
	    	return ResponseEntity.ok(count);
	    }
	    
	    @DeleteMapping("deletedorder/{id}")
	    public ResponseEntity<Order> getDeletedOrder(@PathVariable String id){
	    	Order order = orderRepository.deleteByIdReturnOrder(id);
	    	if(order == null)
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	return ResponseEntity.ok(order);
	    }
	    
	

}
