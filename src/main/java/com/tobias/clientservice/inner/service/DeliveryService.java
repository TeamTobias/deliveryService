package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.RequestDelivery;

public interface DeliveryService {
    void addDelivery(RequestDelivery client);
    Iterable<Delivery> getDeliverysAll();
    Delivery getDelivery(int id);
    void deleteDelivery(int id);
}
