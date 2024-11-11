package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.User;
import org.example.e_commerce.service.UserService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getObjects();
    }

    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable Long id){
        return userService.getOneObject(id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteObject(id);
    }

    @GetMapping("/users/email/{email}")
    public User findUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }


}
