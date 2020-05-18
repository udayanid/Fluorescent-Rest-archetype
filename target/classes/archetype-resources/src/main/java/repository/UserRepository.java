#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${package}.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username); 
	
}
