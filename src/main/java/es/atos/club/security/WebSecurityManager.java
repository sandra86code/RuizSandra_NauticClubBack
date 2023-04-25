package es.atos.club.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.atos.club.service.UserService;



@Configuration
@EnableWebSecurity
public class WebSecurityManager {
	
	@Autowired
	private UserService myUserDetailService;
	
	@Autowired
	AuthEntryPointJwt unauthorizedHandler;
	
	// Indicamos que la configuración se hará a través del servicio.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService);
	}
	
	@Bean
	@Primary
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
	
	// Método que nos suministrará la codificación
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Método que autentifica
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
			throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public JwtAuthorizationFilter authenticationJwtTokenFilter() {
		return new JwtAuthorizationFilter();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
		.authorizeHttpRequests((requests) ->{
			requests
			.requestMatchers("/signin").permitAll()
			.requestMatchers("/swagger-ui/**").permitAll()
			.requestMatchers("/v3/api-docs/**").permitAll()
			.requestMatchers(HttpMethod.GET, "/boats").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/boats/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.POST, "/boats").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/boats/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/boats/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/departures").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/departures/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.POST, "/departures").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/departures/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/departures/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/members").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/members/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.POST, "/members").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/members/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/members/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/captains").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.GET, "/captains/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.POST, "/captains").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/captains/{id}").hasAuthority("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/captains/{id}").hasAuthority("ADMIN")
			.anyRequest().denyAll();
		});
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.cors();
		return http.build();
	}
}
