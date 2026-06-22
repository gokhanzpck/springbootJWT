package org.example.springbootjwt.service.impl;

import org.example.springbootjwt.dto.DtoUser;
import org.example.springbootjwt.jwt.AuthRequest;
import org.example.springbootjwt.jwt.AuthResponse;
import org.example.springbootjwt.jwt.jwtService;
import org.example.springbootjwt.model.User;
import org.example.springbootjwt.repository.UserRepository;
import org.example.springbootjwt.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IAuthServiceImpl implements IAuthService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
     private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private jwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            System.out.println("AUTH METODUNA GİRDİ");

            Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

            System.out.println("Request username: " + request.getUsername());
            System.out.println("Request password: " + request.getPassword());
            System.out.println("DB password: " + optionalUser.get().getPassword());
            System.out.println("Şifre eşleşiyor mu: " +
                    passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword()));

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    );

            authenticationProvider.authenticate(auth);

            String token = jwtService.generateToken(optionalUser.get());

            return new AuthResponse(token);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("kullanıcı adı veya şifre hatalı");
        }

        return null;
    }
/*
    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    );

            authenticationProvider.authenticate(auth);

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();
            System.out.println("Şifre eşleşiyor mu: " +
                    passwordEncoder.matches(request.getPassword(), user.getPassword()));

            String token = jwtService.generateToken(user);

            return new AuthResponse(token);


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("kullanıcı adı veya şifre hatalı");

        }

        return null;
    }*/

    @Override
    public DtoUser register(AuthRequest request) {
      DtoUser dto=new DtoUser();
        User user =new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

         User saveUser= userRepository.save(user);
        BeanUtils.copyProperties(saveUser,dto);

        return dto;
    }


}
