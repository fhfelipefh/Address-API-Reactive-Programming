package com.fhfelipefh.addressapireactiveprogramming.service;

import com.fhfelipefh.addressapireactiveprogramming.model.Address;
import com.fhfelipefh.addressapireactiveprogramming.repository.AddressRepository;
import com.fhfelipefh.addressapireactiveprogramming.util.CreateAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    private Address address = CreateAddress.buildAddress();

    @BeforeEach
    public void setUp() {
        assertNotNull(addressRepository);

        BDDMockito.when(addressRepository.findAll()).thenReturn(Flux.just(address));

        BDDMockito.when(addressRepository.findById(address.getId())).thenReturn(Mono.just(address));

        BDDMockito.when(addressRepository.save(ArgumentMatchers.any(Address.class))).thenReturn(Mono.just(address));

    }

    @Test
    @DisplayName("findAll Address returns a flux of address")
    public void findAll_ReturnFluxOfAddress_WhenSuccessful() {
        StepVerifier.create(addressService.findAllAddresses())
                .expectSubscription()
                .expectNext(address)
                .verifyComplete();
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById Address returns a Mono of address")
    public void findById_ReturnMonoOfAddress_WhenSuccessful() {
        StepVerifier.create(addressService.findAddressById(address.getId()))
                .expectSubscription()
                .expectNext(address)
                .verifyComplete();
    }

    @Test
    @DisplayName("save Address returns a Mono of address")
    public void save_ReturnMonoOfAddress_WhenSuccessful() {
        StepVerifier.create(addressService.saveAddress(address))
                .expectSubscription()
                .expectNext(address)
                .verifyComplete();
    }

    @Test
    @DisplayName("delete Address returns a Mono of address")
    public void delete_ReturnMonoOfAddress_WhenSuccessful() {
        BDDMockito.when(addressRepository.deleteById(address.getId())).thenReturn(Mono.empty());
        StepVerifier.create(addressService.deleteAddressById(address.getId()))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("update Address returns a Mono of address")
    public void update_ReturnMonoOfAddress_WhenSuccessful() {
        StepVerifier.create(addressService.updateAddress(address.getId(),address))
                .expectSubscription()
                .expectNext(address)
                .verifyComplete();
    }


}