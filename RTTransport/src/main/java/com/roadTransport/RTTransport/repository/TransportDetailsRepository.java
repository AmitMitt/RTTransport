package com.roadTransport.RTTransport.repository;

import com.roadTransport.RTTransport.entity.TransportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface TransportDetailsRepository extends JpaRepository<TransportDetails, Long> {

    @Query("Select t from TransportDetails t where t.ownerMobileNumber = :ownerMobileNumber")
    public TransportDetails findByMdn(@PathVariable("ownerMobileNumber") long ownerMobileNumber);

    @Query("Select t from TransportDetails t where t.transportRegistrationNumber = :transportRegistrationNumber")
    public TransportDetails findBytransportRegistrationNumber(@PathVariable("transportRegistrationNumber") String transportRegistrationNumber);
}
