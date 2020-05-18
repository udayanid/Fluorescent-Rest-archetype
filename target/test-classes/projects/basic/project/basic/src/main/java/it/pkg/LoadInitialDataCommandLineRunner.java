package it.pkg;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.pkg.domain.Role;
import it.pkg.domain.User;
import it.pkg.service.RoleService;
import it.pkg.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadInitialDataCommandLineRunner implements CommandLineRunner {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Override
	public void run(String... args) throws Exception {
		// adding basic role		
		  Role role1 = new Role(null, "ADMIN", "Admin Role", null); Role role2 = new
		  Role(null, "USER", "User Role", null); Role role3 = new Role(null, "GUEST",
		  "Guest Role", null); roleService.saveRole(role1);
		  roleService.saveRole(role2); roleService.saveRole(role3); 
		  //creating role set
		  Set<Role> roleSet1=new HashSet<>(); roleSet1.add(role1); roleSet1.add(role2);
		  Set<Role> roleSet2=new HashSet<>(); roleSet2.add(role2); 
		  //adding default user to the api service 
		  User user1=new User(null, "sathish",
		  encoder.encode("password"), roleSet1); log.debug("user1 :: {}",user1);
		  userService.saveUser(user1); User user2=new
		  User(null,"udayan",encoder.encode("password"), roleSet2);
		  log.debug("user2 :: {}",user2); userService.saveUser(user2);
		 
	}

}
