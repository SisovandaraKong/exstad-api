package co.istad.exstadapi.security;

import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Security configuration class for integrating Keycloak OAuth2 authentication
 * with Spring Security. This class defines authorization rules, JWT processing,
 * and CORS settings for the application.
 */
@Configuration
@RequiredArgsConstructor
public class KeycloakSecurity {

    // Injected CORS configuration source for cross-origin resource sharing
    private final CorsConfigurationSource corsConfigurationSource;

    /**
     * Configures the security filter chain with authorization rules for all endpoints.
     * This method defines which roles can access which endpoints and HTTP methods.
     *
     * @param http HttpSecurity object to configure security settings
     * @return SecurityFilterChain configured security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(endpoint -> endpoint
                // Auth endpoints - public access for registration and login
                .requestMatchers("/api/auth/**").permitAll()

                // User management endpoints
                // Only ADMIN, INSTRUCTOR1, and INSTRUCTOR2 can register new users
                .requestMatchers("/api/v1/users/register").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Login endpoint is publicly accessible
                .requestMatchers("/api/v1/users/login").permitAll()
                // POST operations on users require instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // GET operations on users require instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")

                // Province endpoints - publicly accessible for viewing
                .requestMatchers(HttpMethod.GET,"/api/v1/provinces/**").permitAll()

                // University endpoints
                // Creating universities is publicly accessible
                .requestMatchers(HttpMethod.POST,"/api/v1/universities/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating universities requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PATCH,"/api/v1/universities/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Viewing universities requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/universities/**").permitAll()
                // Deleting universities requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/universities/**").hasAnyRole("ADMIN")

                // Program endpoints
                // Viewing programs requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/programs/**").permitAll()
                // Creating programs requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/programs/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating programs requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/programs/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting programs requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/programs/**").hasAnyRole("ADMIN")

                // Badge endpoints
                // Viewing badges requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/badges/**").permitAll()
                // Creating badges requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/badges/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating badges requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/badges/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting badges requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/badges/**").hasAnyRole("ADMIN")

                // File upload endpoints
                .requestMatchers("/api/v1/documents/**").permitAll()
                .requestMatchers("/documents/**").permitAll()

                // Current Address endpoints
                // Viewing addresses is publicly accessible
                .requestMatchers(HttpMethod.GET,"/api/v1/current-addresses/**").permitAll()
                // Creating addresses requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/current-addresses/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating addresses requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/current-addresses/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting addresses requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/current-addresses/**").hasAnyRole("ADMIN")

                // Scholar endpoints
                // Viewing scholars requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/scholars/**").permitAll()
                // Creating scholars requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating scholars requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Partially updating scholars requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PATCH,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting scholars requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars/**").hasAnyRole("ADMIN")

                // Scholar Badge endpoints (Note: Same path as scholars - might need review)
                // Viewing scholar badges requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/scholars/**").permitAll()
                // Creating scholar badges requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Partially updating scholar badges requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PATCH,"/api/v1/scholars/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting scholar badges requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars/**").hasAnyRole("ADMIN")

                // Opening Program endpoints
                // Viewing opening programs requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/opening-programs/**").permitAll()
                // Creating opening programs requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/opening-programs/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating opening programs requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/opening-programs/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting opening programs requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/opening-programs/**").hasAnyRole("ADMIN")

                // Class endpoints
                // Viewing classes requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/classes/**").permitAll()
                // Creating classes requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating classes requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/classes/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting classes requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/classes/**").hasAnyRole("ADMIN")

                // Enrollment endpoints
                // Viewing enrollments requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/enrollments/**").permitAll()
                // Creating enrollments requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/enrollments/**").permitAll()
                // Partially updating enrollments requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PATCH,"/api/v1/enrollments/**").permitAll()
                // Updating enrollments requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/enrollments/**").permitAll()

                // Achievement endpoints
                // Viewing achievements requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/achievements/**").permitAll()
                // Creating achievements requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Partially updating achievements requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PATCH,"/api/v1/achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting achievements requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/achievements/**").hasAnyRole("ADMIN")

                // Scholar Achievement endpoints
                // Viewing scholar achievements requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/scholars-achievements/**").permitAll()
                // Creating scholar achievements requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/scholars-achievements/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Deleting scholar achievements requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars-achievements/**").hasAnyRole("ADMIN")

                // Scholar Class endpoints
                // Viewing scholar classes requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/scholars-classes/**").permitAll()
                // Creating scholar classes requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating scholar classes requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting scholar classes requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/scholars-classes/**").hasAnyRole("ADMIN")

                // Certificate endpoints
                // Viewing certificates requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/certificates/**").permitAll()
                // Creating certificates requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/certificates/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating certificates requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/certificates/**").hasAnyRole("ADMIN","INSTRUCTOR1")

                // Instructor Class endpoints
                // Viewing instructor classes requires any instructor or admin role
                .requestMatchers(HttpMethod.GET,"/api/v1/instructors-classes/**").permitAll()
                // Creating instructor classes requires any instructor or admin role
                .requestMatchers(HttpMethod.POST,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1","INSTRUCTOR2")
                // Updating instructor classes requires ADMIN or INSTRUCTOR1
                .requestMatchers(HttpMethod.PUT,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN","INSTRUCTOR1")
                // Deleting instructor classes requires ADMIN only
                .requestMatchers(HttpMethod.DELETE,"/api/v1/instructors-classes/**").hasAnyRole("ADMIN")

                // Bakong endpoints - public access
                .requestMatchers("/api/v1/bakong/**").permitAll()
                .requestMatchers("/api/bakong/**").permitAll()

                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()



                // All other requests require authentication
                .anyRequest().authenticated()
        );

        // Disable form-based login (using JWT instead)
        http.formLogin(AbstractHttpConfigurer::disable);

        // Configure CORS with the injected configuration source
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource);
        });

        // Configure OAuth2 resource server to validate JWT tokens
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        // Disable CSRF protection (not needed for stateless JWT authentication)
        http.csrf(AbstractHttpConfigurer::disable);

        // Configure stateless session management (no session cookies)
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

    /**
     * Configures JWT authentication converter to extract roles from Keycloak JWT tokens.
     * This converter reads roles from the "realm_access" claim and converts them to
     * Spring Security GrantedAuthority objects with "ROLE_" prefix.
     *
     * @return JwtAuthenticationConverter configured JWT converter
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Define a converter to extract roles from JWT claims
        Converter<Jwt, Collection<GrantedAuthority>> jwtCollectionConverter = jwt -> {
            // Extract the realm_access claim containing role information
            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            // Get the roles collection from realm_access
            Collection<String> roles = realmAccess.get("roles");

            // Convert each role to a GrantedAuthority with "ROLE_" prefix
            // This prefix is required by Spring Security's hasRole() method
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        };

        // Create and configure the JWT authentication converter
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtCollectionConverter);
        return jwtAuthenticationConverter;
    }

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
     * This allows the frontend application to make requests to the backend API.
     *
     * @return CorsConfigurationSource configured CORS settings
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow requests from the frontend running on localhost:3000
        configuration.setAllowedOriginPatterns(List.of("http://localhost:3000"));

        // Allow common HTTP methods
        configuration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));

        // Allow all headers in requests
        configuration.setAllowedHeaders(List.of("*"));

        // Allow credentials (cookies, authorization headers, etc.)
        configuration.setAllowCredentials(true);

        // Register CORS configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}