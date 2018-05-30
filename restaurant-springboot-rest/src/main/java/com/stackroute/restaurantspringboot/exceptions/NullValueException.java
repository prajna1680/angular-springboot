package com.stackroute.restaurantspringboot.exceptions;

public class NullValueException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullValueException() {
		super();
}
	
	public NullValueException(String message) {
		super(message);
}

}
