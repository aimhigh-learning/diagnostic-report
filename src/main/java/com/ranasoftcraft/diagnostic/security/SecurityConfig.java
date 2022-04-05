/**
 * 
 */
package com.ranasoftcraft.diagnostic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ranasoftcraft.diagnostic.security.services.UsersService;

/**
 * @author sandeep.rana
 *
 */
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsersService userService;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService((UserDetailsService) userService)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
	      .csrf().disable()
	      .httpBasic().and()
	      .authorizeRequests()
	      .antMatchers("/resources/***","/bootstrap-5.0/**","/report/**").permitAll()
//	      .antMatchers("/admin/**").hasRole("ADMIN") // todo 
	      .antMatchers("/anonymous*").anonymous()
	      .antMatchers("/login*","/signup").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .formLogin()
	      .loginPage("/login")
	      .loginProcessingUrl("/perform_login")
	      .failureUrl("/login?error=true").permitAll()
	      .defaultSuccessUrl("/welcome")
	      .usernameParameter("user_name")
	      .passwordParameter("password")
	      
	      .and()
	      .logout()
	      .logoutUrl("/perform_logout")
	      .logoutSuccessUrl("/login?logout=true")
	      .deleteCookies("JSESSIONID");
//	      .logoutSuccessHandler(logoutSuccessHandler());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
