package com.dugas.covenantclassroom.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.dugas.covenantclassroom.model.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
   
    @Autowired
    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication login(Request.loginRequest lr, HttpServletRequest request, HttpServletResponse response) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(lr.username(), lr.password());
        try {
            Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return authenticationResult;
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new RuntimeException("Invalid login credentials", e);
        }
    }
}
