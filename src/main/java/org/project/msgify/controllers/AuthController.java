package org.project.msgify.controllers;

import org.project.msgify.dto.UserDto;
import org.project.msgify.models.Users;
import org.project.msgify.services.AuthService;
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
    public void registerUser(
            @RequestBody UserDto user
    ){

        service.registerUser(user);
    }

    @PostMapping("/login")
    public void loginUser(
            @RequestBody UserDto user
    ){
        service.loginUser(user);
    }
}
