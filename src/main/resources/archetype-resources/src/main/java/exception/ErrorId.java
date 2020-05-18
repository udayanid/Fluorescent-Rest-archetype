#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

public enum ErrorId {

	CODE_201("CODE_201"), 
	CODE_204("CODE_204"),
	CODE_404("CODE_404"),
	CODE_400("CODE_400"),
	CODE_200("CODE_200"),
	CODE_409("CODE_409");
	
	private String errorCode;

	ErrorId(final String errorCode) {
		this.errorCode = errorCode;
	}	
	
    public String getValue(){
        return errorCode;
    }
}

