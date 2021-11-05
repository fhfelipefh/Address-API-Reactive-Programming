package com.fhfelipefh.addressapireactiveprogramming.repository;

import com.fhfelipefh.addressapireactiveprogramming.model.Address;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {

    @Query("SELECT * FROM address WHERE cep LIKE :cep")
    Flux<Address> findAddressByCep(String cep);
}

