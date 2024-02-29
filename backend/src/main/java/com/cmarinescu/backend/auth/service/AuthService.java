package com.cmarinescu.backend.auth.service;

import com.cmarinescu.backend.auth.dto.AuthRequest;
import com.cmarinescu.backend.auth.dto.AuthResponse;
import com.cmarinescu.backend.common.exception.AuthenticationFailedException;
import com.cmarinescu.backend.common.exception.DatabaseError;
import com.cmarinescu.backend.common.exception.EntityAlreadyExistsException;
import com.cmarinescu.backend.common.exception.EntityNotFoundException;
import com.cmarinescu.backend.user.model.User;
import com.cmarinescu.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    private UserRepository userRepository;
    private JWTUtils jwtUtils;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse signUp(AuthRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EntityAlreadyExistsException("Email is already in use");
        }

        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);

        if(savedUser != null){
            return new AuthResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole(), "User saved successfully");
        } else {
            throw new DatabaseError("Failed to save user");
        }
    }

    public AuthResponse logIn(AuthRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationFailedException("Invalid email or password");
        }

        Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with email " + request.getEmail() + " not found");
        }

        User user = userOptional.get();

        String jwt = jwtUtils.generateToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                jwt,
                "8H",
                "Successfully logged in"
        );
    }
}
