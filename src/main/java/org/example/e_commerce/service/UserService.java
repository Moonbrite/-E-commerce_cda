package org.example.e_commerce.service;

import org.example.e_commerce.execption.UtilisateurDejaPresentEmailException;
import org.example.e_commerce.model.DTO.UserDTO;
import org.example.e_commerce.model.User;
import org.example.e_commerce.repository.UserRepository;
import org.example.e_commerce.util.HashPasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
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

    @Transactional
    public User registerUser(UserDTO dto) throws NoSuchAlgorithmException {
        // Transformer le DTO en entité Utilisateur
        byte[] salt = HashPasswordUser.generateSalt();
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(HashPasswordUser.hashPassword(dto.getPassword(), salt))
                .email(dto.getEmail())
                .build();

        user.setRole("ROLE_USER");
        user.setSalt(salt);

        // Sauvegarder l'utilisateur dans la base de données
        return userRepository.save(user);
    }

}
