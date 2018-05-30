package com.stackroute.restaurantspringboot;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stackroute.restaurantspringboot.controller.RestaurantController;
import com.stackroute.restaurantspringboot.domain.Restaurant;
import com.stackroute.restaurantspringboot.services.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RestaurantService restaurantServiceMock;
	
	@Test
	public void testAddRestaurant() throws Exception {
		Restaurant mockRestaurant = new Restaurant(3, "quizine", "bangalore", "indian", 4);
		String restaurantJson = "{\"id\":\"3\",\"name\":\"quizine\",\"location\":\"bangalore\",\"cuisine\":\"indian\",\"rating\":\"4\"}";
		Mockito.when(restaurantServiceMock.addRestaurant(Mockito.any(Restaurant.class))).thenReturn(mockRestaurant);	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/restaurant").accept(MediaType.APPLICATION_JSON).content(restaurantJson).contentType(MediaType.APPLICATION_JSON) ;
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();	
		String expected = "{id:3,name:quizine,location:bangalore,cuisine:indian,rating:4}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);		
	}

	
	@Test
	public void testDisplayRestaurants() throws Exception {
		Restaurant restaurant = new Restaurant(1, "s", "s", "s", 1);
		Restaurant restaurant1 = new Restaurant(2, "q", "q", "q", 4);
		List<Restaurant> restList = new ArrayList<Restaurant>(); 		
		restList.add(restaurant);
		restList.add(restaurant1);
		String restaurantJson = "{\"id\":\"1\",\"name\":\"s\",\"location\":\"s\",\"cuisine\":\"s\",\"rating\":\"1\"},{\"id\":\"2\",\"name\":\"q\",\"location\":\"q\",\"cuisine\":\"q\",\"rating\":\"4\"}";

		Mockito.when(restaurantServiceMock.getAllRestaurants()).thenReturn(restList);		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/restaurants").accept(MediaType.APPLICATION_JSON).content(restaurantJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();		
		String expected = "[{id:1,name:s,location:s,cuisine:s,rating:1},{id:2,name:q,location:q,cuisine:q,rating:4}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);		
	}
	
	@Test
	public void testDeleteRestaurantByID() throws Exception {
		new Restaurant(1, "quizine", "bangalore", "chinese", 4);
		doNothing().when(restaurantServiceMock).deleteRestaurant(1);
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/restaurant/1");		
		mockMvc.perform(delete("/api/v1/restaurant/{id}", 1)).andExpect(status().isOk());
		//verify(restaurantServiceMock, times(1)).deleteRestaurant(user.getId());
		
		
	}
	
	
	@Test
	public void testRestaurantById() throws Exception {
		Restaurant user = new Restaurant(1, "quizine", "bangalore", "chinese", 4);
        String restaurantJson1 = "{\"id\":\"1\",\"name\":\"quizine\",\"location\":\"bangalore\",\"cuisine\":\"chinese\",\"rating\":\"4\"}";
        Mockito.when(restaurantServiceMock.getRestaurantById(1)).thenReturn(user);	
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/restaurant/1").accept(MediaType.APPLICATION_JSON).content(restaurantJson1).contentType(MediaType.APPLICATION_JSON) ;
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();  
        String expected = "{id:1,name:quizine,location:bangalore,cuisine:chinese,rating:4}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testUpdaterestaurant() throws Exception {
	
		Restaurant user = new Restaurant(1, "quizine", "bangalore", "chinese", 4);
        String restaurantJson1 = "{\"id\":\"1\",\"name\":\"quizine\",\"location\":\"bangalore\",\"cuisine\":\"chinese\",\"rating\":\"4\"}";
        Mockito.when(restaurantServiceMock.updateRestaurant(Mockito.any(Restaurant.class))).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/restaurant/1").accept(MediaType.APPLICATION_JSON).content(restaurantJson1).contentType(MediaType.APPLICATION_JSON);
		restaurantServiceMock.updateRestaurant(user);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{id:1,name:quizine,location:bangalore,cuisine:chinese,rating:4}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}

