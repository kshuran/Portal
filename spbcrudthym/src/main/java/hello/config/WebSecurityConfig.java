package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/css/**", "/js/**", "/assets/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
						.antMatchers("/admin/**").hasRole("ADMIN")
						.antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
						.anyRequest().fullyAuthenticated()
						.and()
				.formLogin()
					.loginPage("/sign-in")
					.defaultSuccessUrl("/home", true)
					.permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.ldapAuthentication()
						.userDnPatterns("uid={0},ou=Users")
						.groupSearchFilter("member={0}")
						.groupSearchBase("ou=groups")
						.contextSource()
								.url("ldap://ldap.link.com:10389/dc=example,dc=com");
	}
	
}
