package com.sample.client;

/*
 * Class OrderException for any presistence exceptions
 */

public class OrderException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Throwable exception;
    private String err_msg;
   
    public OrderException(String err_msg){
        this.err_msg = err_msg;
        System.out.println("Order Exception "+err_msg);
    }
    
    public OrderException(Exception exception){
        this.exception = exception;
        exception.printStackTrace();
    }

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
    
    

}
