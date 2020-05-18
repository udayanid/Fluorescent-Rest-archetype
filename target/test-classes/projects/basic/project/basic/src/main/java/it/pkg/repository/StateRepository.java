package it.pkg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import it.pkg.domain.State;

@Repository("stateRepository")
public interface StateRepository extends JpaRepository<State, Long> {

	public List<State> findByDescription(String description);

	public Optional<State> findByStateName(String stateName);

	@Query("select s from State s where s.stateName=:stateName and s.description like %:description%")
	public List<State> findByStateAndDescription(@PathVariable(value = "stateName") String stateName,
			@PathVariable(value = "description") String description);

}

//https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
//@Query("SELECT e FROM Employee e WHERE e.age = :age")
//public List findByAge(@Param("age") int age);