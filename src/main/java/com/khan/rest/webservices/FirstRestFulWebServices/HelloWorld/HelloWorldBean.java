package com.khan.rest.webservices.FirstRestFulWebServices.HelloWorld;

public class HelloWorldBean {
	
	private String message;
	private String error;
	
	public HelloWorldBean(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + ", error=" + error + "]";
	}


	
	

}
