package org.example.springbootjwt.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.*;
import org.example.springbootjwt.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {
    //“Spring Security’ye diyoruz ki: kullanıcı login olmaya çalıştığında git veritabanından bul, şifresini kontrol et, doğruysa sisteme al.”

    @Autowired
    private org.example.springbootjwt.repository.UserRepository userRepository;


    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> optional= userRepository.findByUsername(username);
                if (optional.isPresent()){
                    return  optional.get();
                }
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));

            }
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider(userDetailsService());

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return  configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}