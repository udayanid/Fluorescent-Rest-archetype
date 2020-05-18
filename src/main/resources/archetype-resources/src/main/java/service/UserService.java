#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;
import java.util.Optional;

import ${package}.domain.User;
import ${package}.exception.${project-name}Exception;
import ${package}.exception.ResourceNotFoundException;
import ${package}.view.UserRequestModel;

public interface UserService {

	Optional<User> findByUsername(final String username);
	
	void saveUser(final User user);
	
	void userRegisteration(final UserRequestModel userRequestModel) throws ResourceNotFoundException, ${project-name}Exception;
	
	void updateUser(final User user);

	void deleteUserId(final Long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	boolean isUserExist(final User user);
}
