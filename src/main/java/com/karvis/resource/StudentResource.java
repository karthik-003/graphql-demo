package com.karvis.resource;

import com.karvis.model.Student;
import com.karvis.response.StudentInfo;
import com.karvis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("students")
public class StudentResource {

    @Autowired
    StudentService service;

    @GetMapping("/")
    public ResponseEntity<List<StudentInfo>> getAllStudents(){
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id){
        System.out.println("Getting student with id: "+id);
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<List<Student>> getStudentsByDepartment(@RequestBody String departmentName){
        System.out.println("Get students by department: "+departmentName);
        return ResponseEntity.ok(service.getStudentsByDepartment(departmentName));
    }


}
