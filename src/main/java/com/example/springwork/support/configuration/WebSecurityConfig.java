package com.example.springwork.support.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// --- JWT ---
	@Override public void configure(WebSecurity web) { 
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override protected void configure(HttpSecurity http) throws Exception { 
		http.csrf().disable().authorizeRequests() 
			// 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함 
			.anyRequest()
			.permitAll()
			// 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다. 
			.and() 
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			// form 기반의 로그인에 대해 비활성화 한다. 
			.and() 
			.formLogin()
			.disable(); 
	}

	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder() { 
		return new BCryptPasswordEncoder(); 
	}

	/*
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
	*/
	
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