package com.roadTransport.RTTransport.serviceImpl;

import com.roadTransport.RTTransport.entity.TransportDetails;
import com.roadTransport.RTTransport.entity.TransportTemporaryDetails;
import com.roadTransport.RTTransport.model.otp.OtpDetails;
import com.roadTransport.RTTransport.model.TransportRequest;
import com.roadTransport.RTTransport.otpService.OtpService;
import com.roadTransport.RTTransport.repository.TransportDetailsRepository;
import com.roadTransport.RTTransport.repository.TransportTemporaryDetailsRepository;
import com.roadTransport.RTTransport.repository.TransportTemporaryPageDetailsRepository;
import com.roadTransport.RTTransport.service.TransportTemporaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class TransportTemporaryDetailsServiceImpl implements TransportTemporaryDetailsService {

    @Autowired
    private TransportTemporaryPageDetailsRepository transportTemporaryPageDetailsRepository;

    @Autowired
    private TransportTemporaryDetailsRepository transportTemporaryDetailsRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private TransportDetailsRepository transportDetailsRepository;

    @Override
    public TransportTemporaryDetails add(TransportRequest transportRequest) throws Exception {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        TransportTemporaryDetails transportTemporaryDetails = transportTemporaryDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());

        if(transportDetails !=null){
            throw new Exception("Transport already Exist.");
        }

        if(transportTemporaryDetails !=null){

            transportTemporaryDetails.setCreatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
            transportTemporaryDetails.setOwnerPanCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerPanCardImage().getBytes()));
            transportTemporaryDetails.setKyc(false);
            transportTemporaryDetails.setOwnerAadhaarCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerAadhaarCardImage().getBytes()));
            transportTemporaryDetails.setOwnerAadhaarNumber(transportRequest.getOwnerAadhaarNumber());
            transportTemporaryDetails.setOwnerImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerImage().getBytes()));
            transportTemporaryDetails.setOwnerPanCardNumber(transportRequest.getOwnerPanCardNumber());
            transportTemporaryDetails.setStatus(false);
            transportTemporaryDetails.setTotalBuses(transportRequest.getTotalBuses());
            transportTemporaryDetails.setTotalLoadVehicles(transportRequest.getTotalLoadVehicles());
            transportTemporaryDetails.setOtherVehicles(transportRequest.getOtherVehicles());
            transportTemporaryDetails.setTotalPersonalVehicles(transportRequest.getTotalPersonalVehicles());
            transportTemporaryDetails.setTotalVehicles(transportRequest.getTotalVehicles());
            transportTemporaryDetails.setTransportLicenceImage(Base64.getEncoder().encodeToString(transportRequest.getTransportLicenceImage().getBytes()));
            transportTemporaryDetails.setTransportLocation(transportRequest.getTransportLocation());
            transportTemporaryDetails.setTransportName(transportRequest.getTransportName());
            transportTemporaryDetails.setTransportOwnerMobileNumber(transportRequest.getTransportOwnerMobileNumber());
            transportTemporaryDetails.setTransportOwnerName(transportRequest.getTransportOwnerName());
            transportTemporaryDetails.setTransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
            transportTemporaryDetails.setTransportLogo(Base64.getEncoder().encodeToString(transportRequest.getTransportLogo().getBytes()));

            OtpDetails otpDetails = otpService.getOtp(Long.parseLong(transportRequest.getTransportOwnerMobileNumber()));
            transportTemporaryDetails.setOtp(otpDetails.getOtpNumber());
            transportTemporaryDetailsRepository.saveAndFlush(transportTemporaryDetails);
            return transportTemporaryDetails;
        }
        else{

            TransportTemporaryDetails transportTemporaryDetails1 = new TransportTemporaryDetails();
            transportTemporaryDetails1.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
            transportTemporaryDetails1.setOwnerPanCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerPanCardImage().getBytes()));
            transportTemporaryDetails1.setKyc(false);
            transportTemporaryDetails1.setOwnerAadhaarCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerAadhaarCardImage().getBytes()));
            transportTemporaryDetails1.setOwnerAadhaarNumber(transportRequest.getOwnerAadhaarNumber());
            transportTemporaryDetails1.setOwnerImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerImage().getBytes()));
            transportTemporaryDetails1.setOwnerPanCardNumber(transportRequest.getOwnerPanCardNumber());
            transportTemporaryDetails1.setStatus(false);
            transportTemporaryDetails1.setTotalBuses(transportRequest.getTotalBuses());
            transportTemporaryDetails1.setTotalLoadVehicles(transportRequest.getTotalLoadVehicles());
            transportTemporaryDetails1.setOtherVehicles(transportRequest.getOtherVehicles());
            transportTemporaryDetails1.setTotalPersonalVehicles(transportRequest.getTotalPersonalVehicles());
            transportTemporaryDetails1.setTotalVehicles(transportRequest.getTotalVehicles());
            transportTemporaryDetails1.setTransportLicenceImage(Base64.getEncoder().encodeToString(transportRequest.getTransportLicenceImage().getBytes()));
            transportTemporaryDetails1.setTransportLocation(transportRequest.getTransportLocation());
            transportTemporaryDetails1.setTransportName(transportRequest.getTransportName());
            transportTemporaryDetails1.setTransportOwnerMobileNumber(transportRequest.getTransportOwnerMobileNumber());
            transportTemporaryDetails1.setTransportOwnerName(transportRequest.getTransportOwnerName());
            transportTemporaryDetails1.setTransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
            transportTemporaryDetails1.setTransportLogo(Base64.getEncoder().encodeToString(transportRequest.getTransportLogo().getBytes()));

            OtpDetails otpDetails = otpService.getOtp(Long.parseLong(transportRequest.getTransportOwnerMobileNumber()));
            transportTemporaryDetails1.setOtp(otpDetails.getOtpNumber());
            transportTemporaryDetailsRepository.saveAndFlush(transportTemporaryDetails);
            return transportTemporaryDetails;
        }
    }

    @Override
    public TransportTemporaryDetails getDataByMdn(String ownerMobileNumber) throws Exception {

        TransportTemporaryDetails transportTemporaryDetails = transportTemporaryDetailsRepository.findByMdn(ownerMobileNumber);
        if (transportTemporaryDetails==null){
            throw new Exception("Transport Data not Exist.");
        }
        return transportTemporaryDetails;
    }

    @Override
    public TransportTemporaryDetails getDataByTRN(String transportRegistrationNumber) throws Exception {
        TransportTemporaryDetails transportTemporaryDetails = transportTemporaryDetailsRepository.findBytransportRegistrationNumber(transportRegistrationNumber);
        if (transportTemporaryDetails==null){
            throw new Exception("Transport Data not Exist.");
        }
        return transportTemporaryDetails;
    }

    @Override
    public Page<TransportTemporaryDetails> listAllByPage(Pageable pageable) {
        return transportTemporaryPageDetailsRepository.findAll(pageable);
    }
}