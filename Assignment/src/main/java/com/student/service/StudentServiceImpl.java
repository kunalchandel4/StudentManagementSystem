package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.entity.StudentDTO;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;
import com.student.repository.StudentRepo;

@Service
public class StudentServiceImpl  implements StudentService{

	@Autowired
	private StudentRepo studentRepository;

	public Student updateProfile(Long studentId, StudentDTO studentDTO) throws StudentNotFoundException {
	
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));

		
		student.setEmail(studentDTO.getEmail());
		student.setMobileNumber(studentDTO.getMobileNumber());
		student.setParentsName(studentDTO.getParentsName());

		return studentRepository.save(student);
	}

	public List<Course> getAssignedCourses(Long studentId) throws StudentNotFoundException {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));

		return student.getCourses();
	}

	public void leaveCourse(Long studentId, Long courseId) throws StudentNotFoundException, CourseNotFoundException {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));

		Course course = student.getCourses().stream().filter(c -> c.getId().equals(courseId)).findFirst()
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

		student.getCourses().remove(course);

		studentRepository.save(student);
	}
}
