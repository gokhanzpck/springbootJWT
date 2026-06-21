package org.example.springbootjwt.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  jwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         String header;
         String token;
         String username;
       header= request.getHeader("Authorization");
       if (header==null){
           filterChain.doFilter(request,response);
           return;
       }
       token =header.substring(7);
       try {
        username=  jwtService.geyUsernameByToken(token);
if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
 UserDetails userDetails= userDetailsService.loadUserByUsername(username);
if (userDetailsService!=null && jwtService.isTokenExpired(token)){
    //kişiyi security context e  koyabilirim.
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());

    authentication.setDetails(userDetails);
     SecurityContextHolder.getContext().setAuthentication(authentication);
}
}
       }catch (ExpiredJwtException e){
           System.out.println("token süresi dolmuş"+e.getMessage());
       }
       catch (Exception e){
           System.out.println("genel bir hata oluştu : "+e.getMessage());
       }
       filterChain.doFilter(request,response);
    }
}
