#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.domain.State;
import ${package}.repository.StateRepository;

@Service("stateService")
public class StateServiceImpl implements StateService {
	@Autowired
	StateRepository stateRepository;

	@Override
	public Optional<State> findById(final Long id) {
		return stateRepository.findById(id);
	}

	@Override
	public Optional<State> findByStateName(final String stateName) {
		return stateRepository.findByStateName(stateName);
	}

	@Override
	public void saveState(final State state) {
		stateRepository.save(state);
	}

	@Override
	public void updateState(final State state) {
		saveState(state);
	}

	@Override
	public void deleteStateById(final Long id) {
		stateRepository.deleteById(id);
	}

	@Override
	public List<State> findAllStates() {
		return stateRepository.findAll();
	}

	@Override
	public void deleteAllStates() {
		stateRepository.deleteAll();
	}

	@Override
	public boolean isStateExist(State stateDetail) {
		final Optional<State> state = findByStateName(stateDetail.getStateName());
		return state.isPresent();
	}
}
