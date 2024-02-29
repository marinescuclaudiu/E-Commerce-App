package com.cmarinescu.backend.auth.service;

import com.cmarinescu.backend.auth.dto.AuthRequest;
import com.cmarinescu.backend.auth.dto.AuthResponse;

public interface IAuthService {
    AuthResponse signUp(AuthRequest request);
    AuthResponse logIn(AuthRequest request);

}
