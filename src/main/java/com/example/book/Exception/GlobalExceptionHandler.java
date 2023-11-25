package com.example.book.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		ErrorResponse er = new ErrorResponse();
				
			er.setCode(ex.getCode());
			er.setMessage(ex.getMessage());
			return er;
				
	}
    
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException()
	{
		ErrorResponse er = new ErrorResponse();
		er.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		er.setMessage("Something Went Wrong");
		return er;
	}
	
}
