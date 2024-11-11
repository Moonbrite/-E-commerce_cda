package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.CartItem;
import org.example.e_commerce.service.CartItemService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CartItemController {

    private final CartItemService cartItemService;


    @GetMapping("/cart/items")
    public List<CartItem> getCarts() {
        return cartItemService.getObjects();
    }

    @GetMapping("/cart/item/{id}")
    public CartItem getCart(@PathVariable Long id) {
        return cartItemService.getOneObject(id);
    }

    @PostMapping("/cart/items")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CartItem postCart(@RequestBody CartItem cartItem) {
        return cartItemService.postObjectOrUpdate(cartItem);
    }

    @DeleteMapping("/cart/items/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteObject(id);
    }


}
