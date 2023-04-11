package delprom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import delprom.security.JwtAuthenticationEntryPoint;
import delprom.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
		name = "Bear Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer")
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter authenticationFilter;

	public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize
				// GET
				.requestMatchers(HttpMethod.GET, "/api/kategorija/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/proizvod/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/korisnik/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/porudzbina/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/dostava/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/placanje/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/stavkaporudzbine/**").hasRole("ROLE_ADMIN")
				// POST
				.requestMatchers(HttpMethod.POST, "/api/kategorija/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/proizvod/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/korisnik/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/porudzbina/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/dostava/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/placanje/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/stavkaporudzbine/**").hasRole("ROLE_ADMIN")
				// PUT
				.requestMatchers(HttpMethod.PUT, "/api/kategorija/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/proizvod/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/korisnik/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/porudzbina/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/dostava/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/placanje/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/stavkaporudzbine/**").hasRole("ROLE_ADMIN")
				// DELETE
				.requestMatchers(HttpMethod.DELETE, "/api/kategorija/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/proizvod/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/korisnik/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/porudzbina/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/dostava/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/placanje/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/stavkaporudzbine/**").hasRole("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/v3/api-docs/**").permitAll()
				.anyRequest().authenticated()

		).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults());

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
