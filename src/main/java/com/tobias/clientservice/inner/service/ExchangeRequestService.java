package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.ExchangeRequest;
import com.tobias.clientservice.inner.domain.RequestExchangeRequest;

public interface ExchangeRequestService {
    void addExchangeRequest(RequestExchangeRequest exchangeRequest);
    Iterable<ExchangeRequest> getExchangeRequestsAll();
    ExchangeRequest getExchangeRequest(int id);
    void deleteExchangeRequest(int id);
}
