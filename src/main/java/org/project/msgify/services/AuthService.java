package org.project.msgify.services;

import org.project.msgify.dto.LoginDto;
import org.project.msgify.dto.RegisterDto;
import org.project.msgify.exceptions.LoginFailedException;
import org.project.msgify.models.Users;
import org.project.msgify.repositories.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(UserRepo userRepo, PasswordEncoder encoder, AuthenticationManager authManager, JwtService jwtService){
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public String registerUser(RegisterDto registerDto){
        Users user = ConvertToEntity(registerDto);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return jwtService.createToken(user.getUsername());
    }

    public String loginUser(LoginDto loginDto){
        try {
            // Performs the authentication.
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        } catch(BadCredentialsException e){
            // Throw the error so the Custom Global exception handler can handle it instead of the security exception handler.
            throw new LoginFailedException("Invalid Credentials.");
        }
        return jwtService.createToken(loginDto.username());
    }

    private Users ConvertToEntity(RegisterDto registerDto){
        Users user = new Users();
        user.setUsername(registerDto.username());
        user.setEmail(registerDto.email());
        user.setPassword(registerDto.password());
        return user;
    }
}
