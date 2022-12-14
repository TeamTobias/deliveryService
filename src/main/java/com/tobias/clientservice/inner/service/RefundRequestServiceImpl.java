package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.Delivery;
import com.tobias.clientservice.inner.domain.RefundRequest;
import com.tobias.clientservice.inner.domain.RequestDelivery;
import com.tobias.clientservice.inner.domain.RequestRefundRequest;
import com.tobias.clientservice.inner.repository.RefundRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RefundRequestServiceImpl implements RefundRequestService {
    private final RefundRequestRepository refundRequestRepository;

    public Iterable<RefundRequest> getRefundRequestsAll() {return refundRequestRepository.findAll();}

    public RefundRequest getRefundRequest(int id){return refundRequestRepository.findById(id);}

    @Transactional
    public void addRefundRequest(RequestRefundRequest requestRefundRequest) {

        RefundRequest refundRequest = refundRequestRepository.findById(requestRefundRequest.getId());

        if (refundRequest == null) {
            refundRequest = RefundRequest.createRefundRequest(requestRefundRequest);
            refundRequestRepository.save(refundRequest);
        }
    }

    @Transactional
    public void deleteRefundRequest(int clientId){
        refundRequestRepository.deleteById(clientId);
    }
}
