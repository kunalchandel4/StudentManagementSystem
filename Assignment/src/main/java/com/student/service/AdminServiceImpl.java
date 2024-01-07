package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;
import com.student.repository.CourseRepo;
import com.student.repository.StudentRepo;

@Service
public class AdminServiceImpl  implements AdminService{

	@Autowired
	private StudentRepo studentRepository;

	@Autowired
	private CourseRepo courseRepository;

	public Student admitStudent(Student student) throws StudentNotFoundException {

		return studentRepository.save(student);
	}

	public Course uploadCourse(Course course) throws CourseNotFoundException {

		return courseRepository.save(course);
	}

	public boolean assignCoursesToStudent(Long studentId, List<Long> courseIds) throws StudentNotFoundException {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));

		List<Course> courses = courseRepository.findAllById(courseIds);
		student.getCourses().addAll(courses);

		studentRepository.save(student);
		return true;
	}

	public List<Student> getStudentsByName(String name) {

		return studentRepository.findByName(name);
	}

	public List<Student> getStudentsByCourse(Long courseId) throws CourseNotFoundException {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

		return course.getStudents();
	}

}
