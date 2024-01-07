package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Course;
import com.student.entity.CurrentTrack;
import com.student.entity.Student;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;
import com.student.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;
    
    private CurrentTrack cs;

    @PostMapping("/admitStudent")
    public ResponseEntity<Student> admitStudent(@RequestBody Student student) throws StudentNotFoundException {
        Student admittedStudent = adminService.admitStudent(student);
        return new ResponseEntity<>(admittedStudent, HttpStatus.CREATED);
    }

    @PostMapping("/uploadCourse")
    public ResponseEntity<Course> uploadCourse(@RequestBody Course course) throws CourseNotFoundException {
        Course uploadedCourse = adminService.uploadCourse(course);
        return new ResponseEntity<>(uploadedCourse, HttpStatus.CREATED);
    }

    @PostMapping("/assignCourses/{studentId}")
    public ResponseEntity<Void> assignCoursesToStudent(@PathVariable Long studentId,
                                                       @RequestBody List<Long> courseIds) throws StudentNotFoundException {
        adminService.assignCoursesToStudent(studentId, courseIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getStudentsByName")
    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name) {
        List<Student> students = adminService.getStudentsByName(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/getStudentsByCourse/{courseId}")
	public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId)
			throws CourseNotFoundException {
        List<Student> students = adminService.getStudentsByCourse(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
