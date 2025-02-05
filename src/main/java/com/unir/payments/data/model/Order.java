package com.unir.payments.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "user_id")
    private Long userId;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    private Status status;

    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentType paymentType;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
