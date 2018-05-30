package com.stackroute.restaurantspringboot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.restaurantspringboot.domain.Restaurant;

@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	
	@Test
	public void testAddRestaurant() {
		ResponseEntity<Restaurant> responseEntity = testRestTemplate.postForEntity("/api/v1/restaurant", new Restaurant(1, "hotel", "bangalore", "indian", 2), Restaurant.class);
	        Restaurant response = responseEntity.getBody();
	        assertEquals("hotel", response.getName());
	}
	
	@Test
	public void testgetRestaurantById() {
		testRestTemplate.postForEntity("/api/v1/restaurant", new Restaurant(1, "hotel", "bangalore", "indian", 2), Restaurant.class);
		ResponseEntity<Restaurant> responseEntity1 = testRestTemplate.getForEntity("/api/v1/restaurant/{id}", Restaurant.class,1);
	        Restaurant response = responseEntity1.getBody();
	        assertEquals(1, response.getId());
	}
	
	@Test
	public void getAllRestaurants() {
		 testRestTemplate.postForEntity("/api/v1/restaurant", new Restaurant(1, "hotel", "bangalore", "indian", 2), Restaurant.class);
		 testRestTemplate.postForEntity("/api/v1/restaurant", new Restaurant(2, "hotel", "bangalore", "indian", 2), Restaurant.class);

		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/restaurants", String.class);
        String response = responseEntity.getBody();
        assertNotNull(response);	
	}

	@Test
	public void deleteRestaurant() {
		testRestTemplate.postForEntity("/api/v1/restaurant", new Restaurant(1, "hotel", "bangalore", "indian", 2), Restaurant.class);
		testRestTemplate.delete("/api/v1/restaurant/{id}",1, Restaurant.class);
		ResponseEntity<Restaurant> responseEntity = testRestTemplate.getForEntity("/api/v1/restaurant/{id}", Restaurant.class, 1);
		 Restaurant response = responseEntity.getBody();
	      assertNull(response);
	}
	
	@Test
	public void updateRestaurant() {
		testRestTemplate.postForEntity("/api/v1/restaurant", new Restaurant(5, "rest", "bangalore", "indian", 2), Restaurant.class);
		testRestTemplate.put("/api/v1/restaurant/5", new Restaurant(5, "lounge", "bangalore", "indian", 2), Restaurant.class);
		ResponseEntity<Restaurant> responseEntity = testRestTemplate.getForEntity("/api/v1/restaurant/{id}", Restaurant.class, 5);
		 Restaurant response = responseEntity.getBody();
		 assertEquals("lounge", response.getName());
	
	}
	
}
