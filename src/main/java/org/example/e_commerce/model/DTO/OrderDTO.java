package org.example.e_commerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {


    @NotNull
    @Positive
    private Double totalPrice;

    @NotNull
    private int userId;

}
