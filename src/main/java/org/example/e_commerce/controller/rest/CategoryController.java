package org.example.e_commerce.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.e_commerce.model.*;
import org.example.e_commerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getCarts() {
        return categoryService.getObjects();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.getOneObject(id);
    }

    @PostMapping("/category")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category postPayment(@RequestBody Category category) {
        return categoryService.postObjectOrUpdate(category);
    }

    @DeleteMapping("/category/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        categoryService.deleteObject(id);
    }

}
