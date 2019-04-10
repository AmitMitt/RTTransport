package com.roadTransport.RTTransport.serviceImpl;

import com.roadTransport.RTTransport.entity.Admin;
import com.roadTransport.RTTransport.entity.RoleName;
import com.roadTransport.RTTransport.entity.TransportDetails;
import com.roadTransport.RTTransport.model.SignUpRequest;
import com.roadTransport.RTTransport.model.otp.OtpRequest;
import com.roadTransport.RTTransport.model.TransportRequest;
import com.roadTransport.RTTransport.otpService.OtpService;
import com.roadTransport.RTTransport.repository.AdminRepository;
import com.roadTransport.RTTransport.repository.TransportDetailsRepository;
import com.roadTransport.RTTransport.repository.TransportPageDetailsRepository;
import com.roadTransport.RTTransport.service.TransportDetailsService;
import com.roadTransport.RTTransport.walletService.WalletRequest;
import com.roadTransport.RTTransport.walletService.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class TransportDetailsServiceImpl implements TransportDetailsService {

    @Autowired
    private TransportPageDetailsRepository transportPageDetailsRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private TransportDetailsRepository transportDetailsRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public TransportDetails add(SignUpRequest signUpRequest) {

          TransportDetails transportDetails = new TransportDetails();

          transportDetails.setDeleted(false);
          transportDetails.setStatus(false);
          transportDetails.setTransportOwnerName(signUpRequest.getName());
          transportDetails.setEmail(signUpRequest.getEmail());
          transportDetails.setTransportOwnerMobileNumber(signUpRequest.getMobile());
          transportDetails.setCreatedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
          transportDetails.setKyc(false);
          WalletRequest walletRequest = new WalletRequest();
          walletRequest.setBalance(0);
          walletRequest.setOwnerName(signUpRequest.getName());
          walletRequest.setRoleName(String.valueOf(RoleName.ROLE_ADMIN));
          walletRequest.setWalletId(Long.parseLong(signUpRequest.getMobile()));
          long pin = Long.parseLong(signUpRequest.getMobile()) % 10000;
          walletRequest.setWalletPin(pin);

          walletService.add(walletRequest);
          return transportDetails;
    }

    @Override
    public TransportDetails getByMobileNumeber(String ownerMobileNumber) throws Exception {

        TransportDetails transportDetails = transportDetailsRepository.findByMdn(ownerMobileNumber);
        if(transportDetails==null){
            throw new Exception("Transport Data Not Exist.");
        }

        return transportDetails;
    }

    @Override
    public TransportDetails getByTransportNumber(String transportRegistrationNumber) throws Exception {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRegistrationNumber);
        if(transportDetails==null){
            throw new Exception("Transport Data Not Exist.");
        }

        return transportDetails;
    }

    @Override
    public Page<TransportDetails> listAllByPage(Pageable pageable) {
        return transportPageDetailsRepository.findAll(pageable);
    }

    @Override
    public TransportDetails updateTranportDetails(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        transportDetails.setKyc(true);
        transportDetails.setOwnerAadhaarNumber(transportRequest.getOwnerAadhaarNumber());
        transportDetails.setOwnerPanCardNumber(transportRequest.getOwnerPanCardNumber());
        transportDetails.setStatus(true);
        transportDetails.setDeleted(false);
        transportDetails.setTotalBuses(transportRequest.getTotalBuses());
        transportDetails.setTotalLoadVehicles(transportRequest.getTotalLoadVehicles());
        transportDetails.setOtherVehicles(transportRequest.getOtherVehicles());
        transportDetails.setTotalPersonalVehicles(transportRequest.getTotalPersonalVehicles());
        transportDetails.setTotalVehicles(transportRequest.getTotalVehicles());
        transportDetails.setTransportLocation(transportRequest.getTransportLocation());
        transportDetails.setTransportName(transportRequest.getTransportName());
        transportDetails.setTransportOwnerName(transportRequest.getTransportOwnerName());
        transportDetails.setTransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setGstNumber(transportRequest.getGstNumber());
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateOwnerImage(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        transportDetails.setOwnerImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateTransportLogo(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        transportDetails.setTransportLogo(Base64.getEncoder().encodeToString(transportRequest.getTransportLogo().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateTransportLicence(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        transportDetails.setTransportLicenceImage(Base64.getEncoder().encodeToString(transportRequest.getTransportLicenceImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateAadhaarImage(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        transportDetails.setOwnerAadhaarCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerAadhaarCardImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updatePanImage(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        transportDetails.setOwnerPanCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerPanCardImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateStatus(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setStatus(transportRequest.isStatus());
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails deleteByOtp(OtpRequest otpRequest) throws Exception {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(otpRequest.getTransportRegistrationNumber());
        Admin admin = adminRepository.findByMobile(otpRequest.getTransportRegistrationNumber());
        boolean verify = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());

        if(verify == false){

            throw new Exception("Otp is Expired.");
        }
        transportDetails.setDeleted(true);
        adminRepository.delete(admin);
        transportDetailsRepository.saveAndFlush(transportDetails);
        walletService.delete(otpRequest.getUserMobileNumber(),"ROLE_ADMIN");
        return null;
    }
}
