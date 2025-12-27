package org.project.msgify.services;

import org.project.msgify.dto.UserDto;
import org.project.msgify.models.Users;
import org.project.msgify.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    public AuthService(UserRepo userRepo, PasswordEncoder encoder){
        this.userRepo = userRepo;
        this.encoder = encoder;

    }

    public void registerUser(UserDto userDto){
        Users user = ConvertToEntity(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void loginUser(UserDto user){
        System.out.println("Handling login logic...");
    }

    private Users ConvertToEntity(UserDto userDto){
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
