package com.student.service;

import java.util.List;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.entity.StudentDTO;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;

public interface StudentService {

	public Student updateProfile(Long studentId, StudentDTO studentDTO) throws StudentNotFoundException ;
	public List<Course> getAssignedCourses(Long studentId) throws StudentNotFoundException ;
	public void leaveCourse(Long studentId, Long courseId) throws StudentNotFoundException, CourseNotFoundException;
}
