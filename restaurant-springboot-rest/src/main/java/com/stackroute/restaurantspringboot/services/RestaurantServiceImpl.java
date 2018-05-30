package com.stackroute.restaurantspringboot.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.restaurantspringboot.domain.Restaurant;
import com.stackroute.restaurantspringboot.exceptions.NullValueException;
import com.stackroute.restaurantspringboot.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	
	private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);
	
	
	RestaurantRepository restaurantRepository;
	
	
	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		super();
		this.restaurantRepository = restaurantRepository;
	}

	@PostConstruct
	public void populateData() {
		Restaurant data1 = new Restaurant(11, "q", "q", "q", 2);
		Restaurant data2 = new Restaurant(12, "a", "a", "a", 3);
		List<Restaurant> restList = new ArrayList<>();
		restList.add(data1);
		restList.add(data2);
		restaurantRepository.saveAll(restList);
	}
	
	
	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		
		try {
		logger.info("add method of service layer");

		restaurantRepository.save(restaurant);
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return restaurant;
	}

	@Override
	public Iterable<Restaurant> getAllRestaurants() throws NullValueException {
		logger.info("get all method of service");
		
		Iterable<Restaurant> restaurantList = restaurantRepository.findAll();
		
		if(restaurantList.equals(null)) {
			logger.info("database is empty");
			throw new NullValueException("database has no entries");
		}
		
		return restaurantList;
		
	}

	@Override
	public void deleteRestaurant(int id) throws NullValueException {
				
		logger.info("delete method of service");
		if(id == 0) {
			logger.info("entered id 0");
			throw new NullValueException("entered value for id is 0");
		}
		restaurantRepository.deleteById(id);
	}

	@Override
	public Restaurant getRestaurantById(Integer id) throws NullValueException {
		logger.info("get by ID method of service");
		if(id.equals(0)) {
			logger.info("entered id 0");
			throw new NullValueException("enterd id was 0");
		}
		Restaurant foundRestaurant = restaurantRepository.findById(id).orElse(null);
		return foundRestaurant;
	}

	public Restaurant[] findByName(String name) {
		Restaurant searchResult[] = restaurantRepository.findByNameStartsWith(name);
		return searchResult;
	}
	
	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {
		logger.info("get update method of service");

		Restaurant updateRestaurant = restaurantRepository.save(restaurant);
		return updateRestaurant;
	}

	

	
	
	
}
