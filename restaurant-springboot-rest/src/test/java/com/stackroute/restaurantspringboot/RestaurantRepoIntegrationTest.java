package com.stackroute.restaurantspringboot;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.restaurantspringboot.domain.Restaurant;
import com.stackroute.restaurantspringboot.repositories.RestaurantRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RestaurantRepoIntegrationTest {

	Restaurant restaurant;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Before
	public void clearDb() {
		restaurantRepository.deleteAll();
	}
	
	@Test
	public void testAddRestaurant() {
		restaurant = new Restaurant(5, "hotel", "bangalore", "indian", 3);
		restaurantRepository.save(restaurant);
		assertEquals(5, restaurant.getId());
		restaurantRepository.delete(restaurant);
	}
	
	@Test
	public void testGetAllRestaurants() {
		List<Restaurant> expectedlist= new ArrayList<>();
		restaurant = new Restaurant(4, "hotel", "bangalore", "indian", 3);
		Restaurant restaurant1 = new Restaurant(5, "lounge", "bangalore", "indian", 3);
		expectedlist.add(restaurant);
		expectedlist.add(restaurant1);
		restaurantRepository.save(restaurant);
		restaurantRepository.save(restaurant1);
		List<Restaurant> received = (List<Restaurant>) restaurantRepository.findAll();
		assertEquals(expectedlist.size(), received.size());
	}
	
	@Test
	public void testGetRestaurantById() {
		restaurant = new Restaurant(7, "hotel", "bangalore", "indian", 3);
		restaurantRepository.findById(7);
		assertEquals(7, restaurant.getId());
	
		
	}
	
	@Test
	public void testUpdateRestaurant() {
		restaurant = new Restaurant(4, "lounge", "bangalore", "indian", 3);
		restaurantRepository.save(restaurant);
		assertEquals("lounge", restaurant.getName());
	}
	
	
	@Test
	public void testDeleteRestaurant() {
		restaurant = new Restaurant(1, "hotel", "bangalore", "indian", 3);
		restaurantRepository.deleteById(1);
		assertEquals(Optional.empty(),restaurantRepository.findById(1));
	}
}
	
	
	
	
	
	

