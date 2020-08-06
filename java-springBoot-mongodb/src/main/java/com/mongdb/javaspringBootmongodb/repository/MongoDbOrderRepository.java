package com.mongdb.javaspringBootmongodb.repository;

import java.util.ArrayList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mongdb.javaspringBootmongodb.entity.Order;
import com.mongdb.javaspringBootmongodb.entity.OrderLines;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;



public  class MongoDbOrderRepository implements OrderRepository {
	
	private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
	
	
	MongoCollection<Order> collectionOrder = null;
	
	
	private ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/order");
	
	//ConnectionString connectionString = new ConnectionString("mongodb://" + username + ":" + password + "@" + host + ":" + port);
	CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
	CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

	MongoClientSettings clientSettings = MongoClientSettings.builder()
	                .applyConnectionString(connectionString)
	                .codecRegistry(codecRegistry)
	                .build();
	MongoClient mongoClient = MongoClients.create(clientSettings);
	MongoDatabase mongoDatabase = mongoClient.getDatabase("order");
	
	
	
	  @PostConstruct void init(){ 
		  collectionOrder = mongoDatabase.getCollection("orderDetail" , Order.class); 
		  }
	  

	 
	
	@Override
	public Order save(Order order) {
		//System.out.println("Inside save "+collectionOrder.count());
		//List<OrderLines> ls = new ArrayList();
		order.setOrderNo(new ObjectId());
		
		//Document document = new Document();
		//document.put("orderNo",order.getOrderNo());
		//document.put("description", order.getOrderDescription());
		//document.put("orderLine",order.getOrderLine());
		//order.setOrderNo(new ObjectId());
		//order.setOrderNo(new ObjectId());
		collectionOrder.insertOne(order);
		return order;
	}

	
	@Override
	public List<Order> saveAll(List<Order> allorder) {
		ArrayList<Document> arrayList = new ArrayList<Document>();
		 collectionOrder.insertMany(allorder);
                return allorder;
            
        
	}

	
	

	
	@Override
	public FindIterable<Order> finaAll() {
		return   collectionOrder.find();
		
	}




	@Override
	public Order findById(String id) {
	Order itr = collectionOrder.find(eq("orderNo", new ObjectId(id))).first(); //here we are doing the filter of 
	return itr;                                          //orderNo which is matching to given ID
	}



	
	

	
	/**
	 * update order
	 */
	@Override
	public Order updateOrderById(Order order) {
		FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
	return collectionOrder.findOneAndReplace(eq("orderNo", order.getOrderNo()) , order , options);
	
	// FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
    //return personCollection.findOneAndReplace(eq("_id", person.getId()), person, options);
		
		
	}
	
	/**
	 * deleting count
	 * @param id
	 * @return
	 */
	@Override
	public long deleteById(String id) {
		return collectionOrder.deleteOne(eq("orderNo" , new ObjectId(id))).getDeletedCount();
	}
	
	/**
	 * Method will delete and return order
	 */
	@Override
	public Order deleteByIdReturnOrder(String id) {
		return collectionOrder.findOneAndDelete(eq("orderNo" , new ObjectId(id)));
	}
	

	

	
	

}
