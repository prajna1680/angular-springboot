 package com.stackroute.restaurantspringboot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.restaurantspringboot.domain.Restaurant;
import com.stackroute.restaurantspringboot.exceptions.NullValueException;
import com.stackroute.restaurantspringboot.repositories.RestaurantRepository;
import com.stackroute.restaurantspringboot.services.RestaurantServiceImpl;


public class RestaurantServiceTest {

	private Restaurant restaurant;	
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@InjectMocks
	private RestaurantServiceImpl restaurantService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddRestaurant() throws Exception {
		restaurant = new Restaurant(3, "quizine", "bangalore", "indian", 4);
		when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		Restaurant rest = restaurantService.addRestaurant(restaurant);
		assertEquals(restaurant, rest);
	
	}
	
	@Test
	public void testDeleteRestaurantById() throws NullValueException {
		restaurant = new Restaurant(3, "quizine", "bangalore", "indian", 4);
		Mockito.doNothing().when(restaurantRepository).deleteById(3);
		restaurantService.deleteRestaurant(3);
		verify(restaurantRepository, times(1)).deleteById(3);
	}
	
	@Test
	public void testGetRestaurantById() throws NullValueException {
		restaurant = new Restaurant(5, "quizine", "bangalore", "indian", 4);
		Optional<Restaurant> restOptional = Optional.of(restaurant);
		when(restaurantRepository.findById(5)).thenReturn(restOptional);
		Restaurant actualRestaurant = restaurantService.getRestaurantById(5);
		Optional<Restaurant> actual = Optional.of(actualRestaurant);
		assertEquals(restOptional, actual);
	}
	
	@Test
	public void testGetAllRestaurants() throws NullValueException {
		restaurant = new Restaurant(3, "quizine", "bangalore", "indian", 4);
		Restaurant restaurant1 = new Restaurant(4, "qer", "qwq", "qw", 4);
		List<Restaurant> restList = new ArrayList<Restaurant>(); 		
		restList.add(restaurant);
		restList.add(restaurant1);
		when(restaurantRepository.findAll()).thenReturn(restList);
		List<Restaurant> actual = (List<Restaurant>) restaurantService.getAllRestaurants();
		assertEquals(restList,actual);		
	}
	
	@Test
	public void testUpdateRestaurantById() {
		restaurant = new Restaurant(3, "quizine", "bangalore", "indian", 4);
		when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		Restaurant result = restaurantService.updateRestaurant(restaurant);
		assertEquals(result, restaurant);
	}
}
