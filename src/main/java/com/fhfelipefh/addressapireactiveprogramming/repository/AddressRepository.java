package com.fhfelipefh.addressapireactiveprogramming.repository;

import com.fhfelipefh.addressapireactiveprogramming.model.Address;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {

}
