package org.example.e_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id = null;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private String paymentIntentId;

    private Double amount;

    private String status;

    private String currency;


}
