package com.jai.Java_deployement.mapper;

import com.jai.Java_deployement.dto.AddressDto;
import com.jai.Java_deployement.dto.UserDto;
import com.jai.Java_deployement.entity.Address;
import com.jai.Java_deployement.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        List<Address> addressList = new ArrayList<>();

        //if (userDto.getAddresses() != null){
            for (AddressDto addressDto : userDto.getAddresses()) {
                Address address = new Address();
                address.setHouseNumber(addressDto.getHouseNumber());
                address.setStreet(addressDto.getStreet());
                address.setCity(addressDto.getCity());
                address.setCountry(addressDto.getCountry());
                address.setPincode(addressDto.getPincode());

                System.out.println("jaideep mapped address "+ address);
                address.getUsers().add(user); // set reverse link
                addressList.add(address);
            }
        //}

        user.setAddresses(addressList);
        return user;
    }

    public static UserDto toDTO(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        List<AddressDto> addresses = new ArrayList<>();
        for (Address address : user.getAddresses()) {

            AddressDto addressDto = new AddressDto();
            addressDto.setHouseNumber(address.getHouseNumber());
            addressDto.setStreet(address.getStreet());
            addressDto.setCity(address.getCity());
            addressDto.setCountry(address.getCountry());
            addressDto.setPincode(address.getPincode());
            addresses.add(addressDto);
        }

        dto.setAddresses(addresses);
        return dto;
    }

    public static User toEntity(UserDto userDto, List<Address> addresses){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddresses(addresses);
        return user;
    }
}
