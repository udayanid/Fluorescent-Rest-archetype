package it.pkg.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.pkg.exception.CustomErrorType;
import it.pkg.exception.ExceptionCode;
import it.pkg.exception.${project-name}Exception;
import it.pkg.exception.ResourceNotFoundException;
import it.pkg.security.exception.DisabledUserException;
import it.pkg.security.exception.InvalidUserCredentialsException;
import it.pkg.security.service.QrCodeForMeUserDetailService;
import it.pkg.security.util.JwtUtil;
import it.pkg.security.view.AuthenticationRequest;
import it.pkg.security.view.AuthenticationResponse;
import it.pkg.service.UserService;
import it.pkg.view.UserRequestModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JwtAuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private QrCodeForMeUserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String HelloWorld() {
		return "Default Page for the CostEstimator Application";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody @Valid UserRequestModel userRequest) {
		log.debug("UserRequestModel ::{}", userRequest);
		try {
			userService.userRegisteration(userRequest);
		} catch (ResourceNotFoundException e) {
			log.error(ExceptionCode.ROLE_NOT_FOUND.getMsg());
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(ExceptionCode.ROLE_NOT_FOUND.getMsg()),
					HttpStatus.NOT_FOUND);
		} catch (${project-name}Exception e) {
			log.error(String.format(ExceptionCode.USER_ALREADY_FOUND.getMsg(), userRequest.getUsername()));
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType(
							String.format(ExceptionCode.USER_ALREADY_FOUND.getMsg(), userRequest.getUsername())),
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("User inserted successfully", HttpStatus.OK);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		log.debug("Before Authentication ::{}", authenticationRequest);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new DisabledUserException("USER_INACTIVE");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("INVALID_CREDENTIALS");
		}
		log.debug("After authentication......................");
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
