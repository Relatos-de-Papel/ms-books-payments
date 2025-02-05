package com.unir.payments.service.impl;

import com.unir.payments.controller.model.FilterBaseRequest;
import com.unir.payments.data.StatusJpaRepository;
import com.unir.payments.data.model.Status;
import com.unir.payments.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    private StatusJpaRepository statusJpaRepository;

    @Override
    public Page<Status> findAll(FilterBaseRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());
        return this.statusJpaRepository.findAll(pageable);
    }

    @Override
    public Status findById(Long id) {
        return this.statusJpaRepository.findById(id).orElseThrow(() -> new FindException("Status not found"));
    }

    @Override
    public Status save(Status object) {
        return this.statusJpaRepository.save(object);
    }

    @Override
    public Status update(Long id, Status object) {
        if (!this.statusJpaRepository.existsById(id)) {
            throw new FindException("Status not found");
        }
        return this.statusJpaRepository.saveAndFlush(object);
    }

    @Override
    public void delete(Long id) {

    }

}
