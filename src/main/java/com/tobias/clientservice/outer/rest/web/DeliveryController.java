package com.tobias.clientservice.outer.rest.web;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.RequestDelivery;
import com.tobias.clientservice.inner.domain.ResponseDelivery;
import com.tobias.clientservice.inner.service.DeliveryService;
import com.tobias.clientservice.outer.adaptor.KafkaProducer;
import com.tobias.clientservice.outer.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class DeliveryController {

        private final DeliveryService deliveryService;
        private final KafkaProducer kafkaProducer;

        @GetMapping("/health_check")
        public String status(){
            return "It's Working in Delivery CUD Service";
        }

        @PostMapping("/delivery/v1")
        public HttpStatus addDelivery(@RequestBody RequestDelivery requestDelivery) {
            deliveryService.addDelivery(requestDelivery);
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            Delivery delivery = deliveryService.getDelivery(requestDelivery.getProductId());
            DeliveryDto deliveryDto = mapper.map(delivery, DeliveryDto.class);
            kafkaProducer.sendDelivery("client-request-topic", deliveryDto);
            return HttpStatus.OK;
        }

        @GetMapping("/delivery/v1")
        public ResponseEntity<List<ResponseDelivery>> allDelivery() {
            Iterable<Delivery> products = deliveryService.getDeliverysAll();
            List<ResponseDelivery> result = new ArrayList<>();
            products.forEach(v -> result.add(new ModelMapper().map(v, ResponseDelivery.class)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        @DeleteMapping("/delivery/v1/{deliveryId}")
        public HttpStatus deleteDelivery(@PathVariable("deliveryId") int deliveryId) {
            deliveryService.deleteDelivery(deliveryId);
            return HttpStatus.OK;
        }
}
