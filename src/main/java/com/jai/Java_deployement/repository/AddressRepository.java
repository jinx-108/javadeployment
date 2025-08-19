package com.jai.Java_deployement.repository;

import com.jai.Java_deployement.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByHouseNumberAndStreetAndCityAndCountryAndPincode(
            String houseNumer,
            String street,
            String city,
            String country,
            String pincode
    );
}
