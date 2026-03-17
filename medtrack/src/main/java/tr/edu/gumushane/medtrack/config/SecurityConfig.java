package tr.edu.gumushane.medtrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.userDetailsService(userDetailsService)
				.authorizeHttpRequests(auth -> auth
						// Actuator endpoints - herkese açık
						.requestMatchers("/actuator/health", "/actuator/info").permitAll()
						
						// GET operasyonları - tüm roller erişebilir
						.requestMatchers("GET", "/members/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("GET", "/doctors/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("GET", "/medications/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("GET", "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("GET", "/roles/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("GET", "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("GET", "/users/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						
						// POST operasyonları - ADMIN ve DOCTOR erişebilir
						.requestMatchers("POST", "/members/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("POST", "/doctors/**").hasAnyRole("ADMIN")
						.requestMatchers("POST", "/medications/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("POST", "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("POST", "/roles/**").hasAnyRole("ADMIN")
						.requestMatchers("POST", "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("POST", "/users/**").hasAnyRole("ADMIN")
						
						// PUT operasyonları - ADMIN ve DOCTOR erişebilir
						.requestMatchers("PUT", "/members/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("PUT", "/doctors/**").hasAnyRole("ADMIN")
						.requestMatchers("PUT", "/medications/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("PUT", "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("PUT", "/roles/**").hasAnyRole("ADMIN")
						.requestMatchers("PUT", "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("PUT", "/users/**").hasAnyRole("ADMIN")
						
						// PATCH operasyonları - ADMIN ve DOCTOR erişebilir
						.requestMatchers("PATCH", "/members/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("PATCH", "/doctors/**").hasAnyRole("ADMIN")
						.requestMatchers("PATCH", "/medications/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("PATCH", "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("PATCH", "/roles/**").hasAnyRole("ADMIN")
						.requestMatchers("PATCH", "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers("PATCH", "/users/**").hasAnyRole("ADMIN")
						
						// DELETE operasyonları - sadece ADMIN erişebilir
						.requestMatchers("DELETE", "/members/**").hasRole("ADMIN")
						.requestMatchers("DELETE", "/doctors/**").hasRole("ADMIN")
						.requestMatchers("DELETE", "/medications/**").hasRole("ADMIN")
						.requestMatchers("DELETE", "/prescriptions/**").hasRole("ADMIN")
						.requestMatchers("DELETE", "/roles/**").hasRole("ADMIN")
						.requestMatchers("DELETE", "/records/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers("DELETE", "/users/**").hasRole("ADMIN")
						
						// Dashboard - tüm roller erişebilir
						.requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}

