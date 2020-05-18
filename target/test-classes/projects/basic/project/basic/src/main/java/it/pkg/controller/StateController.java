package it.pkg.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import it.pkg.domain.State;
import it.pkg.exception.CustomErrorType;
import it.pkg.exception.ExceptionCode;
import it.pkg.exception.ResourceNotFoundException;
import it.pkg.service.StateService;

import lombok.extern.slf4j.Slf4j;

//https://www.javaguides.net/2018/09/spring-data-jpa-auditing-with-spring-boot2-and-mysql-example.html
//http://websystique.com/spring-boot/spring-boot-rest-api-example/
@Slf4j
@RestController
@RequestMapping("state")
public class StateController {


	@Autowired
	StateService stateService;
	
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<State> states = stateService.findAllStates();
		return states.isEmpty() ? new ResponseEntity<String>(ExceptionCode.STATE_NO_CONTENT.getMsg(),HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<State>>(states, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
		Optional<State> state = stateService.findById(id);
		if (state.isPresent()) {
			return new ResponseEntity<State>(state.get(), HttpStatus.OK);
		}
		log.info(String.format(ExceptionCode.STATE_NOT_FOUND.getMsg(), id));
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ExceptionCode.STATE_NOT_FOUND.getMsg(), id)),HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> saveState(@Valid @RequestBody State state, UriComponentsBuilder ucBuilder) {
		System.out.println("SAVE-STATE CONTROLLER METHOD IS CALLED");
		if (stateService.findByStateName(state.getStateName()).isPresent()) {
			log.error(String.format(ExceptionCode.STATE_ALREADY_FOUND.getMsg(), state.getStateName()));
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ExceptionCode.STATE_ALREADY_FOUND.getMsg(), state.getStateName())), HttpStatus.CONFLICT);
		}
		stateService.saveState(state);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/state/{id}").buildAndExpand(state.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<?> updateState(@PathVariable(value = "id") Long id, @Valid @RequestBody final State state) {
		Optional<State> optionalState = stateService.findById(id);
		State stateToBeUpdated = null;
		try {
			optionalState.orElseThrow(() -> new ResourceNotFoundException(ExceptionCode.STATE_NOT_FOUND, id));
			stateToBeUpdated = optionalState.get();
			stateToBeUpdated.setStateName(state.getStateName());
			stateToBeUpdated.setDescription(state.getDescription());
			stateService.updateState(stateToBeUpdated);
		} catch (final ResourceNotFoundException e) {
			log.error(String.format(ExceptionCode.STATE_NOT_FOUND.getMsg(), id));
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ExceptionCode.STATE_NOT_FOUND.getMsg(), id)),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<State>(stateToBeUpdated, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteState(@PathVariable(value = "id") final Long id) {
		Optional<State> optionalState = stateService.findById(id);
		try {
			optionalState.orElseThrow(
					() -> new ResourceNotFoundException(ExceptionCode.STATE_UNABLE_DELETE, id));
			stateService.deleteStateById(id);
		} catch (final ResourceNotFoundException e) {
			log.error(String.format(ExceptionCode.STATE_UNABLE_DELETE.getMsg(), id));
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ExceptionCode.STATE_UNABLE_DELETE.getMsg(), id)),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(String.format(ExceptionCode.STATE_DEL_SUCCESS.getMsg(), id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping()
	public ResponseEntity<String> deleteAllStates() {
		log.info(ExceptionCode.STATE_ALL_DEL_SUCCESS.getMsg());
		stateService.deleteAllStates();
		return new ResponseEntity<String>(ExceptionCode.STATE_ALL_DEL_SUCCESS.getMsg(),HttpStatus.NO_CONTENT);
	}
}
