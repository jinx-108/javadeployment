package com.jai.Java_deployement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String pincode;

    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(mappedBy = "addresses", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

}
