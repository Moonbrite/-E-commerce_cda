package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.CartItem;
import org.example.e_commerce.model.Order;
import org.example.e_commerce.service.OrderService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getObjects();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOneObject(id);
    }

    @PostMapping("/orders")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return orderService.postObjectOrUpdate(order);
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteObject(id);
    }




}
