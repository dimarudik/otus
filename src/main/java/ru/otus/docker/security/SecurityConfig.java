package ru.otus.docker.security;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/error").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> userDetailsService())
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
*/
}
