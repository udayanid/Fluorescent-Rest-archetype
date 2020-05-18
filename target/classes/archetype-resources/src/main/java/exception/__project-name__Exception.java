#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

public class ${project-name}Exception extends Exception {

	private static final long serialVersionUID = 8429014722575617332L;

	public ${project-name}Exception(String message) {
		super(message);
	}

	public ${project-name}Exception(ExceptionCode exceptionCode, final Object... args) {
		super(String.format(exceptionCode.getMsg(), args));
	}
}
