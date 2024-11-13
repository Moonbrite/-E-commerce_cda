package org.example.e_commerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemDTO {

    @NotNull
    @Positive
    private double price;

    @NotNull
    @Positive
    private int quantity;

    @NotNull
    private int orderId;

    @NotNull
    private int productId;


}
