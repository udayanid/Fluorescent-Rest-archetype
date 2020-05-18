package it.pkg.exception;

public class CustomErrorType {
	 
    private String errorMessage;
 
    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 }