package com.unir.payments.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "book_id")
    private Long bookId;

    private Integer quantity;

    private Double price;


}
