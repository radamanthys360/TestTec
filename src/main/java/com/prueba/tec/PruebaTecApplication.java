package com.prueba.tec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.prueba.tec.serviceimpl.PermisoRecursoServices;


@SuppressWarnings("deprecation")
@SpringBootApplication
public class PruebaTecApplication extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PermisoRecursoServices permisoRecursoServices;

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecApplication.class, args);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSecurity permisos = permisos(http);
		permisos.authorizeRequests()
			.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/authenticateTheUser")
	            .defaultSuccessUrl("/principal")
	            .failureUrl("/denegado")
				.permitAll()
				.and()
				.logout().logoutSuccessUrl("/").invalidateHttpSession(true).permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/denegado");
		http.csrf().disable(); // temporal solo para test rest sin seguridad
	}
	
	private HttpSecurity permisos(HttpSecurity http) throws Exception {
		Object[][] findPermisoRecurso = permisoRecursoServices.findPermisoRecurso();
  	for (Object[] permisosDto : findPermisoRecurso) {
  		http.authorizeRequests().antMatchers(permisosDto[0].toString()).
  		hasAnyRole(permisosDto[1].toString());
		}
		return http;
	}
	
	
	

}
