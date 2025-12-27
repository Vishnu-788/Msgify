package org.project.msgify.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greet {
    @GetMapping("/")
    public String greetUser(){
        return "Hello user!";
    }
}
