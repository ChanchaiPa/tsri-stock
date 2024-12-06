package com.tsri.stock.exception;

public class ReqValidateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReqValidateException(String message) {
		super("[ReqValidateException] " + message);
	}

}
