package com.stackroute.restaurantspringboot.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="restaurantmongo")
public class Restaurant {
	
	@Id
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String location;
	@NotNull
	private String cuisine;
	@Min(1)
	@Max(5)
	private int rating;
	
	public Restaurant() {}
	
	public Restaurant(int id, String name, String location, String cuisine, int rating) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.cuisine = cuisine;
		this.rating = rating;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
	
	
}
