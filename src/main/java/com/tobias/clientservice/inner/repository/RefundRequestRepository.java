package com.tobias.clientservice.inner.repository;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.RefundRequest;
import org.springframework.data.repository.CrudRepository;

public interface RefundRequestRepository extends CrudRepository<RefundRequest, Long> {
    RefundRequest findById(int id);
    void deleteById(int id);
}
