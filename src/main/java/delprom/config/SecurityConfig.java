package delprom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import delprom.security.CustomAuthenticationProvider;
import delprom.security.JwtAuthenticationEntryPoint;
import delprom.security.JwtAuthenticationFilter;
import delprom.security.RoleHeaderFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableMethodSecurity
@SecurityScheme(name = "Bear Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter authenticationFilter;

	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	 @Autowired
	    private CorsConfigurationSource corsConfigurationSource;

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
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public FilterRegistrationBean<RoleHeaderFilter> roleHeaderFilterRegistration() {
		FilterRegistrationBean<RoleHeaderFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new RoleHeaderFilter());
		registration.addUrlPatterns("/*");
		registration.setName("roleHeaderFilter");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize
				// GET
				.requestMatchers(HttpMethod.GET, "/api/get-client-secret/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/kategorija/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/proizvod/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/korisnik/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/porudzbina/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/dostava/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/placanje/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/stavkaporudzbine/**").hasRole("ADMIN")
				// POST
				.requestMatchers(HttpMethod.POST, "/api/kategorija/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/proizvod/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/korisnik/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/porudzbina/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/dostava/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/placanje/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/stavkaporudzbine/**").hasRole("ADMIN")
				// PUT
				.requestMatchers(HttpMethod.PUT, "/api/kategorija/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/proizvod/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/korisnik/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/porudzbina/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/dostava/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/placanje/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/stavkaporudzbine/**").hasRole("ADMIN")
				// DELETE
				.requestMatchers(HttpMethod.DELETE, "/api/kategorija/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/proizvod/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/korisnik/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/porudzbina/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/dostava/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/placanje/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/stavkaporudzbine/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/mojipodaci/**").hasRole("KUPAC")
				.requestMatchers(HttpMethod.PUT, "/api/mojipodaci/**").hasRole("KUPAC")
				.requestMatchers(HttpMethod.GET, "/api/mojeporudzbine/**").hasRole("KUPAC")
				.requestMatchers("/swagger-ui/**").permitAll().requestMatchers("/v3/api-docs/**").permitAll()
				.anyRequest().authenticated()

		).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors().configurationSource(corsConfigurationSource);

		return http.build();
	}

}
