package it.pkg.security.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.pkg.domain.User;
import it.pkg.security.util.LoggedInUser;
import it.pkg.service.UserService;

@Service
public class QrCodeForMeUserDetailService implements UserDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(QrCodeForMeUserDetailService.class);

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User ::" + username + " not found"));
		logger.debug("findbyusername::{}",user);
	   return new LoggedInUser(user.getUsername(), user.getPassword(),true,true,true,true,getAuthority(user),user.getId());
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				getAuthority(user));
	}

	private Set<? extends GrantedAuthority> getAuthority(User user) {
		Set<GrantedAuthority> authorities = new HashSet<>();
        logger.debug("User Details::{}",user.toString());
        logger.debug("User Roles::{}",user.getRoles());
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		});
		return authorities;
	}

}
