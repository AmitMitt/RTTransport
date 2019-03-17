package com.roadTransport.RTTransport.repository;

import com.roadTransport.RTTransport.entity.TransportTemporaryDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTemporaryPageDetailsRepository extends PagingAndSortingRepository<TransportTemporaryDetails,Long> {
}
