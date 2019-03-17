package com.roadTransport.RTTransport.repository.deletedRepository;

import com.roadTransport.RTTransport.entity.DeletedTransportDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedTransportPageDetailsRepositoy extends PagingAndSortingRepository<DeletedTransportDetails, Long> {
}
