package com.unir.payments.service;

import org.springframework.data.domain.Page;

public interface BaseService<T, R> {

    Page<T> findAll(R filter);

    T findById(Long id);

    T save(T object);

    T update(Long id, T object);

    void delete(Long id);
}
