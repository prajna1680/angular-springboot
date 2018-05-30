package com.stackroute.restaurantspringboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.restaurantspringboot.domain.Restaurant;
import com.stackroute.restaurantspringboot.exceptions.NullValueException;
import com.stackroute.restaurantspringboot.exceptions.ValidationException;
import com.stackroute.restaurantspringboot.services.RestaurantService;
import com.stackroute.restaurantspringboot.services.RestaurantServiceImpl;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

	RestaurantService restaurantService;
	
	/**
	 * @param restaurantService
	 */
	@Autowired
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}	
	
	@RequestMapping(value = "/restaurant", method = RequestMethod.POST)
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) throws Exception {
		Restaurant newRestaurant;
			if(restaurant.getId() == 0) {
				logger.info("id was 0");
				throw new ValidationException("NullValueIdexception");
			}
			if(restaurant.getName().length()>15) {
				logger.info("name exceeds 15 character");
				throw new javax.validation.ValidationException("NameMaxCharExceedException");
			}
			if(restaurant.getRating()<1||restaurant.getRating()>5) {
				logger.info("rating range exceeded");
				throw new ValidationException("RatingRangeViolated");
			}
			
			
			logger.info("add method of controller layer");
			newRestaurant = restaurantService.addRestaurant(restaurant);
			return new ResponseEntity<Restaurant>(newRestaurant, HttpStatus.OK);
			
	}
	
	
	@RequestMapping(value = "/restaurants", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Restaurant>> displayRestaurant(Restaurant restaurant) throws NullValueException { 
		logger.info("get all method of controller");
		Iterable<Restaurant> display = restaurantService.getAllRestaurants();
		return new ResponseEntity<Iterable<Restaurant>>(display,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> findRestaurantById(@PathVariable(value = "id") int id) throws NullValueException {
		logger.info("get by ID method of controller");
		Restaurant display = restaurantService.getRestaurantById(id);		
		return new ResponseEntity<Restaurant>(display,HttpStatus.OK);
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant[]> findRestaurantByName(@PathVariable(value="name") String name) throws NullValueException {
		logger.info("get by name method of controller");
		System.out.println(name);
		Restaurant[] display = restaurantService.findByName(name);		
		return new ResponseEntity<Restaurant[]>(display,HttpStatus.OK);
	}
		
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.DELETE)
	public void deleteRestaurant(@PathVariable(value = "id") int id) throws NullValueException {
		
		logger.info("delete method of controler");
		restaurantService.deleteRestaurant(id);
		//return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> updateRestaurantbyId(@PathVariable(value = "id") int id, @RequestBody Restaurant restaurant) {
		logger.info("update method of controller");
		restaurant.setId(id);
		Restaurant updatedRestautant = restaurantService.updateRestaurant(restaurant);
		return new ResponseEntity<Restaurant>(updatedRestautant, HttpStatus.OK);
	}
	
	

}
