package com.unir.payments.service.model;

import com.unir.payments.data.model.OrderDetail;
import com.unir.payments.data.model.PaymentType;
import com.unir.payments.data.model.Status;
import com.unir.payments.facade.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {

    private Long id;
    private Integer orderNumber;
    private Date dateCreated;
    private User user;
    private Status status;
    private PaymentType paymentType;
    private Double totalPrice;
    private List<OrderDetailDTO> orderDetails;
}
