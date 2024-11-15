package org.example.e_commerce.service;

import org.example.e_commerce.model.Cart;
import org.example.e_commerce.model.Category;
import org.example.e_commerce.repository.CartRepository;
import org.example.e_commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getObjects(){
        return categoryRepository.findAll();
    }

    public Category getOneObject(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        categoryRepository.deleteById(id);
    }

    public Category postObjectOrUpdate(Category object){
        return categoryRepository.save(object);
    }


}
