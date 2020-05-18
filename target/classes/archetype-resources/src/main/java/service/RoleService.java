#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;
import java.util.Optional;

import ${package}.domain.Role;

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
