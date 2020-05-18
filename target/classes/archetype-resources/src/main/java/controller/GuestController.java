#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {
	
	@PreAuthorize("hasAuthority('ROLE_GUEST')")
	@GetMapping("/guest")
	public String guestResponse() {
		return "guest response";
	}
}
