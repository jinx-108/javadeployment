package com.jai.Java_deployement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String pincode;
}
