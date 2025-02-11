package com.lorrilanig.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String hashedPassword;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = encoder.encode(hashedPassword);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = encoder.encode(hashedPassword);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, hashedPassword);
    }
}


