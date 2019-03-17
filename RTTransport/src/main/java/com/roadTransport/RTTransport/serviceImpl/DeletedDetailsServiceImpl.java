package com.roadTransport.RTTransport.serviceImpl;

import com.roadTransport.RTTransport.entity.DeletedTransportDetails;
import com.roadTransport.RTTransport.repository.deletedRepository.DeletedTransportPageDetailsRepositoy;
import com.roadTransport.RTTransport.repository.deletedRepository.DeletedTransportRepository;
import com.roadTransport.RTTransport.service.DeletedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeletedDetailsServiceImpl implements DeletedDetailsService {

    @Autowired
    private DeletedTransportPageDetailsRepositoy deletedTransportPageDetailsRepositoy;

    @Autowired
    private DeletedTransportRepository deletedTransportRepository;

    @Override
    public Page<DeletedTransportDetails> listAllByPage(Pageable pageable) {
        return deletedTransportPageDetailsRepositoy.findAll(pageable);
    }

    @Override
    public DeletedTransportDetails findByTRN(String transportRegistrationNumber) throws Exception {

        DeletedTransportDetails deletedTransportDetails = deletedTransportRepository.findByTRN(transportRegistrationNumber);
        if(deletedTransportDetails == null){
            throw new Exception("Data Not Exist.");
        }

        return deletedTransportDetails;
    }
}
