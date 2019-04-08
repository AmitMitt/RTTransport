package com.roadTransport.RTTransport.controller;

import com.roadTransport.RTTransport.entity.TransportDetails;
import com.roadTransport.RTTransport.model.SignUpRequest;
import com.roadTransport.RTTransport.model.otp.OtpRequest;
import com.roadTransport.RTTransport.model.TransportRequest;
import com.roadTransport.RTTransport.model.TransportResponse;
import com.roadTransport.RTTransport.service.TransportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TransportDetails")
public class TransportDetailsController {

    @Autowired
    private TransportDetailsService transportDetailsService;

    @PostMapping("/addUser")
    public ResponseEntity<TransportResponse> addUser(@RequestBody SignUpRequest signUpRequest) throws Exception {

        transportDetailsService.add(signUpRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Driver Add Successfully.");
        return ResponseEntity.ok(transportResponse);
    }

    @Cacheable(value = "TransportDetails", key = "#transportRegistrationNumber")
    @GetMapping("/getlistByPage")
    public Page<TransportDetails> getList(Pageable pageable){

        Page<TransportDetails> list = transportDetailsService.listAllByPage(PageRequest.of(0,10, Sort.Direction.ASC));
        return list;
    }

    @Cacheable(value = "TransportDetails", key = "#transportRegistrationNumber")
    @GetMapping("/getDataByMdn/{ownerMobileNumber}")
    public ResponseEntity<TransportDetails> getListByMdn(@PathVariable("ownerMobileNumber") String ownerMobileNumber) throws Exception {

        TransportDetails transportDetails = transportDetailsService.getByMobileNumeber(ownerMobileNumber);
        return ResponseEntity.ok(transportDetails);
    }

    @Cacheable(value = "TransportDetails", key = "#transportRegistrationNumber")
    @GetMapping("/getlistByTRN/{transportRegistrationNumber}")
    public ResponseEntity<TransportDetails> getListByTRN(@PathVariable("transportRegistrationNumber") String transportRegistrationNumber) throws Exception {

        TransportDetails transportDetails = transportDetailsService.getByTransportNumber(transportRegistrationNumber);
        return ResponseEntity.ok(transportDetails);
    }

    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updateDetails")
    public ResponseEntity<TransportResponse> update (@RequestBody TransportRequest transportRequest){

        transportDetailsService.updateTranportDetails(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Successfully Updated.");
        return ResponseEntity.ok(transportResponse);
    }

    @CacheEvict(value = "TransportDetails", allEntries = true)
    @DeleteMapping("/delete")
    public ResponseEntity<TransportResponse> delete(@RequestBody OtpRequest otpRequest) throws Exception {

        transportDetailsService.deleteByOtp(otpRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("User Successfully Deleted.");
        transportResponse.setOtp(otpRequest.getOtp());
        return ResponseEntity.ok(transportResponse);
    }


    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updateOwnerImage")
    public ResponseEntity<TransportResponse> updateOwnerImage(@RequestBody TransportRequest transportRequest){

        transportDetailsService.updateOwnerImage(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(transportResponse);
    }

    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updateAdhaarImage")
    public ResponseEntity<TransportResponse> updateAdhaarImage(@RequestBody TransportRequest transportRequest){

        transportDetailsService.updateAadhaarImage(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(transportResponse);
    }

    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updatePanCardImage")
    public ResponseEntity<TransportResponse> updatePanCardImage(@RequestBody TransportRequest transportRequest){

        transportDetailsService.updatePanImage(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(transportResponse);
    }

    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updateTransportLicenceImage")
    public ResponseEntity<TransportResponse> updateTransportLicenceImage(@RequestBody TransportRequest transportRequest){

        transportDetailsService.updateTransportLicence(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(transportResponse);
    }

    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updateTransportLogoImage")
    public ResponseEntity<TransportResponse> updateTransportLogoImage (@RequestBody TransportRequest transportRequest){

        transportDetailsService.updateTransportLogo(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Image Updated Successfully.");
        return ResponseEntity.ok(transportResponse);
    }

    @CachePut(value = "TransportDetails", key = "#transportRegistrationNumber")
    @PutMapping("/updateStatus")
    public ResponseEntity<TransportResponse> updateStatus(@RequestBody TransportRequest transportRequest){

        transportDetailsService.updateStatus(transportRequest);
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setMessage("Status has been change.");
        return ResponseEntity.ok(transportResponse);
    }
}
