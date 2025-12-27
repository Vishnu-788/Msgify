package org.project.msgify.services;

import org.project.msgify.dto.UserDto;
import org.project.msgify.exceptions.LoginFailedException;
import org.project.msgify.filters.JwtFilter;
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

    public void registerUser(UserDto userDto){
        Users user = ConvertToEntity(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public String loginUser(UserDto userDto){
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        } catch(BadCredentialsException e){
            throw new LoginFailedException("Invalid Credentials.");
        }
        return jwtService.createToken(userDto.getUsername());
    }

    private Users ConvertToEntity(UserDto userDto){
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
