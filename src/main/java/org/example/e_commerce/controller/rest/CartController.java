package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.Cart;
import org.example.e_commerce.service.CartService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;


    @GetMapping("/carts")
    public List<Cart> getCarts() {
        return cartService.getObjects();
    }

    @GetMapping("/carts/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartService.getOneObject(id);
    }

    @PostMapping("/carts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.postObjectOrUpdate(cart);
    }

    @DeleteMapping("/carts/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteObject(id);
    }



}
