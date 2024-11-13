package org.example.e_commerce.model.DTO;

import lombok.Data;

@Data
public class CartItemDTO {

    private int quantity;

    private int cartId;

    private int productId;

}
