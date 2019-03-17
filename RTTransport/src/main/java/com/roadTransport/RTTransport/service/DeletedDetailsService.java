package com.roadTransport.RTTransport.service;

import com.roadTransport.RTTransport.entity.DeletedTransportDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DeletedDetailsService {

    public Page<DeletedTransportDetails> listAllByPage(Pageable pageable);
    public DeletedTransportDetails findByTRN(String transportRegistrationNumber) throws Exception;

}
