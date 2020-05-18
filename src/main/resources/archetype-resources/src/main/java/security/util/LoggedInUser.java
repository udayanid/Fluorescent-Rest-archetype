#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.security.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoggedInUser extends User {
	private static final long serialVersionUID = 4887019124253116698L;

	private Long userId;

	public LoggedInUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Long userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		setUserId(userId);
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoggedInUser [userId=");
		builder.append(userId);
		builder.append(", getAuthorities()=");
		builder.append(getAuthorities());
		builder.append(", getPassword()=");
		builder.append(getPassword());
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append(", isEnabled()=");
		builder.append(isEnabled());
		builder.append(", isAccountNonExpired()=");
		builder.append(isAccountNonExpired());
		builder.append(", isAccountNonLocked()=");
		builder.append(isAccountNonLocked());
		builder.append(", isCredentialsNonExpired()=");
		builder.append(isCredentialsNonExpired());
		builder.append("]");
		return builder.toString();
	}

	
}
