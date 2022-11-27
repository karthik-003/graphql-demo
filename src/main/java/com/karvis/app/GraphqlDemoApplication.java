package com.karvis.app;

import com.karvis.model.Address;
import com.karvis.model.Student;
import com.karvis.utils.MockStudentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication
@ComponentScan("com.karvis")
public class GraphqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}

	@Autowired
	MockStudentUtils mockStudentUtils;

	Map<String,Student> studentRepo = new HashMap<>();

	@Bean
	@Scope("singleton")
	public Map<String, Student> studentsRepo(){
		System.out.println("Initializing repo...");
		if(studentRepo.isEmpty()){
			for(int i=0;i<20;i++){
				String id = UUID.randomUUID().toString();
				studentRepo.put(id,mockStudentUtils.getMockStudent(id));
			}
		}

		return studentRepo;
	}

}
