package org.example.e_commerce.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.e_commerce.model.Cart;
import org.example.e_commerce.model.DTO.CartDTO;
import org.example.e_commerce.model.User;
import org.example.e_commerce.service.CartService;
import org.example.e_commerce.service.UserService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    private final UserService userService;


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
    public Cart postCart(@RequestBody @Valid CartDTO cartDTO) {

        User user = userService.getOneObject((long) cartDTO.getUserId());

        Cart cart = new Cart();
        cart.setUser(user);

        return cartService.postObjectOrUpdate(cart);
    }

    /**
     * Endpoint pour récupérer le panier d'un utilisateur.
     */
    @GetMapping("/carts/user/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        Optional<Cart> cart = cartService.getCartByUserId(userId);

        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cart found for user with ID: " + userId);
        }
    }


    @DeleteMapping("/carts/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteObject(id);
    }



}
