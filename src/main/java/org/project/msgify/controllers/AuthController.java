package org.project.msgify.controllers;

import org.project.msgify.dto.AuthResponseDto;
import org.project.msgify.dto.LoginDto;
import org.project.msgify.dto.RegisterDto;
import org.project.msgify.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody RegisterDto registerDto
    ){
        String token = service.registerUser(registerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AuthResponseDto(registerDto.username(), token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(
            @RequestBody LoginDto loginDto
    ){
        String token = service.loginUser(loginDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AuthResponseDto(loginDto.username(), token));
    }
}
