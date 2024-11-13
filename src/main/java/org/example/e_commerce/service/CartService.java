package org.example.e_commerce.service;

import org.example.e_commerce.model.Cart;
import org.example.e_commerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public List<Cart> getObjects(){
        return cartRepository.findAll();
    }

    public Cart getOneObject(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        cartRepository.deleteById(id);
    }

    public Cart postObjectOrUpdate(Cart object){
        return cartRepository.save(object);
    }

    public Optional<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

}
