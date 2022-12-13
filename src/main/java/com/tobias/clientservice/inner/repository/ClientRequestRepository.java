package com.tobias.clientservice.inner.repository;

import com.tobias.clientservice.inner.domain.ClientRequest;
import org.springframework.data.repository.CrudRepository;

public interface ClientRequestRepository extends CrudRepository<ClientRequest, Long> {
    ClientRequest findById(int id);
    void deleteById(int id);
}
