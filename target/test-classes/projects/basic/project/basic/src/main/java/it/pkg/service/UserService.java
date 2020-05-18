package it.pkg.service;

import java.util.List;
import java.util.Optional;

import it.pkg.domain.User;
import it.pkg.exception.${project-name}Exception;
import it.pkg.exception.ResourceNotFoundException;
import it.pkg.view.UserRequestModel;

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
