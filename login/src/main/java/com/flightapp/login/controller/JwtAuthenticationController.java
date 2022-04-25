package com.flightapp.login.controller;

import com.flightapp.login.Response.ErrorResponse;
import com.flightapp.login.Response.JwtRequest;
import com.flightapp.login.Response.JwtResponse;
import com.flightapp.login.service.UserService;
import com.flightapp.login.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;

	// @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest reqBody) throws Exception {

		try {
			authenticate(reqBody.getUsername(), reqBody.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(reqBody.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			// return ResponseEntity.ok(new JwtResponse(token));
			return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Invalid Credentials"), HttpStatus.UNAUTHORIZED);
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}