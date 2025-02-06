package com.unir.payments.service.impl;

import com.unir.payments.controller.model.FilterBaseRequest;
import com.unir.payments.data.OrderDetailJpaRepository;
import com.unir.payments.data.OrderJpaRepository;
import com.unir.payments.data.model.Order;
import com.unir.payments.data.model.OrderDetail;
import com.unir.payments.facade.BooksFacade;
import com.unir.payments.facade.UsersFacade;
import com.unir.payments.facade.model.Book;
import com.unir.payments.facade.model.User;
import com.unir.payments.service.OrderService;
import com.unir.payments.service.model.OrderDTO;
import com.unir.payments.service.model.OrderDetailDTO;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private OrderDetailJpaRepository orderDetailJpaRepository;
    @Autowired
    private BooksFacade booksFacade;
    @Autowired
    private UsersFacade usersFacade;


    @Override
    public Page<Order> findAll(FilterBaseRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());
        return this.orderJpaRepository.findAll(pageable);
    }

    @Override
    public Order findById(Long id) {
        Order order = this.orderJpaRepository.findById(id).orElse(null);

        if (order != null) {
            order.getOrderDetails().forEach(detail -> {
                detail.setOrder(null);
            });
        }
        return order;
    }

    @Override
    public Order save(Order object) {
        object.setOrderDetails(new ArrayList<>());
        return this.orderJpaRepository.save(object);
    }

    @Override
    public Order update(Long id, Order object) {
        if (!this.orderJpaRepository.existsById(id)) {
            throw new FindException("Order not found");
        }
        return this.orderJpaRepository.saveAndFlush(object);
    }

    @Override
    public void delete(Long id) {
        this.orderJpaRepository.deleteById(id);
    }

    @Override
    public Boolean deleteOrderDetail(Long orderDetailId) {
        if (!this.orderDetailJpaRepository.existsById(orderDetailId)) {
            return false;
        }
        this.orderDetailJpaRepository.deleteById(orderDetailId);
        return true;
    }

    @Override
    public Order saveOrder(Order object) throws ValidationException {
        List<OrderDetail> detailList = object.getOrderDetails();
        validUser(object);
        validBook(detailList);

        Double totalPrice = detailList.stream().map(OrderServiceImpl::multiply).reduce(0.0, Double::sum);
        object.setTotalPrice(totalPrice);
        object.setOrderDetails(new ArrayList<>());
        Order newOrder = this.orderJpaRepository.save(object);

        detailList.forEach(detail -> detail.setOrder(newOrder));
        List<OrderDetail> newDetailList = this.orderDetailJpaRepository.saveAll(detailList);
        newDetailList.forEach(detail -> detail.setOrder(null));
        newOrder.setOrderDetails(newDetailList);

        return newOrder;
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order order = this.orderJpaRepository.findById(orderId).orElse(null);
        OrderDTO dto = OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .dateCreated(order.getDateCreated())
                .paymentType(order.getPaymentType())
                .user(usersFacade.getUser(order.getUserId().toString()))
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();

       List<OrderDetailDTO> orderDetailDTOS = order.getOrderDetails().stream().map(detail -> OrderDetailDTO.builder()
                .id(detail.getId())
                .price(detail.getPrice())
                .quantity(detail.getQuantity())
                .book(booksFacade.getBook(detail.getBookId().toString()))
                .build()).toList();

       dto.setOrderDetails(orderDetailDTOS);
        return dto;
    }

    private static Double multiply(OrderDetail orderDetail) {
        return orderDetail.getPrice() * orderDetail.getQuantity();
    }

    private void validBook(List<OrderDetail> orderDetails) throws ValidationException {
        for (OrderDetail orderDetail : orderDetails) {
            Book book = booksFacade.getBook(orderDetail.getBookId().toString());
            if (book == null) {
                throw new ValidationException("El libro no existe");
            }
            if (!book.isVisible()) {
                throw new ValidationException("El libro no es visible");
            }
            if (book.getStock() < orderDetail.getQuantity()) {
                throw new ValidationException("No hay stock");
            }
            orderDetail.setPrice(book.getUnitPrice());
        }
    }

    private void validUser(Order order) throws ValidationException {
        User user = usersFacade.getUser(order.getUserId().toString());
        if (user == null) {
            throw new ValidationException("El usuario no existe");
        }
    }

}
