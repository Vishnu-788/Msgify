package org.project.msgify.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username cannot be empty.")
    @Pattern(
            regexp = "^\\w+$",
            message = "Username can only contain characters, numbers and underscores."
    )
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty.")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be empty.")
    @Pattern(
            // Email with other domains.
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Please provide a valid email address."
    )
    private String email;

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Users() {

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail(){ return email; }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                '}';
    }
}
