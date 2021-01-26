package com.ticoyk.sspractice.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {
    
    @GetMapping(path = "{studentId}")
    public Student getStudednt(@PathVariable("studentId") Long studentId){


        
    }
}
