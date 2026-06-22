package org.example.springbootjwt.config;

import org.example.springbootjwt.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//Bu sınıf JWT projesinin güvenlik kapısıdır. Burada Spring Security'ye hangi isteklere izin vereceğimizi ve JWT kontrolünün nasıl yapılacağını söylüyoruz.
@Configuration
@EnableWebSecurity
public class securityConfig {
    private  static  final  String AUTHENTICATE="/authenticate";
    private  static  final  String REGISTER="/register";

    @Autowired
    private AuthenticationProvider authenticationProvider;
@Autowired
      private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
       http
               .csrf(csrf -> csrf.disable())
               . authorizeHttpRequests(request-> request.requestMatchers(AUTHENTICATE,REGISTER)
               .permitAll().anyRequest().authenticated())
               .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



       return  http.build();
   }


}/*
        Kısaca bu sınıfın yaptığı 4 şey:

        /authenticate ve /register herkese açık
        Diğer bütün endpointler JWT ister
        Session kullanımını kapatır (STATELESS)
        JwtAuthenticationFilter'ı Spring Security zincirine ekler.*/
