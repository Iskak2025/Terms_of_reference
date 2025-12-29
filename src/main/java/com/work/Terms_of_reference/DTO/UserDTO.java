package com.work.Terms_of_reference.DTO;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank // обязателен для заполнения
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
