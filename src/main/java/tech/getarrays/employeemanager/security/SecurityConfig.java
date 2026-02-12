package tech.getarrays.employeemanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //WE use the Bean annotation because we might wanna inject or use the data in the class somewhere so bean allows it to be safely stored
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET).permitAll()
                                .anyRequest()
                                .authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();
        UserDetails user= User.builder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }
}
