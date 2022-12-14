package com.tobias.clientservice.inner.service;

import com.tobias.clientservice.inner.domain.RefundRequest;
import com.tobias.clientservice.inner.domain.RequestRefundRequest;

public interface RefundRequestService {
    void addRefundRequest(RequestRefundRequest client);
    Iterable<RefundRequest> getRefundRequestsAll();
    RefundRequest getRefundRequest(int id);
    void deleteRefundRequest(int id);
}
