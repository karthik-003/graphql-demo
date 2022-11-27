package com.karvis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class Department {
    String id;
    String name;
    String head;
}
