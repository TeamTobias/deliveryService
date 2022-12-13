package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.ClientRequest;
import com.tobias.clientservice.inner.domain.RequestClientRequest;

public interface ClientRequestService {
    void addClientRequest(RequestClientRequest client);
    Iterable<ClientRequest> getClientRequestsAll();
    ClientRequest getClientRequest(int id);
    void deleteClientRequest(int id);
    void setClientRequest(int clientId, RequestClientRequest client);
}
