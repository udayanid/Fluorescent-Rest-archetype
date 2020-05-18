package it.pkg.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.pkg.domain.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Optional<Role> findByName(final String name);
}
