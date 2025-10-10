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
        http.authorizeHttpRequests(endpoint -> endpoint
//                // Register and Login can access by anyone
//                .requestMatchers("/api/auth/**").permitAll()
//                // User and create into our db and keycloak
//                .requestMatchers("/api/v1/users/register").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers("/api/v1/users/login").permitAll()
//                .requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                // Province
//                .requestMatchers(HttpMethod.GET,"/api/v1/provinces/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                // University
//                .requestMatchers(HttpMethod.POST,"/api/v1/universities/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PATCH,"/api/v1/universities/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.GET,"/api/v1/universities/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/universities/**").hasAnyRole("ADMIN")
//                // Program
//                .requestMatchers(HttpMethod.GET,"/api/v1/programs/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/programs/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/programs/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/programs/**").hasAnyRole("ADMIN")
//                // Badge
//                .requestMatchers(HttpMethod.GET,"/api/v1/badges/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/badges/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/badges/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/badges/**").hasAnyRole("ADMIN")
//                // Upload file
//                .requestMatchers("/api/v1/documents/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers("/documents/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                // Current Address
//                .requestMatchers(HttpMethod.GET,"/api/v1/current-addresses/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/current-addresses/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/current-addresses/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/current-addresses/**").hasAnyRole("ADMIN")
//                // Scholar
//                .requestMatchers(HttpMethod.GET,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.PATCH,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars/**").hasAnyRole("ADMIN")
//                // Scholar Badge
//                .requestMatchers(HttpMethod.GET,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PATCH,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars/**").hasAnyRole("ADMIN")
//                // Opening Program
//                .requestMatchers(HttpMethod.GET,"/api/v1/opening-programs/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/opening-programs/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/opening-programs/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/opening-programs/**").hasAnyRole("ADMIN")
//                // Class
//                .requestMatchers(HttpMethod.GET,"/api/v1/classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/classes/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/classes/**").hasAnyRole("ADMIN")
//                // Enrollment
//                .requestMatchers(HttpMethod.GET,"/api/v1/enrollments/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/enrollments/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PATCH,"/api/v1/enrollments/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/enrollments/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                // Achievement
//                .requestMatchers(HttpMethod.GET,"/api/v1/achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PATCH,"/api/v1/achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/achievements/**").hasAnyRole("ADMIN")
//                // Scholar Achievement
//                .requestMatchers(HttpMethod.GET,"/api/v1/scholars-achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/scholars-achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars-achievements/**").hasAnyRole("ADMIN")
//                // Scholar Class
//                .requestMatchers(HttpMethod.GET,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN")
//                // Certificate
//                .requestMatchers(HttpMethod.GET,"/api/v1/certificates/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/certificates/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/certificates/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                // Instructor Class
//                .requestMatchers(HttpMethod.GET,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.POST,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
//                .requestMatchers(HttpMethod.PUT,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1")
//                .requestMatchers(HttpMethod.DELETE,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
        );

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
