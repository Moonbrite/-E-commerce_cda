package org.example.e_commerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartDTO {

    @NotNull
    private int userId;

}
