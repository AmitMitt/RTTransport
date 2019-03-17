package com.roadTransport.RTTransport.service;

import com.roadTransport.RTTransport.entity.TransportTemporaryDetails;
import com.roadTransport.RTTransport.model.TransportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TransportTemporaryDetailsService {

    public TransportTemporaryDetails add (TransportRequest transportRequest) throws Exception;
    public TransportTemporaryDetails getDataByMdn(long ownerMobileNumber) throws Exception;
    public TransportTemporaryDetails getDataByTRN(String transportRegistrationNumber) throws Exception;
    public Page<TransportTemporaryDetails> listAllByPage(Pageable pageable);

}
