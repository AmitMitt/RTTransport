package com.roadTransport.RTTransport.repository;

import com.roadTransport.RTTransport.entity.TransportDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportPageDetailsRepository extends PagingAndSortingRepository<TransportDetails, Long> {
}
