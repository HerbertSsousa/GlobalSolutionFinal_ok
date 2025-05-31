package org.example.GlobalSolutionFinal.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Mantém provider para autenticação local via banco de dados
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/home", "/login",
                                "/css/**", "/js/**", "/img/**",
                                "/usuarios/novo", "/usuarios/salvar",
                                "/api/groq/**"  // liberar API Groq sem autenticação
                        ).permitAll()
                        .requestMatchers(
                                "/dashboard",
                                "/usuarios",
                                "/usuarios/excluir",
                                "/usuarios/editar/**"
                        ).authenticated()
                        .anyRequest().authenticated()
                )
                // Configuração para login via form
                .formLogin(form -> form
                        .loginPage("/home")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/home?error=true")
                        .permitAll()
                )
                // Configuração para login via OAuth2
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/home")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/home?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/groq/**")
                );

        return http.build();
    }
}
