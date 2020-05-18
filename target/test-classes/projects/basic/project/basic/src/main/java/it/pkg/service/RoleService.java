package it.pkg.service;

import java.util.List;
import java.util.Optional;

import it.pkg.domain.Role;

public interface RoleService {
	
	Optional<Role> findById(final Integer id);

	Optional<Role> findByName(final String name);

	void saveRole(final Role role);
	
	void updateRole(final Role role);

	void deleteRoleId(final Integer id);

	List<Role> findAllRoles();

	void deleteAllRoles();

	boolean isRoleExist(final Role role);
}
