package com.tsri.stock.exception;

public class InternalServerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InternalServerException(String message) {
		super("[InternalServerException] " + message);
	}	
	
}
