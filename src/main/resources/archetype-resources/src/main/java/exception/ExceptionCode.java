#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

public enum ExceptionCode {

	STATE_ALREADY_FOUND(ErrorId.CODE_409, "Unable to create. A State with name %s already exist"),
	STATE_NOT_FOUND(ErrorId.CODE_404, "Given state id::%d is not found"),
	STATE_NO_CONTENT(ErrorId.CODE_204, "No State Found"),
	STATE_UNABLE_DELETE(ErrorId.CODE_400, "Unable to Delete, since the given State id ::%d not found"),
	STATE_DEL_SUCCESS(ErrorId.CODE_200, "Given State id ::%d deleted"),
	STATE_ALL_DEL_SUCCESS(ErrorId.CODE_200, "All States have been Deleted"),
	
	ROLE_ALREADY_FOUND(ErrorId.CODE_409, "Unable to create. A Role with name %s already exist"),
	ROLE_NOT_FOUND(ErrorId.CODE_404, "Given Role id :: %d is not found"),
	ROLE_NO_CONTENT(ErrorId.CODE_204, "No Such Role Found"),
	ROLE_UNABLE_DELETE(ErrorId.CODE_400, "Unable to Delete, since the given Role id ::%d not found"),
	ROLE_DEL_SUCCESS(ErrorId.CODE_200, "Given Role id :: %d deleted"),
	ROLE_ALL_DEL_SUCCESS(ErrorId.CODE_200, "All Roles have been Deleted"),
	
	USER_ALREADY_FOUND(ErrorId.CODE_409, "Unable to create a new user. A User with name %s already exist"),
	USER_NOT_FOUND(ErrorId.CODE_404, "Given User id :: %d is not found"),
	USER_UNABLE_DELETE(ErrorId.CODE_400, "Unable to Delete, since the given User id ::%d not found"),
	USER_DEL_SUCCESS(ErrorId.CODE_200, "Given User id :: %d deleted"),
	USER_NO_CONTENT(ErrorId.CODE_204, "No Such User Found"),
	USER_ALL_DEL_SUCCESS(ErrorId.CODE_200, "All User have been Deleted");

	private final String id;
	private final String msg;

	ExceptionCode(final ErrorId errorCode, final String msg) {
		this.id = errorCode.getValue();
		this.msg = msg;
	}

	public String getId() {
		return this.id;
	}

	public String getMsg() {
		return msg;
	}
}
