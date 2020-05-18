package it.pkg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pkg.domain.Role;
import it.pkg.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<Role> findByName(final String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}

	@Override
	public void updateRole(Role role) {
		saveRole(role);
	}

	@Override
	public void deleteRoleId(Integer id) {
		roleRepository.deleteById(id);

	}

	@Override
	public List<Role> findAllRoles() {
		List<Role> roleList = new ArrayList<Role>();
		roleRepository.findAll().forEach(roleList::add);
		return roleList;
	}

	@Override
	public void deleteAllRoles() {
		roleRepository.deleteAll();
	}

	@Override
	public boolean isRoleExist(Role roleDetail) {
		final Optional<Role> role = findByName(roleDetail.getName());
		return role.isPresent();
	}

}
