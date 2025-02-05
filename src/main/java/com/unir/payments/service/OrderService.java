package com.unir.payments.service;

import com.unir.payments.controller.model.FilterBaseRequest;
import com.unir.payments.data.model.Order;
import com.unir.payments.data.model.OrderDetail;
import jakarta.xml.bind.ValidationException;

public interface OrderService extends BaseService<Order, FilterBaseRequest> {

    Boolean deleteOrderDetail(Long orderDetailId);

    Order saveOrder(Order object) throws ValidationException;
}
