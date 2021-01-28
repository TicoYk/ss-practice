package com.ticoyk.sspractice.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "Xico"),
        new Student(2, "Chicona"),
        new Student(3, "Chiquinho")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("creating student");
        System.out.println(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Integer id) {
        System.out.println("deleting student");
        System.out.println(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable("id") Integer id,@RequestBody Student student) {
        System.out.println("Updating students");
        System.out.println(String.format("%s %s", id, student));
    }

}
