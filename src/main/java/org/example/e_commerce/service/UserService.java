package org.example.e_commerce.service;

import org.example.e_commerce.execption.UtilisateurDejaPresentEmailException;
import org.example.e_commerce.model.User;
import org.example.e_commerce.repository.UserRepository;
import org.example.e_commerce.util.HashPasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private HashPasswordUser passwordEncoder;


    public List<User> getObjects(){
        return userRepository.findAll();
    }

    public User getOneObject(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        userRepository.deleteById(id);
    }

    public User postObjectOrUpdate(User object) {
        if (userRepository.findByEmail(object.getEmail()) == null) {
            return userRepository.save(object);
        }
        throw new UtilisateurDejaPresentEmailException(String.format("Un utilisateur posséde deja cette email :" + object.getEmail()));

    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
