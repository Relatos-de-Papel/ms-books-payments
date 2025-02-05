package com.unir.payments.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
