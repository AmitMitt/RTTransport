package com.roadTransport.RTTransport.service;

import com.roadTransport.RTTransport.entity.TransportDetails;
import com.roadTransport.RTTransport.model.SignUpRequest;
import com.roadTransport.RTTransport.model.otp.OtpRequest;
import com.roadTransport.RTTransport.model.TransportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TransportDetailsService {

        public TransportDetails add (SignUpRequest signUpRequest);
        public TransportDetails getByMobileNumeber(String ownerMobileNumber) throws Exception;
        public TransportDetails getByTransportNumber(String transportRegistrationNumber) throws Exception;
        public Page<TransportDetails> listAllByPage(Pageable pageable);
        public TransportDetails updateTranportDetails(TransportRequest transportRequest);
        public TransportDetails updateOwnerImage(TransportRequest transportRequest);
        public TransportDetails updateTransportLogo(TransportRequest transportRequest);
        public TransportDetails updateTransportLicence(TransportRequest transportRequest);
        public TransportDetails updateAadhaarImage(TransportRequest transportRequest);
        public TransportDetails updatePanImage(TransportRequest transportRequest);
        public TransportDetails updateStatus(TransportRequest transportRequest);
        public TransportDetails deleteByOtp (OtpRequest otpRequest) throws Exception;

}
