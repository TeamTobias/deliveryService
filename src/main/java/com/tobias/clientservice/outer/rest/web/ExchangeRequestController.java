package com.tobias.clientservice.outer.rest.web;

import com.tobias.clientservice.inner.domain.*;
import com.tobias.clientservice.inner.service.ExchangeRequestService;
import com.tobias.clientservice.outer.adaptor.KafkaProducer;
import com.tobias.clientservice.outer.dto.ExchangeRequestDto;
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
public class ExchangeRequestController {

        private final ExchangeRequestService exchangeRequestService;
        private final KafkaProducer kafkaProducer;

        @PostMapping("/exchange/v1")
        public HttpStatus addExchangeRequest(@RequestBody RequestExchangeRequest requestExchangeRequest) {
            exchangeRequestService.addExchangeRequest(requestExchangeRequest);
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            ExchangeRequest exchangeRequest = exchangeRequestService.getExchangeRequest(requestExchangeRequest.getProductId());
            ExchangeRequestDto exchangeRequestDto = mapper.map(exchangeRequest, ExchangeRequestDto.class);
            kafkaProducer.sendExchangeReqeust("exchange-request-topic", exchangeRequestDto);
            return HttpStatus.OK;
        }

        @GetMapping("/exchange/v1")
        public ResponseEntity<List<ResponseExchangeRequest>> allExchangeRequest() {
            Iterable<ExchangeRequest> products = exchangeRequestService.getExchangeRequestsAll();
            List<ResponseExchangeRequest> result = new ArrayList<>();
            products.forEach(v -> result.add(new ModelMapper().map(v, ResponseExchangeRequest.class)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        @DeleteMapping("/exchange/v1/{exchangeId}")
        public HttpStatus deleteExchangeRequest(@PathVariable("exchangeId") int exchangeId) {
            exchangeRequestService.deleteExchangeRequest(exchangeId);
            return HttpStatus.OK;
        }
}
