package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.ClientRequest;
import com.tobias.clientservice.inner.domain.RequestClientRequest;
import com.tobias.clientservice.inner.repository.ClientRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClientRequestServiceImpl implements ClientRequestService{
    private final ClientRequestRepository clientRequestRepository;

    public Iterable<ClientRequest> getClientRequestsAll() {return clientRequestRepository.findAll();}

    public ClientRequest getClientRequest(int id){return clientRequestRepository.findById(id);}

    @Transactional
    public void addClientRequest(RequestClientRequest requestClientRequest) {

        ClientRequest clientRequest = clientRequestRepository.findById(requestClientRequest.getId());

        if (clientRequest == null) {
            clientRequest = ClientRequest.createClientRequest(requestClientRequest);
            clientRequestRepository.save(clientRequest);
        }
    }

    @Transactional
    public void deleteClientRequest(int clientId){
        clientRequestRepository.deleteById(clientId);
    }

    public void setClientRequest(int clientId, RequestClientRequest requestClient){

    }
}
