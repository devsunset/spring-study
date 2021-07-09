package com.example.springwork.support.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// --- BASIC ----
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/h2-console/**" ).permitAll()
				.antMatchers("/swagger-ui/**" ).permitAll()
				// .antMatchers("/**", "/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf()
				.ignoringAntMatchers("/h2-console/**")
				.ignoringAntMatchers("/swagger-ui/**")
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.permitAll();

			http.headers().frameOptions().disable();
	}
	
	@Bean
	@Override
	@Deprecated
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	
	/*
	// --- LDAP ----
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
		.authorizeRequests()
		  .anyRequest().fullyAuthenticated()
		  .and()
		.formLogin();
	}
  
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.ldapAuthentication()
		  .userDnPatterns("uid={0},ou=people")
		  .groupSearchBase("ou=groups")
		  .contextSource()
			.url("ldap://localhost:8389/dc=springframework,dc=org")
			.and()
		  .passwordCompare()
			.passwordEncoder(new BCryptPasswordEncoder())
			.passwordAttribute("userPassword");
	}
	*/
}