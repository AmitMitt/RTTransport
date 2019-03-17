package com.roadTransport.RTTransport.serviceImpl;

import com.roadTransport.RTTransport.entity.DeletedTransportDetails;
import com.roadTransport.RTTransport.entity.TransportDetails;
import com.roadTransport.RTTransport.entity.TransportTemporaryDetails;
import com.roadTransport.RTTransport.model.otp.OtpDetails;
import com.roadTransport.RTTransport.model.otp.OtpRequest;
import com.roadTransport.RTTransport.model.TransportRequest;
import com.roadTransport.RTTransport.otpService.OtpService;
import com.roadTransport.RTTransport.repository.deletedRepository.DeletedTransportRepository;
import com.roadTransport.RTTransport.repository.TransportDetailsRepository;
import com.roadTransport.RTTransport.repository.TransportPageDetailsRepository;
import com.roadTransport.RTTransport.repository.TransportTemporaryDetailsRepository;
import com.roadTransport.RTTransport.service.TransportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

public class TransportDetailsServiceImpl implements TransportDetailsService {

    @Autowired
    private TransportPageDetailsRepository transportPageDetailsRepository;

    @Autowired
    private TransportTemporaryDetailsRepository transportTemporaryDetailsRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private TransportDetailsRepository transportDetailsRepository;

    @Autowired
    private DeletedTransportRepository deletedTransportRepository;


    @Override
    public TransportDetails add(OtpRequest otpRequest) throws Exception {

        TransportTemporaryDetails transportTemporaryDetails = transportTemporaryDetailsRepository.findBytransportRegistrationNumber(otpRequest.getTransportRegistrationNumber());

        if(transportTemporaryDetails == null){
            throw new Exception("Data Not Exist.");
        }

        boolean verify = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());

