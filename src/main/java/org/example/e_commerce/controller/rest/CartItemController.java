package org.example.e_commerce.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.e_commerce.model.Cart;
import org.example.e_commerce.model.CartItem;
import org.example.e_commerce.model.DTO.CartItemDTO;
import org.example.e_commerce.model.Product;
import org.example.e_commerce.service.CartItemService;
import org.example.e_commerce.service.CartService;
import org.example.e_commerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CartItemController {

    private final CartItemService cartItemService;

    private final ProductService productService;

    private final CartService cartService;


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
    public CartItem postCart(@RequestBody @Valid CartItemDTO cartItemDTO) {

        CartItem cartItem = new CartItem();

        Product product = productService.getOneObject((long) cartItemDTO.getProductId());
        Cart cart = cartService.getOneObject((long) cartItemDTO.getCartId());

        cartItem.setPrice(product.getPrice() * cartItemDTO.getQuantity());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setCart(cart);
        cartItem.setProduct(product);


        return cartItemService.postObjectOrUpdate(cartItem);
    }

    @DeleteMapping("/cart/items/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCartItem(@PathVariable Long id) {

        CartItem cartItem = cartItemService.getOneObject(id);
        Cart cart = cartItem.getCart();
        cartItemService.deleteObject(id);

        int cartSize =  cart.getItems().size();


        if (cartSize == 0){
            cartService.deleteObject(cart.getId());
        }

    }


}
