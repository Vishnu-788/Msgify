package org.project.msgify.controllers;

import org.project.msgify.dto.UserDto;
import org.project.msgify.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserDto userDto
    ){

        service.registerUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Successfully created user with username: " + userDto.getUsername());

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestBody UserDto user
    ){
        String token = service.loginUser(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);
    }
}
