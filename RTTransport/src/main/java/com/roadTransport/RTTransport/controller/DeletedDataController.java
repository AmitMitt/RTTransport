package com.roadTransport.RTTransport.controller;

import com.roadTransport.RTTransport.entity.DeletedTransportDetails;
import com.roadTransport.RTTransport.service.DeletedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DeletedData")
public class DeletedDataController {

    @Autowired
    private DeletedDetailsService deletedDetailsService;

    @Cacheable(value = "DeletedTransport", key = "#transportRegistrationNumber")
    @GetMapping("/getDeletedData/{transportRegistrationNumber}")
    public ResponseEntity<DeletedTransportDetails> getByMdn(@PathVariable("transportRegistrationNumber") String transportRegistrationNumber) throws Exception {

        DeletedTransportDetails deletedTransportDetails = deletedDetailsService.findByTRN(transportRegistrationNumber);
        return ResponseEntity.ok(deletedTransportDetails);
    }

    @Cacheable(value = "DeletedTransport", key = "#transportRegistrationNumber")
    @GetMapping("/getListOfDeletedData")
    public Page<DeletedTransportDetails> getData(Pageable pageable){

        Page<DeletedTransportDetails> list = deletedDetailsService.listAllByPage(PageRequest.of(0, 10, Sort.Direction.ASC));
        return list;
    }

}
