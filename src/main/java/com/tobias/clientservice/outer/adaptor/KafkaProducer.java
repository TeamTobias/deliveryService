package com.tobias.clientservice.outer.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobias.clientservice.inner.domain.RefundRequest;
import com.tobias.clientservice.outer.dto.DeliveryDto;
import com.tobias.clientservice.outer.dto.ExchangeRequestDto;
import com.tobias.clientservice.outer.dto.RefundRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DeliveryDto sendDelivery(String kafkaTopic, DeliveryDto deliveryDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(deliveryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
        log.info("Kafka Producer send data from the delivery: " + deliveryDto);

        return deliveryDto;
    }

    public RefundRequestDto sendRefundRequest(String kafkaTopic, RefundRequestDto refundRequestDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(refundRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
        log.info("Kafka Producer send data from the refundrequest: " + refundRequestDto);

        return refundRequestDto;
    }

    public ExchangeRequestDto sendExchangeRequest(String kafkaTopic, ExchangeRequestDto exchangeRequestDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(exchangeRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
        log.info("Kafka Producer send data from the exchangerequest: " + exchangeRequestDto);

        return exchangeRequestDto;
    }

}
