package org.example.e_commerce.service;


import org.example.e_commerce.model.CartItem;
import org.example.e_commerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItem> getObjects(){
        return cartItemRepository.findAll();
    }

    public CartItem getOneObject(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        cartItemRepository.deleteById(id);
    }

    public CartItem postObjectOrUpdate(CartItem object){
        return cartItemRepository.save(object);
    }


}
