package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.ExchangeRequest;
import com.tobias.clientservice.inner.domain.RequestExchangeRequest;
import com.tobias.clientservice.inner.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ExchangeRequestServiceImpl implements ExchangeRequestService {
    private final ExchangeRequestRepository exchangeRequestRepository;

    public Iterable<ExchangeRequest> getExchangeRequestsAll() {return exchangeRequestRepository.findAll();}

    public ExchangeRequest getExchangeRequest(int id){return exchangeRequestRepository.findById(id);}

    @Transactional
    public void addExchangeRequest(RequestExchangeRequest requestExchangeRequest) {

        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(requestExchangeRequest.getId());

        if (exchangeRequest == null) {
            exchangeRequest = ExchangeRequest.createClientRequest(requestExchangeRequest);
            exchangeRequestRepository.save(exchangeRequest);
        }
    }

    @Transactional
    public void deleteExchangeRequest(int clientId){
        exchangeRequestRepository.deleteById(clientId);
    }
}
