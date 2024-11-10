package org.example.e_commerce.service;

import org.example.e_commerce.model.Order;
import org.example.e_commerce.model.OrderItem;
import org.example.e_commerce.repository.OrderItemRepository;
import org.example.e_commerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {


    @Autowired
    OrderItemRepository orderItemRepository;

    public List<OrderItem> getObjects(){
        return orderItemRepository.findAll();
    }

    public OrderItem getOneObject(Long id){
        return orderItemRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        orderItemRepository.deleteById(id);
    }

    public OrderItem postObjectOrUpdate(OrderItem object){
        return orderItemRepository.save(object);
    }

}
