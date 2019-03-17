package com.roadTransport.RTTransport.repository.deletedRepository;


import com.roadTransport.RTTransport.entity.DeletedTransportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface DeletedTransportRepository extends JpaRepository <DeletedTransportDetails, Long>{

    @Query("Select d from DeletedTransportDetails d where d.transportRegistrationNumber = :transportRegistrationNumber")
    public DeletedTransportDetails findByTRN(@PathVariable("transportRegistrationNumber") String transportRegistrationNumber);
}
