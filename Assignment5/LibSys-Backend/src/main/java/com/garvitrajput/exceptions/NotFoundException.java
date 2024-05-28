package com.garvitrajput.exceptions;

public class NotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException (String message) {
		super(message);
	}

	public NotFoundException(int authorid) {
		// TODO Auto-generated constructor stub
	}

}
