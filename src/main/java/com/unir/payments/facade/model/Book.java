package com.unir.payments.facade.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {

    private Long id;
    private String name;
    private Double unitPrice;
    private String ISBN;
    private Long stock;
    private boolean isActive;
    private Date datePublished;
    private Double ranking;
    private String type;
    private boolean visible;
}
