

package co.istad.exstadapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class KeycloakSecurity {

    private final Environment env; // check active profile

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Common: CORS & CSRF
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // If running locally (dev profile), skip JWT validation and allow all
        if (List.of(env.getActiveProfiles()).contains("dev")) {
            http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        } else {
            // Production: Keycloak JWT validation
            http.authorizeHttpRequests(auth -> auth
                            // Example endpoints (you can keep all your original rules)
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                            .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        }

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:3000")); // frontend
        configuration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
