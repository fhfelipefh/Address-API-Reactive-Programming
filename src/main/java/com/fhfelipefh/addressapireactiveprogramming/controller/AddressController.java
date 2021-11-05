package com.fhfelipefh.addressapireactiveprogramming.controller;

import com.fhfelipefh.addressapireactiveprogramming.model.Address;
import com.fhfelipefh.addressapireactiveprogramming.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public Flux<Address> findAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping(path = "/cep/{cep}")
    public Flux<Address> findByCep(@PathVariable String cep) {
        return addressService.findByCep(cep);
    }

    @GetMapping(path = "{id}")
    public Mono<Address> findById(@PathVariable Long id) {
        return addressService.findAddressById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Address> saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Address> update(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Address> deleteById(@PathVariable Long id) {
        return addressService.deleteAddressById(id);
    }

    @DeleteMapping(path = "deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return addressService.deleteAll();
    }

}
