package com.flightapp.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.flightapp.login.filter.JwtFilter;
import com.flightapp.login.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtFilter jwtRequestFilter;

	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { // We don't need CSRF for this example
	 * httpSecurity.csrf().disable()
	 * .authorizeRequests().antMatchers("/authenticate","/login/register",
	 * "/swagger-ui/**", "/v3/api-docs/**").permitAll().
	 * antMatchers("/login/getFlights","/login//getbookings",
	 * "/login/registerairline","/flightapp/**").access("hasRole('ADMIN')"). // all
	 * other requests need to be authenticated anyRequest().authenticated().and().
	 * exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and
	 * ().sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * // Add a filter to validate the tokens with every request
	 * httpSecurity.addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class); }
	 */
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers("/flightapp/getall", "/flight/getallBookings", "/flightapp/registerAirline")
				.access("hasRole('ADMIN')").antMatchers("/login/home", "/bookingHistory/{emailId}").authenticated()
				.anyRequest().permitAll().and().exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
