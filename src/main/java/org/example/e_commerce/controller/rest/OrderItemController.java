package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.Order;
import org.example.e_commerce.model.OrderItem;
import org.example.e_commerce.service.OrderItemService;
import org.example.e_commerce.service.OrderService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderItemController {


    private final OrderItemService orderItemService;


    @GetMapping("/order/items")
    public List<OrderItem> getOrderItems() {
        return orderItemService.getObjects();
    }

    @GetMapping("/order/items/{id}")
    public OrderItem getOrderItem(@PathVariable Long id) {
        return orderItemService.getOneObject(id);
    }

    @PostMapping("/order/items")
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderItem postOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.postObjectOrUpdate(orderItem);
    }

    @DeleteMapping("/order/items/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteObject(id);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ReponseApi<String> orderItemNotFoundException() {
        return new ReponseApi<>(null, "Order Item Not Found", null);
    }


}
