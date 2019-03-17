package com.roadTransport.RTTransport.repository;

import com.roadTransport.RTTransport.entity.TransportDetails;
import com.roadTransport.RTTransport.entity.TransportTemporaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface TransportTemporaryDetailsRepository extends JpaRepository<TransportTemporaryDetails, Long>{

    @Query("Select t from TransportTemporaryDetails t where t.ownerMobileNumber = :ownerMobileNumber")
    public TransportTemporaryDetails findByMdn(@PathVariable("ownerMobileNumber") long ownerMobileNumber);

    @Query("Select t from TransportTemporaryDetails t where t.transportRegistrationNumber = :transportRegistrationNumber")
    public TransportTemporaryDetails findBytransportRegistrationNumber(@PathVariable("transportRegistrationNumber") String transportRegistrationNumber);

}