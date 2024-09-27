package br.com.ufape.es.topicos.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import br.com.ufape.es.topicos.auth.KeycloakAuthentication;

@Configuration
@Component
//@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private KeycloakAuthentication keycloakAuthentication;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilitar CSRF para simplificação
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(new AntPathRequestMatcher("/api/gerente/**")).hasRole("ECOMMERCE-CLIENTGERENTE")
                .requestMatchers(new AntPathRequestMatcher("/api/usuario/**")).hasAnyRole("ECOMMERCE-CLIENTUSUARIO", "ECOMMERCE-CLIENTGERENTE","OFFLINE_ACCESS")
                
                .anyRequest().permitAll()
            )
            //.authorizeExchange(exchange -> exchange
            //    .pathMatchers("/eureka/**").permitAll()
            //)
            
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakAuthentication)));

        return http.build();
    }

    /*private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        System.out.println("tttt");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("realm_access.roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }*/
}