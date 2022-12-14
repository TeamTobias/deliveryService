package com.tobias.clientservice.inner.repository;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.ExchangeRequest;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRequestRepository extends CrudRepository<ExchangeRequest, Long> {
    ExchangeRequest findById(int id);
    void deleteById(int id);
}
