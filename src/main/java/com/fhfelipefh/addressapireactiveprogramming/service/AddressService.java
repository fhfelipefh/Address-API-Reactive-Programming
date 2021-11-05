package com.fhfelipefh.addressapireactiveprogramming.service;

import com.fhfelipefh.addressapireactiveprogramming.model.Address;
import com.fhfelipefh.addressapireactiveprogramming.repository.AddressRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Mono<Address> updateAddress(Long id, Address address) {
        addressRepository.deleteById(id);
        return addressRepository.save(address);
    }

    public Mono<Address> findAddressById(Long id) {
        log.info("Getting address with id {}", id);
        return addressRepository.findById(id);
    }


    public Flux<Address> findAllAddresses() {
        log.info("Getting all addresses");
        return addressRepository.findAll();
    }

    public Mono<Address> saveAddress(Address address) {
        log.info("Saving address");
        System.out.println(address.toString());
        return addressRepository.save(address);
    }

    @Transactional
    public Flux<Address> saveAll(List<Address> addresses) {
        return addressRepository.saveAll(addresses)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    public Mono deleteAll() {
        log.info("Deleting all addresses");
        return addressRepository.deleteAll();
    }

    public Mono deleteAddressById(Long id) {
        log.info("Deleting address with id {}", id);
        findAddressById(id)
                .flatMap(addressRepository::delete);
        return Mono.empty();
    }

    private void throwResponseStatusExceptionWhenEmptyName(Address address) {
        if (StringUtil.isNullOrEmpty(address.getState())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid State");
        }
    }

    public Flux<Address> findByCep(String cep) {
        return addressRepository.findAddressByCep(cep);
    }
}
