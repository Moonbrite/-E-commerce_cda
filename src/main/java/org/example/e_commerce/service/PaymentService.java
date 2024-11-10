package org.example.e_commerce.service;

import org.example.e_commerce.model.Payment;
import org.example.e_commerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getObjects(){
        return paymentRepository.findAll();
    }

    public Payment getOneObject(Long id){
        return paymentRepository.findById(id).orElse(null);
    }

    public void deleteObject(Long id){
        paymentRepository.deleteById(id);
    }

    public Payment postObjectOrUpdate(Payment object){
        return paymentRepository.save(object);
    }
}
