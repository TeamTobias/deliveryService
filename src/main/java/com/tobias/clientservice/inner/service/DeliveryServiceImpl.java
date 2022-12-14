package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.RequestDelivery;
import com.tobias.clientservice.inner.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public Iterable<Delivery> getDeliverysAll() {return deliveryRepository.findAll();}

    public Delivery getDelivery(int id){return deliveryRepository.findById(id);}

    @Transactional
    public void addDelivery(RequestDelivery requestDelivery) {

        Delivery delivery = deliveryRepository.findById(requestDelivery.getId());

        if (delivery == null) {
            delivery = Delivery.createDelivery(requestDelivery);
            deliveryRepository.save(delivery);
        }
    }

    @Transactional
    public void deleteDelivery(int clientId){
        deliveryRepository.deleteById(clientId);
    }
}
