package com.unir.payments.data;

import com.unir.payments.data.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusJpaRepository extends JpaRepository<Status, Long> {
}
