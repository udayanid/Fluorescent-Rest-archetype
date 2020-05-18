package it.pkg.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.pkg.domain.Role;
import it.pkg.domain.User;
import it.pkg.exception.ExceptionCode;
import it.pkg.exception.${project-name}Exception;
import it.pkg.exception.ResourceNotFoundException;
import it.pkg.repository.UserRepository;
import it.pkg.view.UserRequestModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(final User user) {
		userRepository.save(user);
	}

	@Override
	public void userRegisteration(UserRequestModel userRequestModel) throws ResourceNotFoundException, ${project-name}Exception {
		Optional<User> optionalUser=userRepository.findByUsername(userRequestModel.getUsername());
		log.debug("if user already found: {}",optionalUser);
		if(optionalUser.isPresent()) {
			throw new ${project-name}Exception(ExceptionCode.USER_ALREADY_FOUND, userRequestModel.getUsername());
		}		
		User user = new User();
		BeanUtils.copyProperties(userRequestModel, user);
		log.debug("user after copied properties from userrequest model ::{}", user);
		user.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
		user.setRoles(getRoleSet(userRequestModel.getRoles()));
		userRepository.save(user);
	}

	private Set<Role> getRoleSet(List<Integer> roles) throws ResourceNotFoundException {
		final Set<Role> roleSet = new HashSet<>();
		try {
			roles.forEach(role -> {
				//roleSet.add(roleService.findByName(role).get());
				roleSet.add(roleService.findById(role).get());
			});
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(ExceptionCode.ROLE_NOT_FOUND, roles);
		}
		return roleSet;
	}

	@Override
	public void updateUser(User user) {
		saveUser(user);
		
	}

	@Override
	public void deleteUserId(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
		
	}

	@Override
	public boolean isUserExist(User user) {
		final Optional<User> optionalUser = findByUsername(user.getUsername());
		return optionalUser.isPresent();
	}

}
