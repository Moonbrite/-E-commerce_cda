package org.example.e_commerce.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.e_commerce.model.DTO.OrderItemDTO;
import org.example.e_commerce.model.Order;
import org.example.e_commerce.model.OrderItem;
import org.example.e_commerce.model.Product;
import org.example.e_commerce.service.OrderItemService;
import org.example.e_commerce.service.OrderService;
import org.example.e_commerce.service.ProductService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderItemController {


    private final OrderItemService orderItemService;

    private final OrderService orderService;

    private final ProductService productService;


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
    public List<OrderItem> postOrderItems(@RequestBody @Valid OrderItemDTO orderItemDTO) {
        List<OrderItem> orderItems = new ArrayList<>();


        // Récupérer l'ordre associé
        Order order = orderService.getOneObject((long) orderItemDTO.getOrderId());

        // Récupérer le produit associé
        Product product = productService.getOneObject((long) orderItemDTO.getProductId());

        // Créer un nouvel OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDTO.getQuantity());


        // Sauvegarder chaque OrderItem
        OrderItem savedOrderItem = orderItemService.postObjectOrUpdate(orderItem);
        orderItems.add(savedOrderItem);


        return orderItems;
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
