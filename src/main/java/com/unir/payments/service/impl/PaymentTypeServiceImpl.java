package com.unir.payments.service.impl;

import com.unir.payments.controller.model.FilterBaseRequest;
import com.unir.payments.data.PaymentTypeJpaRepository;
import com.unir.payments.data.model.PaymentType;
import com.unir.payments.service.PaymentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

@Service
@AllArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {


    private PaymentTypeJpaRepository paymentTypeJpaRepository;

    @Override
    public Page<PaymentType> findAll(FilterBaseRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());
        return this.paymentTypeJpaRepository.findAll(pageable);
    }

    @Override
    public PaymentType findById(Long id) {
        return this.paymentTypeJpaRepository.findById(id).orElseThrow(() -> new FindException("Payment Type not found"));
    }

    @Override
    public PaymentType save(PaymentType object) {
        return this.paymentTypeJpaRepository.save(object);
    }

    @Override
    public PaymentType update(Long id, PaymentType object) {
        if (!this.paymentTypeJpaRepository.existsById(id)) {
            throw new FindException("Payment Type not found");
        }
        return this.paymentTypeJpaRepository.save(object);
    }

    @Override
    public void delete(Long id) {

    }
}
