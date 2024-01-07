package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.entity.StudentDTO;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;
import com.student.service.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PutMapping("/updateProfile/{studentId}")
    public ResponseEntity<Student> updateProfile(@PathVariable Long studentId,
                                                 @RequestBody StudentDTO studentDTO) throws StudentNotFoundException {
        Student updatedStudent = studentService.updateProfile(studentId, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @GetMapping("/getAssignedCourses/{studentId}")
    public ResponseEntity<List<Course>> getAssignedCourses(@PathVariable Long studentId) throws StudentNotFoundException {
        List<Course> assignedCourses = studentService.getAssignedCourses(studentId);
        return new ResponseEntity<>(assignedCourses, HttpStatus.OK);
    }

    @PostMapping("/leaveCourse/{studentId}/{courseId}")
    public ResponseEntity<Void> leaveCourse(@PathVariable Long studentId,
                                            @PathVariable Long courseId) throws StudentNotFoundException, CourseNotFoundException {
        studentService.leaveCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

