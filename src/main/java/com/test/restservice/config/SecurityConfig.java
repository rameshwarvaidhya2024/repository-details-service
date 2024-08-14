package com.test.restservice.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConfig.
 */
@Configuration
public class SecurityConfig {

	/**
	 * User details service using InMemoryUserDetailsManager() for demo purpose.
	 * This method can be enhanced by using JdbcUserDetailsManager and managing
	 * users in db.
	 *
	 * @return the user details service
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		var userDetailsService = new InMemoryUserDetailsManager();
		var user = User.withUsername("DemoUser").password("Test12345").authorities("read").build();
		userDetailsService.createUser(user);
		return userDetailsService;
	}

	/**
	 * Password encoder using NoOpPasswordEncoder for demo. BCryptPasswordEncoder is
	 * recommended.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	/**
	 * Security filter chain helps to configure authentication and authorization
	 * using spring security
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.httpBasic(withDefaults()).formLogin(withDefaults())
				.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
				.csrf((csrf) -> csrf.disable()).build();
	}

}
