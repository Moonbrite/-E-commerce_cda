package org.example.e_commerce.model.DTO;

import jakarta.persistence.Column;

import lombok.Data;

@Data
public class UserDTO {


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


}
