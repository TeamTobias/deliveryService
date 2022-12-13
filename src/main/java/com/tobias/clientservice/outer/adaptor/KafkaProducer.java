package com.tobias.clientservice.outer.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobias.clientservice.outer.dto.ClientRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClientRequestDto send(String kafkaTopic, ClientRequestDto clientRequestDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(clientRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
        log.info("Kafka Producer send data from the clientRequest: " + clientRequestDto);

        return clientRequestDto;
    }

    // TODO: service interface를 통해 생성 (리팩터링할 것)
}
