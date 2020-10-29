package br.com.springbootapi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class ConfigProject extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("SELECT nome as username, senha as password, 'true' from clientes where nome = ?")
				.authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
	}

//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
//	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/swagger-ui.html")
		.and().csrf().disable();
	}

	
}
