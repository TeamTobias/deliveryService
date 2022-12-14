package com.tobias.clientservice.inner.repository;

import com.tobias.clientservice.inner.domain.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    Delivery findById(int id);
    void deleteById(int id);
}
