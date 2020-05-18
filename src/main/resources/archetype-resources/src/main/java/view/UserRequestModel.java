#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.view;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel implements Serializable{
	private static final long serialVersionUID = -8846593972116954739L;
	@NotBlank
	private String username;
	@NotBlank
	@Size(min=6, max=30)
	private String password;
	private List<Integer> roles;
	
}
