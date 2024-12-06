package com.tsri.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String handlerDataNotFound(DataNotFoundException e) {
		return e.getMessage();	
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerAdapterParsing(AdapterParsingException e) {
		return e.getMessage();
	}	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerReqValidate(ReqValidateException e) {
		return e.getMessage();
	}	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String handlerInternalServer(InternalServerException e) {
		return e.getMessage();
	}		
	
}
