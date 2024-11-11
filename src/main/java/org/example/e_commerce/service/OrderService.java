package org.example.e_commerce.service;

import org.example.e_commerce.model.Order;
import org.example.e_commerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getObjects(){
        return orderRepository.findAll();
    }

    public Order getOneObject(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        orderRepository.deleteById(id);
    }

    public Order postObjectOrUpdate(Order object){
        return orderRepository.save(object);
    }

}
