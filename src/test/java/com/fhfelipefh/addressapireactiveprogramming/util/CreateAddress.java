package com.fhfelipefh.addressapireactiveprogramming.util;

import com.fhfelipefh.addressapireactiveprogramming.model.Address;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddress {

    public static Address buildAddress() {
        return Address.builder()
                .id(1L)
                .number("0")
                .street("Rua dos Bobos")
                .cep("01234567")
                .city("São Paulo")
                .state("SP")
                .country("BR")
                .type("HOME")
                .build();
    }

}
