package it.pkg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 5754133648751492843L;

	public ResourceNotFoundException(ExceptionCode exceptionCode) {
		super(exceptionCode.getMsg());
	}
	
	public ResourceNotFoundException(ExceptionCode exceptionCode, Object... args) {

		super(String.format(exceptionCode.getMsg(), args));
	}

}