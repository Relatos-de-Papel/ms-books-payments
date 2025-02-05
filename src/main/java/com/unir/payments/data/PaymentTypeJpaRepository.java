package com.unir.payments.data;

import com.unir.payments.data.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeJpaRepository extends JpaRepository<PaymentType, Long> {
}
