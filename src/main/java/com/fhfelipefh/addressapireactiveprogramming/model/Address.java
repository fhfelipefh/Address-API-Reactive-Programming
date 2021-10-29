package com.fhfelipefh.addressapireactiveprogramming.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("address")
public class Address {

    @Id
    private Long id;

    @NotBlank
    @Column("number")
    private String number;

    @NotBlank
    @Column("street")
    @Size(max = 255)
    private String street;

    @NotBlank
    @Column("cep")
    private String cep;

    @NotBlank
    @Column("city")
    private String city;

    @NotBlank
    @Column("state")
    private String state;

    @NotBlank
    @Column("country")
    private String country;

    @NotBlank
    @Column("type")
    private String type;
}
