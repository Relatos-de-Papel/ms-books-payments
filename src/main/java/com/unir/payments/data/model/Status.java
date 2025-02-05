package com.unir.payments.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
