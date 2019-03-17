package com.roadTransport.RTTransport.controller;

import com.roadTransport.RTTransport.entity.TransportTemporaryDetails;
import com.roadTransport.RTTransport.model.TransportRequest;
import com.roadTransport.RTTransport.model.TransportResponse;
import com.roadTransport.RTTransport.service.TransportTemporaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TransportTempDetails")
public class TransportTemporaryDetailsController {

    @Autowired
    private TransportTemporaryDetailsService transportTemporaryDetailsService;

    @PostMapping("/add")
    public ResponseEntity<TransportResponse> add(@RequestBody TransportRequest transportRequest) throws Exception {

        TransportTemporaryDetails transportTemporaryDetails =  transportTemporaryDetailsService.add(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("please enter the otp for verification.");
        transportResponse.setOtp(transportTemporaryDetails.getOtp());
        return ResponseEntity.ok(transportResponse);
    }

    @Cacheable(value = "TransportTemporaryDetails", key = "#transportRegistrationNumber")
    @GetMapping("/getDataByMdn/{ownerMobileNumber}")
    public ResponseEntity<TransportTemporaryDetails> getDataByMdn(@PathVariable("ownerMobileNumber") long ownerMobileNumber) throws Exception {

        TransportTemporaryDetails transportTemporaryDetails = transportTemporaryDetailsService.getDataByMdn(ownerMobileNumber);
        return ResponseEntity.ok(transportTemporaryDetails);
    }

    @Cacheable(value = "TransportTemporaryDetails", key = "#transportRegistrationNumber")
    @GetMapping("/getDataByTRN/{transportRegistrationNumber}")
    public ResponseEntity<TransportTemporaryDetails> getDataByMdn(@PathVariable("transportRegistrationNumber") String transportRegistrationNumber) throws Exception {

        TransportTemporaryDetails transportTemporaryDetails = transportTemporaryDetailsService.getDataByTRN(transportRegistrationNumber);
        return ResponseEntity.ok(transportTemporaryDetails);
    }

    @Cacheable(value = "TransportTemporaryDetails", key = "#transportRegistrationNumber")
    @GetMapping("/getlistByPage")
    public Page<TransportTemporaryDetails> getList(Pageable pageable){

        Page<TransportTemporaryDetails> list = transportTemporaryDetailsService.listAllByPage(pageable);
        return list;
    }
}
