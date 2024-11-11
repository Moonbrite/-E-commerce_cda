package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.Payment;
import org.example.e_commerce.model.Product;
import org.example.e_commerce.service.ProductService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getObjects();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getOneObject(id);
    }

    @PostMapping("/products")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product postProduct(@RequestBody Product product) {
        return productService.postObjectOrUpdate(product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteObject(id);
    }



}
