package it.pkg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.pkg.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username); 
	
}
