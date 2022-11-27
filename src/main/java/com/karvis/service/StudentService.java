package com.karvis.service;

import com.karvis.model.Address;
import com.karvis.model.Department;
import com.karvis.model.Student;
import com.karvis.queries.DepartmentNameFilter;
import com.karvis.request.AddStudentRequest;
import com.karvis.response.StudentInfo;
import com.karvis.utils.MockStudentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    Map<String,Student> studentRepo;

    @Autowired
    MockStudentUtils mockStudentUtils;

    public List<StudentInfo> getAllStudents(){

        return studentRepo.values().stream()
                .map(student ->
                    StudentInfo.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .gender(student.getGender())
                            .age(student.getAge())
                            .build()
                ).collect(Collectors.toList());
    }

    public Student getStudentById(String id){
        return studentRepo.get(id);
    }

    public List<Student> getStudentsByDepartment(String departmentName){
        return studentRepo.values().stream()
                .filter(student -> student.getDept().getName().equals(departmentName))
                .collect(Collectors.toList());
    }

    public Address getAddressForStudent(String id){
        return studentRepo.get(id).getAddress();
    }

    public Department getDepartmentForStudent(String id){
        return studentRepo.get(id).getDept();
    }

    public Student addStudent(AddStudentRequest request){

        String id = UUID.randomUUID().toString();
        Student _student = Student.builder()
                .id(id)
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .gender(request.getGender())
                .age(request.getAge())
                .address(Address.builder()
                        .locality(request.getLocality())
                        .zipCode(request.getZipCode())
                        .state(request.getState())
                        .city(request.getCity())
                        .build())
                .build();
        studentRepo.put(id,_student);
        return _student;
    }

    public Student addDepartmentForStudent(String studentId,String deptName){
        Department _d = mockStudentUtils.getDepartmentInfo(deptName);
        Student _s = studentRepo.get(studentId);
        _s.setDept(_d);
        studentRepo.put(studentId,_s);
        return _s;
    }
}
