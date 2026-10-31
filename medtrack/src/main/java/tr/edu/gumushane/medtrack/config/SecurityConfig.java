package tr.edu.gumushane.medtrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
						.requestMatchers(HttpMethod.GET, "/members/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.GET, "/doctors/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.GET, "/medications/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.GET, "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.GET, "/roles/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.GET, "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

						// POST operasyonları
						.requestMatchers(HttpMethod.POST, "/members/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.POST, "/doctors/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/medications/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.POST, "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.POST, "/roles/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")

						// PUT operasyonları
						.requestMatchers(HttpMethod.PUT, "/members/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.PUT, "/doctors/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/medications/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.PUT, "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")

						// PATCH operasyonları
						.requestMatchers(HttpMethod.PATCH, "/members/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.PATCH, "/doctors/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PATCH, "/medications/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.PATCH, "/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.PATCH, "/roles/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PATCH, "/records/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
						.requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")

						// DELETE operasyonları
						.requestMatchers(HttpMethod.DELETE, "/members/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/doctors/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/medications/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/prescriptions/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/roles/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/records/**").hasAnyRole("ADMIN", "DOCTOR")
						.requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

						// Dashboard - tüm roller erişebilir
						.requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")

						// Panel sayfaları
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/patient/**").hasAnyRole("ADMIN", "PATIENT")

						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}