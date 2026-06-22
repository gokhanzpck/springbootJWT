package org.example.springbootjwt.controller.impl;

import jakarta.validation.Valid;
import org.example.springbootjwt.controller.IRestAuthController;
import org.example.springbootjwt.dto.DtoUser;
import org.example.springbootjwt.jwt.AuthRequest;
import org.example.springbootjwt.jwt.AuthResponse;
import org.example.springbootjwt.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}
