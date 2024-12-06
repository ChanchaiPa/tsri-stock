package com.tsri.stock.exception;

public class AdapterParsingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AdapterParsingException(String message) {
		super("[AdapterParsingException] " + message);
	}

}
