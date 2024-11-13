package org.example.e_commerce.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.e_commerce.model.CartItem;
import org.example.e_commerce.model.DTO.OrderDTO;
import org.example.e_commerce.model.Order;
import org.example.e_commerce.model.User;
import org.example.e_commerce.service.OrderService;
import org.example.e_commerce.service.UserService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    private final UserService userService;


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
    public Order postOrder(@RequestBody @Valid OrderDTO orderDTO) {
        // Conversion de OrderDTO en Order (via un mapper manuel ou MapStruct, si utilisé)

        User user = userService.getOneObject((long) orderDTO.getUserId());

        Order order = new Order();
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setUser(user);
        order.setPaymentStatus("Paye");
        order.setOrderDate(LocalDateTime.now());

        // Appel au service pour sauvegarder ou mettre à jour
        return orderService.postObjectOrUpdate(order);
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteObject(id);
    }




}
