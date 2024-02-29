package com.cmarinescu.backend.auth.controller;

import com.cmarinescu.backend.auth.dto.AuthRequest;
import com.cmarinescu.backend.auth.dto.AuthResponse;
import com.cmarinescu.backend.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
   private AuthService authService;

   public AuthController(AuthService authService){
       this.authService = authService;
   }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> logIn(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.logIn(request));
    }
}