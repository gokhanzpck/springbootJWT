package org.example.springbootjwt.service;

import org.example.springbootjwt.dto.DtoUser;
import org.example.springbootjwt.jwt.AuthRequest;
import org.example.springbootjwt.jwt.AuthResponse;

public interface IAuthService {
    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);

}
