package org.example.e_commerce.model;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}