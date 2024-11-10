package org.example.e_commerce.service;

import org.example.e_commerce.model.Payment;
import org.example.e_commerce.model.Product;
import org.example.e_commerce.repository.PaymentRepository;
import org.example.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getObjects(){
        return productRepository.findAll();
    }

    public Product getOneObject(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        productRepository.deleteById(id);
    }

    public Product postObjectOrUpdate(Product object){
        return productRepository.save(object);
    }

}
