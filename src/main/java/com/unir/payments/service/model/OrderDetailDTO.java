package com.unir.payments.service.model;

import com.unir.payments.data.model.Order;
import com.unir.payments.facade.model.Book;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDetailDTO {

    private Long id;
    private Book book;
    private Integer quantity;
    private Double price;
}
