package com.tobias.clientservice.outer.rest.web;

import com.tobias.clientservice.inner.domain.ClientRequest;
import com.tobias.clientservice.inner.domain.RequestClientRequest;
import com.tobias.clientservice.inner.domain.ResponseClientRequest;
import com.tobias.clientservice.inner.service.ClientRequestService;
import com.tobias.clientservice.outer.adaptor.KafkaProducer;
import com.tobias.clientservice.outer.dto.ClientRequestDto;
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

        private final ClientRequestService clientRequestService;
        private final KafkaProducer kafkaProducer;

        @GetMapping("/health_check")
        public String status(){
            return "It's Working in Delivery CUD Service";
        }

        @PostMapping("/delivery/v1")
        public HttpStatus addClientRequest(@RequestBody RequestClientRequest requestClientRequest) {
            clientRequestService.addClientRequest(requestClientRequest);
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            ClientRequest clientRequest = clientRequestService.getClientRequest(requestClientRequest.getProductId());
            ClientRequestDto clientRequestDto = mapper.map(clientRequest, ClientRequestDto.class);
            kafkaProducer.send("client-request-topic",clientRequestDto);
            return HttpStatus.OK;
        }

        @GetMapping("/delivery/v1")
        public ResponseEntity<List<ResponseClientRequest>> allClientRequest() {
            Iterable<ClientRequest> products = clientRequestService.getClientRequestsAll();
            List<ResponseClientRequest> result = new ArrayList<>();
            products.forEach(v -> result.add(new ModelMapper().map(v, ResponseClientRequest.class)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        @DeleteMapping("/delivery/v1/{deliveryId}")
        public HttpStatus deleteClientRequest(@PathVariable("deliveryId") int deliveryId) {
            clientRequestService.deleteClientRequest(deliveryId);
            return HttpStatus.OK;
        }
}
