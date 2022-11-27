package com.karvis.utils;

import com.github.javafaker.Faker;
import com.karvis.model.Address;
import com.karvis.model.Department;
import com.karvis.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MockStudentUtils {

    Faker faker = new Faker();

    private final Integer MAX_AGE = 23;
    private final Integer MIN_AGE = 15;

    private final String[] DEPARTMENTS = {"Computers","Electronics","Mechanical","Civil","Aerospace","Electrical"};
    private Map<String, Department> DEPT_MAP = new TreeMap<>();
    public MockStudentUtils(){
        DEPT_MAP.put("Computers",Department.builder().id("1").head("Gary Bran").name("Computers").build());
        DEPT_MAP.put("Electronics",Department.builder().id("2").head("Ross Wells").name("Electronics").build());
        DEPT_MAP.put("Mechanical",Department.builder().id("3").head("Benjamin Stone").name("Mechanical").build());
        DEPT_MAP.put("Civil",Department.builder().id("4").head("Eirch Gamma").name("Civil").build());
        DEPT_MAP.put("Aerospace",Department.builder().id("5").head("Richard Helm").name("Aerospace").build());
        DEPT_MAP.put("Electrical",Department.builder().id("6").head("Ralph Johnson").name("Electrical").build());
    }

    public Student getMockStudent(String id){
        return Student.builder()
                .id(id)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(String.valueOf(faker.number().numberBetween(MIN_AGE,MAX_AGE)))
                .gender(faker.number().numberBetween(0,2) == 0 ?"MALE":"FEMALE")
                .address(getMockAddress())
                .dept(DEPT_MAP.get(DEPARTMENTS[faker.number().numberBetween(0,DEPARTMENTS.length)]))
                .build();
    }

    private Address getMockAddress(){
        return Address.builder()
                .city(faker.address().city())
                .locality(faker.address().streetAddress())
                .state(faker.address().state())
                .zipCode(faker.address().zipCode())
                .build();
    }

    public Department getDepartmentInfo(String deptName){
        return DEPT_MAP.get(deptName);
    }
    private int getRandomNumberBetween(int min,int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
