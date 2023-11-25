package com.example.book.Exception;

public enum Errorr 
{

	DATA_NOT_FOUND("001","not found");
	
	private String code;
	private String  message;
	
	Errorr(String code,String message)
	{
		this.code=code;
		this.message=message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
