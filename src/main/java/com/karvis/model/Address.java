package com.karvis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class Address {
    String locality;
    String city;
    String state;
    String zipCode;
}
