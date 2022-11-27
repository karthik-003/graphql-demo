package com.karvis.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Student {
    String id;
    String firstName;
    String lastName;
    String gender;
    String age;
    Address address;
    Department dept;
}
