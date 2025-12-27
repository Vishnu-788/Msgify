package org.project.msgify.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank(message = "Username cannot be empty.")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
