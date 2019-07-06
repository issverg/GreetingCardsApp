package il.co.sela.targil.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import il.co.sela.targil.security.TokenAuthenticationFilter;
import il.co.sela.targil.security.TokenAuthenticationManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	TokenAuthenticationManager manager;

	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers(HttpMethod.POST, "/signin");
		webSecurity.ignoring().antMatchers(HttpMethod.POST, "/signup");
		webSecurity.ignoring().antMatchers(HttpMethod.GET, "/greetingCard/catalog");
		webSecurity.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.addFilterBefore(new TokenAuthenticationFilter(manager), BasicAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.csrf().disable()
			.httpBasic().disable()
			.formLogin().disable();
	}
}