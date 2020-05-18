#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;
import java.util.Optional;

import ${package}.domain.State;

public interface StateService {

	Optional<State> findById(final Long id);

	Optional<State> findByStateName(final String stateName);

	void saveState(final State state);

	void updateState(final State state);

	void deleteStateById(final Long id);

	List<State> findAllStates();

	void deleteAllStates();

	boolean isStateExist(final State state);
}
