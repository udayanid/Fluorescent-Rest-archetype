#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.security.exception;

public class DisabledUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DisabledUserException(String msg) {
		super(msg);
	}
	
}