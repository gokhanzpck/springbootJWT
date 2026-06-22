package org.example.springbootjwt.controller;

import jakarta.validation.Valid;
import org.example.springbootjwt.dto.DtoUser;
import org.example.springbootjwt.jwt.AuthRequest;
import org.example.springbootjwt.jwt.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestAuthController {
    public DtoUser  register(AuthRequest request);


    public AuthResponse authenticate(AuthRequest request);

}
