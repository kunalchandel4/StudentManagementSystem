package com.student.service;

import java.util.List;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;

public interface AdminService {
	public Student admitStudent(Student student) throws StudentNotFoundException ;
	public Course uploadCourse(Course course) throws CourseNotFoundException ;
	public boolean assignCoursesToStudent(Long studentId, List<Long> courseIds) throws StudentNotFoundException ;
	public List<Student> getStudentsByName(String name);
	public List<Student> getStudentsByCourse(Long courseId) throws CourseNotFoundException ;
}