        if(verify == false){

            throw new Exception("Otp is Expired.");
        }
        else{

            TransportDetails transportDetails = new TransportDetails();
            transportDetails.setCreatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
            transportDetails.setOwnerPanCardImage(transportTemporaryDetails.getOwnerPanCardImage());
            transportDetails.setKyc(true);
            transportDetails.setOwnerAadhaarCardImage(transportTemporaryDetails.getOwnerAadhaarCardImage());
            transportDetails.setOwnerAadhaarNumber(transportTemporaryDetails.getOwnerAadhaarNumber());
            transportDetails.setOwnerImage(transportTemporaryDetails.getOwnerImage());
            transportDetails.setOwnerPanCardNumber(transportTemporaryDetails.getOwnerPanCardNumber());
            transportDetails.setStatus(true);
            transportDetails.setTotalBuses(transportTemporaryDetails.getTotalBuses());
            transportDetails.setTotalLoadVehicles(transportTemporaryDetails.getTotalLoadVehicles());
            transportDetails.setOtherVehicles(transportTemporaryDetails.getOtherVehicles());
            transportDetails.setTotalPersonalVehicles(transportTemporaryDetails.getTotalPersonalVehicles());
            transportDetails.setTotalVehicles(transportTemporaryDetails.getTotalVehicles());
            transportDetails.setTransportLicenceImage(transportTemporaryDetails.getTransportLicenceImage());
            transportDetails.setTransportLocation(transportTemporaryDetails.getTransportLocation());
            transportDetails.setTransportName(transportTemporaryDetails.getTransportName());
            transportDetails.setTransportOwnerMobileNumber(transportTemporaryDetails.getTransportOwnerMobileNumber());
            transportDetails.setTransportOwnerName(transportTemporaryDetails.getTransportOwnerName());
            transportDetails.setTransportRegistrationNumber(transportTemporaryDetails.getTransportRegistrationNumber());
            transportDetails.setTransportLogo(transportTemporaryDetails.getTransportLogo());

            transportDetailsRepository.saveAndFlush(transportDetails);
            return transportDetails;

        }
    }

    @Override
    public TransportDetails getByMobileNumeber(long ownerMobileNumber) throws Exception {

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
    public DeletedTransportDetails delete(String transportRegistationNumber) throws Exception {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRegistationNumber);
        DeletedTransportDetails deletedTransportDetails = new DeletedTransportDetails();

        deletedTransportDetails.setCreatedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        deletedTransportDetails.setOtherVehicles(transportDetails.getOtherVehicles());
        deletedTransportDetails.setOwnerAadhaarCardImage(transportDetails.getOwnerAadhaarCardImage());
        deletedTransportDetails.setOwnerAadhaarNumber(transportDetails.getOwnerAadhaarNumber());
        deletedTransportDetails.setOwnerImage(transportDetails.getOwnerImage());
        deletedTransportDetails.setOwnerPanCardImage(transportDetails.getOwnerPanCardImage());
        deletedTransportDetails.setOwnerPanCardNumber(transportDetails.getOwnerPanCardNumber());
        deletedTransportDetails.setStatus(false);
        deletedTransportDetails.setTotalBuses(transportDetails.getTotalBuses());
        deletedTransportDetails.setTotalLoadVehicles(transportDetails.getTotalLoadVehicles());
        deletedTransportDetails.setTotalPersonalVehicles(transportDetails.getTotalPersonalVehicles());
        deletedTransportDetails.setTotalVehicles(transportDetails.getTotalVehicles());
        deletedTransportDetails.setTransportLicenceImage(transportDetails.getTransportLicenceImage());
        deletedTransportDetails.setTransportLocation(transportDetails.getTransportLocation());
        deletedTransportDetails.setTransportLogo(transportDetails.getTransportLogo());
        deletedTransportDetails.setTransportName(transportDetails.getTransportName());
        deletedTransportDetails.setTransportOwnerMobileNumber(transportDetails.getTransportOwnerMobileNumber());
        deletedTransportDetails.setTransportOwnerName(transportDetails.getTransportOwnerName());
        deletedTransportDetails.setTransportRegistrationNumber(transportDetails.getTransportRegistrationNumber());

        OtpDetails otpDetails = otpService.getOtp(transportDetails.getTransportOwnerMobileNumber());
        deletedTransportDetails.setOtp(otpDetails.getOtpNumber());
        deletedTransportRepository.saveAndFlush(deletedTransportDetails);
        return deletedTransportDetails;
    }

    @Override
    public Page<TransportDetails> listAllByPage(Pageable pageable) {
        return transportPageDetailsRepository.findAll(pageable);
    }

    @Override
    public TransportDetails updateTranportDetails(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        transportDetails.setKyc(true);
        transportDetails.setOwnerAadhaarNumber(transportRequest.getOwnerAadhaarNumber());
        transportDetails.setOwnerPanCardNumber(transportRequest.getOwnerPanCardNumber());
        transportDetails.setStatus(true);
        transportDetails.setTotalBuses(transportRequest.getTotalBuses());
        transportDetails.setTotalLoadVehicles(transportRequest.getTotalLoadVehicles());
        transportDetails.setOtherVehicles(transportRequest.getOtherVehicles());
        transportDetails.setTotalPersonalVehicles(transportRequest.getTotalPersonalVehicles());
        transportDetails.setTotalVehicles(transportRequest.getTotalVehicles());
        transportDetails.setTransportLocation(transportRequest.getTransportLocation());
        transportDetails.setTransportName(transportRequest.getTransportName());
        transportDetails.setTransportOwnerMobileNumber(transportRequest.getTransportOwnerMobileNumber());
        transportDetails.setTransportOwnerName(transportRequest.getTransportOwnerName());
        transportDetails.setTransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateOwnerImage(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        transportDetails.setOwnerImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateTransportLogo(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        transportDetails.setTransportLogo(Base64.getEncoder().encodeToString(transportRequest.getTransportLogo().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateTransportLicence(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        transportDetails.setTransportLicenceImage(Base64.getEncoder().encodeToString(transportRequest.getTransportLicenceImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updateAadhaarImage(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        transportDetails.setOwnerAadhaarCardImage(Base64.getEncoder().encodeToString(transportRequest.getOwnerAadhaarCardImage().getBytes()));
        transportDetailsRepository.saveAndFlush(transportDetails);

        return transportDetails;
    }

    @Override
    public TransportDetails updatePanImage(TransportRequest transportRequest) {

        TransportDetails transportDetails = transportDetailsRepository.findBytransportRegistrationNumber(transportRequest.getTransportRegistrationNumber());
        transportDetails.setModifiedDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
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

        boolean verify = otpService.verify(otpRequest.getOtp(),otpRequest.getUserMobileNumber());

        if(verify == false){

            throw new Exception("Otp is Expired.");
        }

        transportDetailsRepository.delete(transportDetails);

        return null;
    }
}
