package com.jai.Java_deployement.service;

import com.jai.Java_deployement.dto.AddressDto;
import com.jai.Java_deployement.dto.UserDto;
import com.jai.Java_deployement.entity.Address;
import com.jai.Java_deployement.entity.User;
import com.jai.Java_deployement.exception.UserNotFoundException;
import com.jai.Java_deployement.mapper.UserMapper;
import com.jai.Java_deployement.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.jai.Java_deployement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    public UserDto findUserById(int id){

        return UserMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id, " does not exist in the DB")));
    }

    public User addUserWithoutDto(User user){
        return userRepository.save(user);
    }


    public List<UserDto> getAllUsers(){

        return userRepository.findAll().stream()
                .map(UserMapper::toDTO).collect(Collectors.toList());
    }
    public UserDto addUser(UserDto userDto){

        List<Address> addresses;
        addresses = getAllAddresses(userDto);

        User user = UserMapper.toEntity(userDto, addresses);
        return UserMapper.toDTO(userRepository.save(user));
    }



    private List<Address> getAllAddresses(UserDto userDto){
        List<AddressDto> addressDtos = userDto.getAddresses();
        List<Address> addresses = new ArrayList<>();

        if (addressDtos != null){
            for (AddressDto addressDto: addressDtos) {
                Optional<Address> existingAddress = addressRepository.findByHouseNumberAndStreetAndCityAndCountryAndPincode(addressDto.getHouseNumber(),
                        addressDto.getStreet(),
                        addressDto.getCity(),
                        addressDto.getCountry(),
                        addressDto.getPincode());

                Address address;
                if (existingAddress.isPresent()) {
                    address = existingAddress.get();
                } else {
                    address = new Address();
                    address.setHouseNumber(addressDto.getHouseNumber());
                    address.setStreet(addressDto.getStreet());
                    address.setCity(addressDto.getCity());
                    address.setCountry(addressDto.getCountry());
                    address.setPincode(addressDto.getPincode());
                    //address = addressRepository.save(address); // save new one
                }
                addresses.add(address);
            }
        }
        return addresses;
    }
}
