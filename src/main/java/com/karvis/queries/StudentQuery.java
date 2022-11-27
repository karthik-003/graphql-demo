package com.karvis.queries;


import com.karvis.model.Address;
import com.karvis.model.Department;
import com.karvis.model.Student;
import com.karvis.request.AddStudentRequest;
import com.karvis.request.FullNameRequests;
import com.karvis.response.StudentInfo;
import com.karvis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentQuery{

    @Autowired
    StudentService service;

    @QueryMapping
    public String firstQuery(){
        return "First Query";
    }

    @QueryMapping
    public String fullName(@Argument("fullNameRequests") FullNameRequests fullNameRequests){
        return fullNameRequests.getFirstName()+" "+fullNameRequests.getLastName();
    }

    @QueryMapping
    public List<StudentInfo> getAllStudents(){
        return service.getAllStudents();
    }

    @QueryMapping
    public StudentInfo getStudentById(@Argument String studentId){
        return StudentInfo.createBasicStudentInfo(service.getStudentById(studentId));
    }
    @SchemaMapping
    public Address address(StudentInfo student){
        return service.getAddressForStudent(student.getId());
    }

    @SchemaMapping
    public Department department(StudentInfo studentInfo){
        return service.getDepartmentForStudent(studentInfo.getId());
    }

    @QueryMapping
    public List<StudentInfo> getStudentsFromDepartment(@Argument List<DepartmentNameFilter> departmentNameFilters){
        if(departmentNameFilters.contains(DepartmentNameFilter.All)){
            return service.getAllStudents();
        }else{
            List<StudentInfo> filteredStudentList = new ArrayList<>();
            for(StudentInfo studentInfo: service.getAllStudents()){
                Department d = service.getDepartmentForStudent(studentInfo.getId());
                if(departmentNameFilters.contains(DepartmentNameFilter.valueOf(d.getName()))){
                    studentInfo.setDepartment(d);
                    filteredStudentList.add(studentInfo);
                }
            }
            return  filteredStudentList;
        }
    }

    @MutationMapping
    public StudentInfo addStudent(@Argument AddStudentRequest addStudentRequest){
        Student createdStudent = service.addStudent(addStudentRequest);
        return StudentInfo.createBasicStudentInfo(createdStudent);
    }

    @MutationMapping
    public StudentInfo updateStudentDepartment(@Argument String studentId,
                                               @Argument DepartmentNameFilter departmentNameFilter){
        if(!departmentNameFilter.name().equalsIgnoreCase("ALL")){
            Student updatedStudent = service.addDepartmentForStudent(studentId,departmentNameFilter.name());
            return StudentInfo.createBasicStudentInfo(updatedStudent);
        }else{
            throw new RuntimeException("Invalid department.");
        }
    }
}
