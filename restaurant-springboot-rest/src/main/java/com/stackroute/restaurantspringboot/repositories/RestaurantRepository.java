package com.stackroute.restaurantspringboot.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.restaurantspringboot.domain.Restaurant;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

//	@Autowired
//    MongoTemplate mongoTemplate;
	
	
	Restaurant[] findByNameStartsWith(String name);
	
	
	
}
