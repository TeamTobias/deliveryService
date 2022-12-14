package com.tobias.clientservice.outer.rest.web;

import com.tobias.clientservice.inner.domain.*;
import com.tobias.clientservice.inner.service.RefundRequestService;
import com.tobias.clientservice.outer.adaptor.KafkaProducer;
import com.tobias.clientservice.outer.dto.ExchangeRequestDto;
import com.tobias.clientservice.outer.dto.RefundRequestDto;
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
public class RefundRequestController {

        private final RefundRequestService refundRequestService;
        private final KafkaProducer kafkaProducer;

        @PostMapping("/refund/v1")
        public HttpStatus addRefundRequest(@RequestBody RequestRefundRequest requestRefundRequest) {
            refundRequestService.addRefundRequest(requestRefundRequest);
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            RefundRequest refundRequest = refundRequestService.getRefundRequest(requestRefundRequest.getProductId());
            RefundRequestDto refundRequestDto = mapper.map(refundRequest, RefundRequestDto.class);
            kafkaProducer.sendRefundRequest("refund-request-topic", refundRequestDto);
            return HttpStatus.OK;
        }

        @GetMapping("/refund/v1")
        public ResponseEntity<List<ResponseRefundRequest>> allRefundRequest() {
            Iterable<RefundRequest> products = refundRequestService.getRefundRequestsAll();
            List<ResponseRefundRequest> result = new ArrayList<>();
            products.forEach(v -> result.add(new ModelMapper().map(v, ResponseRefundRequest.class)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        @DeleteMapping("/refund/v1/{refundId}")
        public HttpStatus deleteRefundRequest(@PathVariable("refundId") int refundId) {
            refundRequestService.deleteRefundRequest(refundId);
            return HttpStatus.OK;
        }
}
