package com.karvis.response;

import com.karvis.model.Address;
import com.karvis.model.Department;
import com.karvis.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentInfo {

    String id;
    String firstName;
    String lastName;
    String gender;
    String age;

    Address address;
    Department department;

    public static StudentInfo createBasicStudentInfo(Student student){
        return StudentInfo.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .gender(student.getGender())
                .address(student.getAddress())
                .department(student.getDept())
                .build();
    }
}
