package com.stackroute.restaurantspringboot.services;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.stackroute.restaurantspringboot.domain.Restaurant;
import com.stackroute.restaurantspringboot.exceptions.NullValueException;

public interface RestaurantService {

	public Restaurant addRestaurant(Restaurant restaurant) throws Exception;
	public Iterable<Restaurant> getAllRestaurants() throws NullValueException;
	public Restaurant getRestaurantById(Integer id) throws NullValueException;
	public Restaurant updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int id) throws NullValueException;
	
	public Restaurant[] findByName(String name);
}

