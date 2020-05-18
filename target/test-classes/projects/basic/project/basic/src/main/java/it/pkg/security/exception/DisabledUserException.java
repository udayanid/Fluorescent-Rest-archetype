package it.pkg.security.exception;

public class DisabledUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DisabledUserException(String msg) {
		super(msg);
	}
	
}