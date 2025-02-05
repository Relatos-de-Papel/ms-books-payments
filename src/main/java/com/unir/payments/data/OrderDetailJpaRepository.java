package com.unir.payments.data;

import com.unir.payments.data.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Long> {
}
