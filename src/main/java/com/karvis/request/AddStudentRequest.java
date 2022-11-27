package com.karvis.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddStudentRequest {
    String firstName;
    String lastName;
    String gender;
    String age;
    String locality;
    String city;
    String state;
    String zipCode;
}
